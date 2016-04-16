package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atid.lib.dev.ATRfidManager;
import com.atid.lib.dev.ATRfidReader;
import com.atid.lib.dev.event.RfidReaderEventListener;
import com.atid.lib.dev.rfid.exception.ATRfidReaderException;
import com.atid.lib.dev.rfid.type.ActionState;
import com.atid.lib.dev.rfid.type.ConnectionState;
import com.atid.lib.dev.rfid.type.ResultCode;
import com.atid.lib.dev.rfid.type.TagType;
import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.dialog.WaitDialog;
import com.atid.lib.util.SysUtil;

import org.apache.thrift.TException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.thread.DeliveryScanBoxScanTagThread;
import sjtu.rfid.thread.DeliveryScanBoxThread;
import sjtu.rfid.thread.DeliverySubmitThread;
import sjtu.rfid.thread.ReceivingScanBoxScanTagThread;
import sjtu.rfid.tools.ConnectServer;
import sjtu.rfid.tools.Converters;
import sjtu.rfid.tools.DeliverySheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.SoundPlay;
import sjtu.rfid.tools.TitleBar;

public class DeliveryScanBoxActivity extends Activity implements RfidReaderEventListener {

    private TextView vDeliverySheetCode;
    private ExpandableListView sheetListView;
    private DeliverySheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mDeliveryBoxesDetails;
    private List<Map<String,String>> mDeliveryBoxes;

    //记录每个ERP编码下，已经扫到的箱号，避免重复扫描造成错误计数
    private Map<String, Set<String>> mDeliveryBoxesItemsList;

    //20160411 记录已经拣选的某类货物的总共数量,Key:物料ERP编码，Value:该物料总数量
    private HashMap<String,Integer> mDeliveryBoxesMatCount;
    //20160411 记录每个箱子里面取了多少件东西,Key:箱子EPC编码，Value：取了该箱内多少件物品
    private HashMap<String,Integer> mDeliveryBoxesPickCountInsideBox;
    //20160411 当前扫到的箱子所包含的货物
    private Good curScanGood = null;
    //20160411 当前扫到的箱子EPC号
    private String curScanEpc = null;

    private List<String> CNumList;

    private TitleBar mTitleBar;

    private String cartonList="\n";
    private DeliveryScanBoxThread deliveryScanBoxThread;
    private DeliverySubmitThread deliverySubmitThread;
    private DeliveryScanBoxScanTagThread deliveryScanBoxScanTagThread;
    boolean isReading = false;

    private List<Good> goodList;
    private String applyCode;
    private boolean deliveryResult;

    private static final String TAG = "MainActivity";
    private static final boolean ENABLE_LOG = false;
    private static final String LOG_PATH = "Log";
    private SoundPlay mSound;

    private int mOperationTime;
    private int mInventoryTime;
    private int mIdleTime;
    private int mPowerLevel;

    private ATRfidReader mReader = null;

