package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.ASN;
import rfid.service.Good;
import sjtu.rfid.entity.Config;
import sjtu.rfid.entity.PutInStorageEntity;
import sjtu.rfid.thread.BindThread;
import sjtu.rfid.thread.PutInStorageScanBoxThread;
import sjtu.rfid.thread.PutInStorageScanLocThread;
import sjtu.rfid.thread.PutInStorageThread;
import sjtu.rfid.tools.CheckByPosExpandableAdapter;
import sjtu.rfid.tools.Converters;
import sjtu.rfid.tools.PutInStorageExpandableAdapter;
import sjtu.rfid.tools.SoundPlay;
import sjtu.rfid.tools.TitleBar;

public class PutInStorageActivity extends Activity implements RfidReaderEventListener {

    private ExpandableListView sheetListView;
    private PutInStorageExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mPutInStorageDetailList;
    private List<Map<String,String>> mPutInStorageList;
    private TitleBar mTitleBar;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private PutInStorageThread putInStorageThread;
//    private PutInStorageScanLocThread scanLocThread;
//    private PutInStorageScanBoxThread scanBoxThread;
    private BindThread bindThread;
    private String CNum="EPC201509000000";
    private Good good;
    public int goodPos=-1;
    private boolean bindResult;

    private int scanType=-1;
    private static final String TAG = "PutInStorageActivity";
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
            bindResult=(boolean)msg.obj;
            if(bindResult) {
                Toast.makeText(getApplicationContext(), "绑定成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "绑定失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler scanHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                try {
                    String epc = (String) msg.obj;
                    TextView vGoodsPos = (TextView) findViewById(R.id.text_put_in_storage_loc);
                    if (Config.LocationMap.containsKey(epc)) {
                        goodPos = Integer.parseInt(Config.LocationMap.get(epc));
                        ((TextView) findViewById(R.id.text_put_in_storage_loc)).setText(epc);
                    }
                } catch (Exception e ) {
                    Log.e("error",e.getMessage());
                }
            }
            else if(msg.what == 1){
                PutInStorageEntity putInStorageEntity=(PutInStorageEntity)msg.obj;
                iniListView(putInStorageEntity);
            }
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
        setContentView(R.layout.activity_put_in_storage);

        mSound = new SoundPlay(getApplicationContext());
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PutInStorageActivity.this.finish();
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

        mPutInStorageDetailList = new HashMap<String, Map<String, String>>();
        mPutInStorageList = new ArrayList<>();
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
    protected void startAction(boolean type,int scanType) {

        this.scanType=scanType;
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
                stopAction();
                mReader.stop();
                String epc = new String(Converters.fromHexString(tag.substring(4)));
                //Toast.makeText(getApplicationContext(),tag+","+epc,Toast.LENGTH_SHORT).show();
                if (scanType==1&&Config.LocationMap.containsKey(epc.substring(0,3))) {
                    Message msg = scanHandler.obtainMessage();
                    msg.what = 0;
                    msg.obj = epc.substring(0,3);
                    scanHandler.sendMessage(msg);
                }else if(scanType==2&&epc.length()==16){
                    CNum=epc;
                    putInStorageThread=new PutInStorageThread(scanHandler,epc);
                    putInStorageThread.start();
                }
            }
        });
        mSound.playSuccess();

    }

    @Override
    public void onReaderResult(ATRfidReader reader, final ResultCode code, ActionState action, final String epc, final String data) {

    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"入库上架");
    }

    public void iniListView(PutInStorageEntity putInStorageEntity) {
        if(putInStorageEntity==null)
            return;

        if( !mPutInStorageDetailList.containsKey(CNum) ) {


            Good good = putInStorageEntity.getGood();
            sheetListView = (ExpandableListView) findViewById(R.id.list_put_in_storage_sheets);
            Map<String, String> map = new HashMap<>();
            map.put("boxCode", CNum);
            map.put("matName", good.getDetail());
            mPutInStorageList.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("matCode", good.getCode());
            detailMap.put("unit", good.getUnit());
            detailMap.put("count", String.valueOf(good.getNum()));
            detailMap.put("projectCode", good.getProjectCode());
            detailMap.put("asnCode", putInStorageEntity.getAsnCode());
            mPutInStorageDetailList.put(CNum, detailMap);

            tmpAdapter = new PutInStorageExpandableAdapter(this, mPutInStorageDetailList, mPutInStorageList);
            sheetListView.setAdapter(tmpAdapter);

            Message msg = handlerScanTag.obtainMessage();
            msg.what = 1;
            handlerScanTag.sendMessage(msg);

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

            ((TextView)findViewById(R.id.text_put_in_storage_box_cnt)).setText("已扫到货箱数量："+mPutInStorageDetailList.size());
        }

    }

    public void iniEvent(){
        Button btnScanLoc=(Button)findViewById(R.id.btn_put_in_storage_scan_loc);
        Button btnScanBox=(Button)findViewById(R.id.btn_put_in_storage_scan_box);
        Button btnClear=(Button)findViewById(R.id.btn_put_in_storage_clear);
        Button btnBind=(Button)findViewById(R.id.btn_put_in_storage_bind);

        btnScanLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOption(300);
                startAction(false,1);
                mReader.connect();
            }
        });
        btnScanBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOption(100);
                startAction(false,2);
                mReader.connect();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.text_put_in_storage_box_cnt)).setText("已扫到货箱数量：0");

                mPutInStorageDetailList.clear();
                mPutInStorageList.clear();

                Message msg = handlerScanTag.obtainMessage();
                msg.what = 1;
                handlerScanTag.sendMessage(msg);

            }
        });
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mPutInStorageDetailList.isEmpty()&&!mPutInStorageList.isEmpty()&&goodPos!=-1){
                    List<String> CNumList = new ArrayList<String>();
                    for (Map.Entry<String, Map<String, String>> entry : mPutInStorageDetailList.entrySet()) {
                        String cartonNum = entry.getKey();
                        CNumList.add(cartonNum);
                    }
                    bindThread = new BindThread(CNumList, goodPos, handler);
                    bindThread.start();
                }else{
                    Toast.makeText(getApplicationContext(),"未扫描货物或货位标签",Toast.LENGTH_SHORT).show();
                }
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
}
