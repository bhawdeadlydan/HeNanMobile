package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.thrift.TException;
import java.util.logging.LogRecord;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.thread.CheckByMatThread;
import sjtu.rfid.tools.ConnectServer;
import sjtu.rfid.tools.Converters;
import sjtu.rfid.tools.SoundPlay;
import sjtu.rfid.tools.TitleBar;

public class LookUpActivity extends Activity implements RfidReaderEventListener {

    TitleBar mTitle;
    private static final String TAG = "MainActivity";
    private static final boolean ENABLE_LOG = false;
    private static final String LOG_PATH = "Log";
    private SoundPlay mSound;
    private GoogleApiClient client;

    private int mOperationTime;
    private int mInventoryTime;
    private int mIdleTime;
    private int mPowerLevel;

    private Good curScanGood = null;
    //20160411 当前扫到的箱子EPC号
    private String curScanEpc = null;

    private ATRfidReader mReader = null;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                ((TextView)findViewById(R.id.text_lookup_cartum)).setText(curScanEpc);
                ((TextView)findViewById(R.id.text_lookup_matname)).setText(curScanGood.getDetail());
                ((TextView)findViewById(R.id.text_lookup_cnt)).setText(String.valueOf(curScanGood.getNum()));
                ((TextView)findViewById(R.id.text_lookup_unit)).setText(curScanGood.getUnit());
                ((TextView)findViewById(R.id.text_lookup_isbom)).setText(curScanGood.isIs_Bom() ? "Y" : "N");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_up);
        mTitle = new TitleBar(this,"查看货箱");

        mSound = new SoundPlay(getApplicationContext());
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                LookUpActivity.this.finish();
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

        Button btn = (Button) findViewById(R.id.btn_lookup_scan);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOption();
                startAction(false);
                mReader.connect();
            }
        });
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
    public void onReaderActionChanged(ATRfidReader atRfidReader, final ActionState action) {
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
                    ScanBoxThread scanThread = new ScanBoxThread(epc);
                    scanThread.start();
                }
            }
        });
        mSound.playSuccess();
    }

    @Override
    public void onReaderResult(ATRfidReader reader, final ResultCode code, ActionState action, final String epc, final String data) {

    }

    class ScanBoxThread extends Thread {
        private  String epc;

        public ScanBoxThread(String epc) {
            this.epc = epc;
            curScanEpc = epc;
        }

        @Override
        public void run() {

            ConnectServer connectServer = new ConnectServer();
            RFIDService.Client client = connectServer.openConnect();
            try {
                curScanGood = client.getGoodByCNum(epc);
            } catch (TException e) {
                e.printStackTrace();
            }
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }
    }
}