    //20160411 Add Dialog To The Activity
    private AlertDialog pickDialog;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            goodList=(List<Good>)msg.obj;
            iniListView(goodList);
        }
    };

    private Handler handlerDelivery=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            deliveryResult=(boolean)msg.obj;
            if(deliveryResult) {
                Toast.makeText(getApplicationContext(), "数据提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "数据提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handlerScanTag=new Handler() {
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                tmpAdapter.notifyDataSetChanged();
            }
        }
    };

    private Handler handlerPickDialog = new Handler() {
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0){
                pickDialog.show();
                String[] strs = (String[]) msg.obj;
                String matCode = strs[0];
                String epc = strs[1];
                Window window = pickDialog.getWindow();
                TextView cartonNum = ((TextView)window.findViewById(R.id.text_dialog_delivery_scan_box_cartonNum));
                TextView matName = ((TextView)window.findViewById(R.id.text_dialog_delivery_scan_box_matName));
                TextView expectCnt = ((TextView)window.findViewById(R.id.text_dialog_delivery_scan_box_expectCnt));
                TextView cartonCnt = ((TextView)window.findViewById(R.id.text_dialog_delivery_scan_box_cartonCnt));
                EditText pickCnt = ((EditText)window.findViewById(R.id.edittext_dialog_delivery_scan_box_pickCnt));
                cartonNum.setText(curScanEpc);
                matName.setText(curScanGood.getDetail());
                expectCnt.setText(mDeliveryBoxesDetails.get(matCode).get("expectedCount"));
                cartonCnt.setText(String.valueOf(curScanGood.Num));
                pickCnt.setText(curScanGood.getExpected_Quantity() == 1 ? "1" : "0");
            }
            else if(msg.what == 1) {
                Toast.makeText(getApplicationContext(),"货箱不在当前出库单中或已经被拣选过！",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_scan_box);

        mSound = new SoundPlay(getApplicationContext());
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                DeliveryScanBoxActivity.this.finish();
            }
        });

        if ((mReader = ATRfidManager.getInstance()) == null) {
            WaitDialog.hide();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("出错啦");
            builder.setMessage("连接UHF模块错误,请检查UHF 模块！");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }
        mSound.playSuccess();

        vDeliverySheetCode=(TextView)findViewById(R.id.text_delivery_scan_box_order_code);
        Bundle bundle=this.getIntent().getExtras();
        applyCode=bundle.getString("delivery_sheet_code");
        vDeliverySheetCode.setText(applyCode);
        iniActivity();
        iniEvent();
        iniDialog();

        deliveryScanBoxThread=new DeliveryScanBoxThread(handler,applyCode);
        deliveryScanBoxThread.start();
    }

    @Override
    protected void onDestroy() {

        ATRfidManager.onDestroy();
        SysUtil.wakeUnlock();
        ATLog.d(TAG, "INFO. onDestroy");
        ATLog.shutdown();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mReader != null) {
            ATRfidManager.wakeUp();

            if (mReader.getState() == ConnectionState.Connected) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        try {
                            ATLog.i(TAG, String.valueOf(mReader.getPower()));
                        } catch (ATRfidReaderException ax) {
                            ATLog.i(TAG, ax.getMessage());

                        }
                    }
                }).start();;
            }
        }


        ATLog.i(TAG, "INFO. onStart()");
    }

    @Override
    protected void onStop() {
        ATRfidManager.sleep();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReader != null)
            mReader.setEventListener(this);
    }

    @Override
    protected void onPause() {
        if (mReader != null)
            mReader.removeEventListener(this);
        super.onPause();
    }

    protected void stopAction() {
        ResultCode res;
        if ((res = mReader.stop()) != ResultCode.NoError) {
            return;
        }
    }

    // ------------------------------------------------------------------------
    // Reader Control Methods
    // ------------------------------------------------------------------------

    // Start Action
    protected void startAction(boolean type) {

        ResultCode res;
        TagType tagType = TagType.Tag6C;//getTagType();


        if (type) {
            // Multi Reading
            switch (tagType) {
                case Tag6C:
                    if ((res = mReader.inventory6cTag()) != ResultCode.NoError) {
                        return;
                    }
                    break;
                case Tag6B:
                    if ((res = mReader.inventory6bTag()) != ResultCode.NoError) {
                        return;
                    }
                    break;
            }
        } else {
            // Single Reading
            switch (tagType) {
                case Tag6C:
                    if ((res = mReader.readEpc6cTag()) != ResultCode.NoError) {
                        return;
                    }
                    break;
                case Tag6B:
                    if ((res = mReader.readEpc6bTag()) != ResultCode.NoError) {
                        return;
                    }
                    break;
            }
        }
    }

    @Override
    public void onReaderStateChanged(final ATRfidReader reader, final ConnectionState state) {
        switch (state) {
            case Connected:
                WaitDialog.hide();
                String version = "";
                try {
                    version = mReader.getFirmwareVersion();
                } catch (ATRfidReaderException e) {
                    version = "";
                    mReader.disconnect();
                }
                //Toast.makeText(this, version, Toast.LENGTH_SHORT);
                break;
            case Disconnected:
                WaitDialog.hide();
                break;
            default:
                break;
        }
    }

    @Override
    public void onReaderActionChanged(ATRfidReader reader, final ActionState action) {
        if (action == ActionState.Stop) {
        }
    }

    @Override
    public void onReaderReadTag(ATRfidReader reader, final String tag, final float rssi) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String epc = new String(Converters.fromHexString(tag.substring(4)));
                //Toast.makeText(getApplicationContext(),epc,Toast.LENGTH_SHORT).show();
                //获取标签
                if(epc.length()==16) {
                    ScanThread scanThread = new ScanThread(epc);
                    scanThread.start();
                }
            }
        });
        mSound.playSuccess();

    }

    @Override
    public void onReaderResult(ATRfidReader reader, final ResultCode code, ActionState action, final String epc, final String data) {

    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"申领出库");
    }

    public void iniListView(List<Good> goodList) {
        mDeliveryBoxesDetails=new HashMap<String, Map<String, String>>();
        mDeliveryBoxes = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_delivery_scan_box_sheets);
        mDeliveryBoxesItemsList = new HashMap<>();
        mDeliveryBoxesMatCount = new HashMap<>();
        mDeliveryBoxesPickCountInsideBox = new HashMap<>();
        CNumList=new ArrayList<>();

        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("matName", good.getDetail());
            map.put("expectedCount", String.valueOf(good.getNum()));
            mDeliveryBoxes.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom() ? "Y" : "N");
            detailMap.put("expectedCount",String.valueOf(good.getNum()));
