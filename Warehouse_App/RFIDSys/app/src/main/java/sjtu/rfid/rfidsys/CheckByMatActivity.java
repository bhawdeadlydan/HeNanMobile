package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rfid.service.Good;
import rfid.service.LocationInfo;
import rfid.service.RFIDService;
import rfid.service.check;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.thread.CheckByMatThread;
import sjtu.rfid.thread.CheckThread;
import sjtu.rfid.tools.CheckByMatAdapter;
import sjtu.rfid.tools.ConnectServer;
import sjtu.rfid.tools.Converters;
import sjtu.rfid.tools.SoundPlay;
import sjtu.rfid.tools.TitleBar;

public class CheckByMatActivity extends Activity implements RfidReaderEventListener {

    private ListView sheetListView;
    private TitleBar mTitleBar;
    private CheckByMatAdapter mAdapter;
    private List<Map<String,String>> mCheckByMatList;

    private Map<Integer,Integer> posMap;
    private Set<String> boxSet;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private String CNum="";
    private CheckByMatThread checkByMatThread;
    private CheckByMatEntity checkByMatEntity;
    private CheckThread checkThread;
    private List<check> checkList;
    private boolean checkResult;

    private static final String TAG = "MainActivity";
    private static final boolean ENABLE_LOG = false;
    private static final String LOG_PATH = "Log";
    private SoundPlay mSound;

    private int mOperationTime;
    private int mInventoryTime;
    private int mIdleTime;
    private int mPowerLevel;

    private int type = -1;
    private String itemCode = "";
    private int curPos = -1;

    private ATRfidReader mReader = null;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkByMatEntity=(CheckByMatEntity)msg.obj;
            iniListView(checkByMatEntity);
        }
    };

    private Handler handlerCheck=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkResult=(boolean)msg.obj;
            if(checkResult)
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handlerScanTag = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if( msg.what == 0 ) {
                type = 1;
                curPos = Integer.valueOf(msg.obj.toString());
                        ((Button) findViewById(R.id.btn_check_by_mat_scan_box)).setText("停止扫描");
                startAction(true);
                mReader.connect();
            } else if (msg.what == 1) {
                type = -1;
                ((Button)findViewById(R.id.btn_check_by_mat_scan_box)).setText("扫描标签");
                stopAction();
                mReader.stop();
            } else if(msg.what == 2) {
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_mat);

        mSound = new SoundPlay(getApplicationContext());
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                CheckByMatActivity.this.finish();
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

        iniActivity();
        iniEvent();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
                Toast.makeText(this, version, Toast.LENGTH_SHORT);
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

                if( type == 0 ) {
                    stopAction();
                    mReader.stop();
                    String epc = new String(Converters.fromHexString(tag.substring(4)));
                    Toast.makeText(getApplicationContext(), epc, Toast.LENGTH_SHORT).show();
                    //获取标签
                    if (epc.length() == 16) {
                        CNum = epc;
                        checkByMatThread = new CheckByMatThread(CNum, handler);
                        checkByMatThread.start();
                    }
                    type = -1;
                } else if ( type == 1) {
                    String epc = new String(Converters.fromHexString(tag.substring(4)));
                    Toast.makeText(getApplicationContext(), epc, Toast.LENGTH_SHORT).show();
                    if( epc.length() == 16 ){
                        if( !boxSet.contains(epc) ) {
                            final String tmp = epc;
                            new Thread() {
                                @Override
                                public void run() {
                                    ConnectServer server = new ConnectServer();
                                    RFIDService.Client client = server.openConnect();
                                    try {
                                        Good g = client.getGoodByCNum(tmp);
                                        if( g.getCode().equals(itemCode) ) {
                                            boxSet.add(tmp);
                                            posMap.put(curPos,posMap.get(curPos)+1);
                                            Message msg = handlerScanTag.obtainMessage();
                                            msg.what = 2;
                                            handlerScanTag.sendMessage(msg);
                                        }
                                    } catch (TException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                        }

                    }
                }

            }
        });
        mSound.playSuccess();

    }

    @Override
    public void onReaderResult(ATRfidReader reader, final ResultCode code, ActionState action, final String epc, final String data) {

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

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"货物盘点");
    }

    public void iniListView(CheckByMatEntity checkByMatEntity) {



        mCheckByMatList=new ArrayList<>();
        Good good=checkByMatEntity.getGood();
        itemCode = good.getCode();
        List<LocationInfo> locationInfoList=checkByMatEntity.getLocationInfoList();

        sheetListView=(ListView)findViewById(R.id.list_check_by_mat_sheets);
        TextView vmatCode=(TextView)findViewById(R.id.text_check_by_mat_mat_code);
        vmatCode.setText(vmatCode.getText()+good.getCode());
        TextView vmatName=(TextView)findViewById(R.id.text_check_by_mat_mat_name);
        vmatName.setText(vmatName.getText() + good.getDetail());
        TextView visBom=(TextView)findViewById(R.id.text_check_by_mat_is_bom);
        visBom.setText(visBom.getText() + (good.isIs_Bom()?"Y":"N"));

        posMap = new HashMap<>();
        boxSet = new HashSet<>();

        for(LocationInfo locationInfo:locationInfoList){
            Map<String,String> mapDetail=new HashMap<>();
            mapDetail.put("posDes",String.valueOf(locationInfo.getID()));
            mapDetail.put("expectedCount",String.valueOf(locationInfo.getNum()));
            mapDetail.put("readedCount","0");
            mCheckByMatList.add(mapDetail);

            posMap.put(locationInfo.getID(),0);

        }
        mAdapter = new CheckByMatAdapter(this,mCheckByMatList,handlerScanTag,posMap);
        sheetListView.setAdapter(mAdapter);
    }

    public void iniEvent(){

        Button btnScanGetInfo=(Button)findViewById(R.id.btn_check_by_mat_scan_box_get_info);
        Button btnCommit=(Button)findViewById(R.id.btn_check_by_mat_commit);

        btnScanGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货位标签线程
                saveOption(300);
                startAction(true);
                type = 0;
                mReader.connect();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkThread=new CheckThread(checkList,handlerCheck);
                checkThread.start();
            }
        });
    }
}
