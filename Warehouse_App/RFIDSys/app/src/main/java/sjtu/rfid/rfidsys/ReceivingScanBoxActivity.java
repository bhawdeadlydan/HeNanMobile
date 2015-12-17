package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import rfid.service.ASN;
import rfid.service.Good;
import rfid.service.RFIDClient;
import rfid.service.RFIDService;
import sjtu.rfid.thread.PrintThread;
import sjtu.rfid.thread.ReceivingScanBoxScanTagThread;
import sjtu.rfid.thread.ReceivingScanBoxThread;
import sjtu.rfid.thread.ReceivingSubmitThread;
import sjtu.rfid.tools.ConnectServer;
import sjtu.rfid.tools.Converters;
import sjtu.rfid.tools.ReceivingSheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.SoundPlay;
import sjtu.rfid.tools.TitleBar;

public class ReceivingScanBoxActivity extends Activity implements RfidReaderEventListener {

    private TextView vReceSheetCode;
    private String sheetCode="";
    private ExpandableListView sheetListView;
    private ReceivingSheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mReceivingBoxesDetails;
    private List<Map<String,String>> mReceivingBoxes;

    //记录每个ERP编码下，已经扫到的箱号，避免重复扫描造成错误计数
    private Map<String, Set<String>> mReceivingBoxesItemsList;

    private TitleBar mTitleBar;


    private ReceivingScanBoxThread receivingScanBoxThread;
    private ReceivingSubmitThread receivingSubmitThread;
    private PrintThread printThread;
    boolean isReading = false;

    private List<Good> goodList;
    private boolean receivingResult;


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
    private Handler handlerReceiving=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            receivingResult=(boolean)msg.obj;
            if(receivingResult) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handlerPrint=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0)
                Toast.makeText(getApplicationContext(), "打印失败", Toast.LENGTH_SHORT).show();
            else if(msg.what==1)
                Toast.makeText(getApplicationContext(),"打印成功，请前往获取标签！", Toast.LENGTH_SHORT).show();
            ((Button)findViewById(R.id.btn_receiving_scan_box_print)).setEnabled(false);
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
        setContentView(R.layout.activity_receiving_scan_box);

        mSound = new SoundPlay(getApplicationContext());
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ReceivingScanBoxActivity.this.finish();
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

        vReceSheetCode=(TextView)findViewById(R.id.text_receiving_scan_box_order_number);
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        sheetCode=bundle.getString("receiving_sheet_code");
        vReceSheetCode.setText(sheetCode);
        iniActivity();
        iniEvent();

        receivingScanBoxThread=new ReceivingScanBoxThread(handler,sheetCode);
        receivingScanBoxThread.start();
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

    public void iniActivity(){
        mTitleBar = new TitleBar(this,"收货贴标");
    }

    public void iniListView(List<Good> goodList) {
        mReceivingBoxesDetails=new HashMap<>();
        mReceivingBoxes = new ArrayList<>();
        mReceivingBoxesItemsList = new HashMap<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_scan_box_sheets);
        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("matName", good.getDetail());
            map.put("boxCount",String.valueOf(good.getNum()));
            mReceivingBoxes.add(map);

            Map<String, String> detailMap = new HashMap<>();
            String cartonList="\n";
            detailMap.put("isBom", good.isIs_Bom() ? "Y" : "N");
            for(String cartonNum:good.getCartonNums()){
                cartonList+=cartonNum+"\n";
            }
            detailMap.put("cartonList",cartonList);
            mReceivingBoxesDetails.put(good.getCode(), detailMap);

            Set<String> boxSet = new HashSet<>();
            mReceivingBoxesItemsList.put(good.getCode(),boxSet);

        }

        tmpAdapter = new ReceivingSheetsScanBoxExpandableAdapter(this, mReceivingBoxesDetails, mReceivingBoxes,mReceivingBoxesItemsList);
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
        final Button btnScan=(Button)findViewById(R.id.btn_receiving_scan_box_scan);
        Button btnCommit=(Button)findViewById(R.id.btn_receiving_scan_box_commit);
        Button btnPrint=(Button)findViewById(R.id.btn_receiving_scan_box_print);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货物标签线程
                saveOption(100);
                if( isReading ) {

                    isReading = false;
                    btnScan.setText("扫描标签");
                    stopAction();
                    mReader.stop();
                    //receivingScanBoxScanTagThread.setIsReading(isReading);
                } else if( !isReading ){
                    isReading = true;
                    btnScan.setText("停止扫描");
                    startAction(true);
                    mReader.connect();
                    //receivingScanBoxScanTagThread = new ReceivingScanBoxScanTagThread(mReceivingBoxesItemsList,isReading,handlerScanTag);
                    //receivingScanBoxScanTagThread.start();
                }
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                receivingSubmitThread = new ReceivingSubmitThread(sheetCode, handlerReceiving);
                receivingSubmitThread.start();

            }
        });

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                printThread = new PrintThread(sheetCode, handlerPrint);
                printThread.start();
            }
        });
    }


    private void saveOption(int mPowerLevel) {
        mOperationTime = 0;
        mInventoryTime = 400;
        mIdleTime = 200;
        //mPowerLevel = 100;


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
            for( Map.Entry<String, Map<String, String>> map : mReceivingBoxesDetails.entrySet() )
            {
                if( map.getValue().get("cartonList").contains(epc) ) {
                    if( mReceivingBoxesItemsList.containsKey(map.getKey()) && !mReceivingBoxesItemsList.get(map.getKey()).contains(epc)) {
                        mReceivingBoxesItemsList.get(map.getKey()).add(epc);
                        Message msg = handlerScanTag.obtainMessage();
                        msg.what = 1;
                        handlerScanTag.sendMessage(msg);
                        break;
                    }
                }
            }
        }
    }
}