//            for(String cartonNum:good.getCartonNums()){
//                cartonList+=cartonNum+"，";
//            }
//            detailMap.put("cartonList",cartonList);
            detailMap.put("cartonList","");
            mDeliveryBoxesDetails.put(good.getCode(), detailMap);

            Set<String> boxSet = new HashSet<>();
            mDeliveryBoxesItemsList.put(good.getCode(),boxSet);

            //20160411 Key:物品MatCode，Value:拣货数量
            mDeliveryBoxesMatCount.put(good.getCode(),0);
        }

        tmpAdapter = new DeliverySheetsScanBoxExpandableAdapter(this, mDeliveryBoxesDetails, mDeliveryBoxes,mDeliveryBoxesItemsList,mDeliveryBoxesMatCount);
        sheetListView.setAdapter(tmpAdapter);


        sheetListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < tmpAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        sheetListView.collapseGroup(i);
                    }
                }
            }
        });
    }

    public void iniEvent(){
        final Button btnScan=(Button)findViewById(R.id.btn_delivery_scan_box_scan);
        Button btnCommit=(Button)findViewById(R.id.btn_delivery_scan_box_commit);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveOption();
                startAction(false);
                mReader.connect();

//                if (isReading) {
//                    isReading = false;
//                    btnScan.setText("扫描标签");
//                    stopAction();
//                    mReader.stop();
//                    //deliveryScanBoxScanTagThread.setIsReading(false);
//
//                } else if (!isReading) {
//                    saveOption();
//                    for (Map.Entry<String, Set<String>> entry : mDeliveryBoxesItemsList.entrySet()) {
//                        entry.getValue().clear();
//                    }
//                    tmpAdapter.notifyDataSetChanged();
//                    isReading = true;
//                    btnScan.setText("停止扫描");
////                    deliveryScanBoxScanTagThread = new DeliveryScanBoxScanTagThread(mDeliveryBoxesItemsList, true, handlerScanTag);
////                    deliveryScanBoxScanTagThread.start();
//                    startAction(true);
//                    mReader.connect();
//                }

                //读货物标签线程

            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliverySubmitThread = new DeliverySubmitThread(applyCode, CNumList, mDeliveryBoxesPickCountInsideBox, handlerDelivery);
                deliverySubmitThread.start();
            }
        });
    }

    private void iniDialog() {

        TableLayout dialogForm = (TableLayout)getLayoutInflater().inflate(R.layout.dialog_delivery_scan_box,null);
        pickDialog = new AlertDialog.Builder(this)
                .setTitle("拣选货物")
                .setView(dialogForm)
                .setPositiveButton("确认回写数据", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!CNumList.contains(curScanEpc)){
                            CNumList.add(curScanEpc);
                        }
                        String matCode = curScanGood.getCode();
                        Window window = pickDialog.getWindow();
                        Integer val = Integer.valueOf(((EditText)window.findViewById(R.id.edittext_dialog_delivery_scan_box_pickCnt)).getText().toString());
                        mDeliveryBoxesMatCount.put(matCode,mDeliveryBoxesMatCount.get(matCode)+val);
                        mDeliveryBoxesPickCountInsideBox.put(curScanEpc,val);
                        mDeliveryBoxesItemsList.get(curScanGood.getCode()).add(curScanEpc);
                        Message msg = handlerScanTag.obtainMessage();
                        msg.what = 1;
                        handlerScanTag.sendMessage(msg);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        //pickDialog.show();
    }

    private void saveOption() {
        mOperationTime = 0;
        mInventoryTime = 400;
        mIdleTime = 200;
        mPowerLevel = 100;


        try {
            mReader.setPower(mPowerLevel);
        }

        catch(ATRfidReaderException e) {
            e.printStackTrace();
        }

        try {
            Log.i(TAG, "********************************" + String.valueOf(mReader.getPower()));
        }
        catch(ATRfidReaderException e) {
            e.printStackTrace();
        }

        try {
            mReader.setInventoryTime(mInventoryTime);
        }

        catch(ATRfidReaderException e) {
            e.printStackTrace();
        }

        try {
            Log.i(TAG, "********************************" + String.valueOf(mReader.getInventoryTime()));
        }

        catch(ATRfidReaderException e) {
            e.printStackTrace();
        }

        try{
            mReader.setIdleTime(mIdleTime);
        }
        catch(ATRfidReaderException e){
            e.printStackTrace();
        }

        try{
            Log.i(TAG, "********************************" + String.valueOf(mReader.getIdleTime()));
        }

        catch(ATRfidReaderException e){
            e.printStackTrace();
        }

        try{
            mReader.setOperationTime(mOperationTime);
        }

        catch(ATRfidReaderException e) {
            e.printStackTrace();
        }

        try {
            Log.i(TAG, "********************************" + String.valueOf(mReader.getOperationTime()));
        }
        catch(ATRfidReaderException e)
        {
            e.printStackTrace();
        }

    }

    class ScanThread extends Thread{

        private  String epc;

        public ScanThread(String epc) {
            this.epc = epc;
            curScanEpc = epc;
        }
        @Override
        public void run() {

            ConnectServer connectServer=new ConnectServer();
            RFIDService.Client client = connectServer.openConnect();
            try {
                curScanGood = client.getGoodByCNum(epc);
            } catch (TException e) {
                e.printStackTrace();
            }
            String matCode = curScanGood.getCode();
            if( (mDeliveryBoxesItemsList.containsKey(matCode)) && !mDeliveryBoxesItemsList.get(matCode).contains(epc)) {
                String[] strs = { matCode,epc};
                Message msg = handlerPickDialog.obtainMessage();
                msg.what = 0;
                msg.obj = strs;
                handlerPickDialog.sendMessage(msg);

//                TextView cartonNum = ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_cartonNum));
//                TextView matName = ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_matName));
//                TextView expectCnt = ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_expectCnt));
//                TextView cartonCnt = ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_cartonCnt));
//                EditText pickCnt = ((EditText)findViewById(R.id.edittext_dialog_delivery_scan_box_pickCnt));
//                cartonNum.setText(curScanEpc);
//                matName.setText(curScanGood.getDetail());
//                expectCnt.setText(mDeliveryBoxesDetails.get(matCode).get("expectedCount"));
//                cartonCnt.setText(curScanGood.getNum());
//                pickCnt.setText(curScanGood.getExpected_Quantity() == 1 ? "1" : "0");


//                ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_cartonNum)).setText(curScanEpc);
//                ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_matName)).setText(curScanGood.getDetail());
//                ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_expectCnt)).setText(mDeliveryBoxesDetails.get(matCode).get("expectedCount"));
//                ((TextView)findViewById(R.id.text_dialog_delivery_scan_box_cartonCnt)).setText(curScanGood.getNum());
//                ((EditText)findViewById(R.id.edittext_dialog_delivery_scan_box_pickCnt))
//                        .setText(curScanGood.getExpected_Quantity() == 1 ? "1" : "0");
                //pickDialog.show();
//                if(!CNumList.contains(epc)){
//                    CNumList.add(epc);
//                }
//                mDeliveryBoxesItemsList.get(matCode).add(epc);
//                Message msg = handlerScanTag.obtainMessage();
//                msg.what = 1;
//                handlerScanTag.sendMessage(msg);
            } else {
                Message msg = handlerPickDialog.obtainMessage();
                msg.what = 1;
                handlerPickDialog.sendMessage(msg);
            }

        }
    }
}
