package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
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
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rfid.service.Good;
import rfid.service.check;
import sjtu.rfid.entity.Config;
import sjtu.rfid.thread.CheckByPosScanLocThread;
import sjtu.rfid.thread.CheckByPosScanTagThread;
import sjtu.rfid.thread.CheckByPosThread;
import sjtu.rfid.thread.CheckThread;
import sjtu.rfid.thread.GetGoodByCartNumThread;
import sjtu.rfid.thread.PutInStorageThread;
import sjtu.rfid.tools.CheckByPosExpandableAdapter;
import sjtu.rfid.tools.Converters;
import sjtu.rfid.tools.SoundPlay;
import sjtu.rfid.tools.TitleBar;

public class CheckByPosActivity extends Activity implements RfidReaderEventListener {

    private ExpandableListView sheetListView;
    private CheckByPosExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mCheckByPosDetailList;
    private List<Map<String,String>> mCheckByPosList;

    private TitleBar mTitleBar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private int goodPos=-1;
    private CheckByPosThread checkByPosThread;
    private CheckByPosScanLocThread checkByPosScanLocThread;
    private CheckByPosScanTagThread checkByPosScanTagThread;

    private boolean isReading = false;

    private List<Good> goodList;
    private Map<String,Boolean> boxList;

    private int scanType=-1;
    private CheckThread checkThread;
    private Map<String,check> checkList;
    private boolean checkResult;

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
            checkList = new HashMap<>();
            for( Good g : goodList ) {
                check c = new check(String.valueOf(goodPos),g.getCode(),0,new Timestamp(Calendar.getInstance().getTimeInMillis()).toString());
                checkList.put(g.getCode(),c);
            }
            iniListView(goodList);

        }
    };

    private Handler handlerCheck=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkResult=(boolean)msg.obj;
            if(checkResult) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handlerScan = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if( msg.what == 0 ) {
                TextView vGoosdPos=(TextView)findViewById(R.id.text_check_by_pos_loc);
                goodPos = Integer.parseInt(Config.LocationMap.get(msg.obj.toString()));
                vGoosdPos.setText("货位:" + msg.obj.toString());
                checkByPosThread=new CheckByPosThread(goodPos,handler);
                checkByPosThread.start();
            } else if ( msg.what == 1) {
                //tmpAdapter.notifyDataSetChanged();
            } else if (msg.what == 2) {
                if( boxList.containsKey(msg.obj.toString()) && !boxList.get(msg.obj.toString()) ) {
                    boxList.put(msg.obj.toString(),true);
                    new GetGoodByCartNumThread(msg.obj.toString(), handlerScan).start();
                    Log.i("111111111111111aaa",msg.obj.toString());
                }
            } else if (msg.what == 3) {
                Good g = (Good)msg.obj;
                if( checkList.containsKey(g.getCode()) ) {
                    int cur = checkList.get(g.getCode()).getRealNum();
                    checkList.get(g.getCode()).setRealNum(cur+1);
                    tmpAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_pos);

        mSound = new SoundPlay(getApplicationContext());
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                CheckByPosActivity.this.finish();
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
                if (scanType==1&&Config.LocationMap.containsKey(epc.substring(0,3))) {
                    Message msg = handlerScan.obtainMessage();
                    msg.what = 0;
                    msg.obj = epc.substring(0,3);
                    handlerScan.sendMessage(msg);
                }else if(scanType==2&&epc.length()==16){
                    final String tmp =epc;

                    new Thread() {
                        @Override
                        public void run() {
                            Message msg = handlerScan.obtainMessage();
                            msg.what = 2;
                            msg.obj = tmp;
                            handlerScan.sendMessage(msg);
                        }
                    }.start();

                    //更新界面已扫到数量并构造checkList

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
    public void iniActivity(){
        mTitleBar = new TitleBar(this,"货位盘点");
        boxList = new HashMap<>();
    }

    public void iniListView(List<Good> goodList) {

        mCheckByPosDetailList = new HashMap<String, Map<String, String>>();
        mCheckByPosList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_check_by_pos_sheets);

        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("expectedCount", String.valueOf(good.getNum()));
            map.put("realCount", "0");
            mCheckByPosList.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
            detailMap.put("matName", good.getDetail());
            mCheckByPosDetailList.put(good.getCode(), detailMap);

            for(String s : good.getCartonNums()) {
                boxList.put(s,false);
            }

        }

        tmpAdapter = new CheckByPosExpandableAdapter(this,mCheckByPosDetailList,mCheckByPosList,checkList);
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
        Button btnScanLoc=(Button)findViewById(R.id.btn_check_by_pos_scan_loc);
        final Button btnScanBox=(Button)findViewById(R.id.btn_check_by_pos_scan_box);
        final Button btnCommit=(Button)findViewById(R.id.btn_check_by_pos_commit);

        btnScanLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货位标签线程
                saveOption(300);
                scanType=1;
                startAction(false);
                mReader.connect();
            }
        });
        btnScanBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货物标签线程
                if( !isReading ) {
                    saveOption(100);
                    isReading = true;
                    btnScanBox.setText("停止扫描");
                    scanType=2;
                    startAction(true);
                    mReader.connect();

                } else {
                    isReading = false;
                    btnScanBox.setText("扫描货物盘点");
                    stopAction();
                    mReader.stop();
                }


                //startAction(false);
                //mReader.connect();


            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未更新checkList

                checkThread=new CheckThread(checkList,handlerCheck);
                checkThread.start();
            }
        });
    }
}
