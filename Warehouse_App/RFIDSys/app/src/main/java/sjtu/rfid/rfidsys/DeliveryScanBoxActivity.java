package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
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
        }

        tmpAdapter = new DeliverySheetsScanBoxExpandableAdapter(this, mDeliveryBoxesDetails, mDeliveryBoxes,mDeliveryBoxesItemsList);
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


                if (isReading) {
                    isReading = false;
                    btnScan.setText("扫描标签");
                    stopAction();
                    mReader.stop();
                    //deliveryScanBoxScanTagThread.setIsReading(false);

                } else if (!isReading) {
                    saveOption();
                    for(Map.Entry<String,Set<String>> entry:mDeliveryBoxesItemsList.entrySet()){
                        entry.getValue().clear();
                    }
                    tmpAdapter.notifyDataSetChanged();
                    isReading = true;
                    btnScan.setText("停止扫描");
//                    deliveryScanBoxScanTagThread = new DeliveryScanBoxScanTagThread(mDeliveryBoxesItemsList, true, handlerScanTag);
//                    deliveryScanBoxScanTagThread.start();
                    startAction(true);
                    mReader.connect();
                }

                //读货物标签线程

            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> CNums = new ArrayList<String>();
                String[] CNumString;
                cartonList = cartonList.trim();
                CNumString = cartonList.split(",");
                for (int i = 0; i < CNumString.length; i++) {
                    CNums.add(CNumString[i]);
                }
                deliverySubmitThread = new DeliverySubmitThread(applyCode, CNums, handlerDelivery);
                deliverySubmitThread.start();
            }
        });
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
        }
        @Override
        public void run() {
            ConnectServer connectServer=new ConnectServer();
            RFIDService.Client client = connectServer.openConnect();
            Good good = null;
            try {
                good = client.getGoodByCNum(epc);
            } catch (TException e) {
                e.printStackTrace();
            }
            String matCode = good.getCode();
            Integer scanCnt = mDeliveryBoxesItemsList.get(matCode).size();
            Integer expecteCnt = Integer.valueOf(mDeliveryBoxesDetails.get(matCode).get("expectedCount"));
            if( (mDeliveryBoxesItemsList.containsKey(matCode)) && !mDeliveryBoxesItemsList.get(matCode).contains(epc)
                    && scanCnt < expecteCnt) {
                mDeliveryBoxesItemsList.get(matCode).add(epc);
                Message msg = handlerScanTag.obtainMessage();
                msg.what = 1;
                handlerScanTag.sendMessage(msg);
            }
        }
    }
}
