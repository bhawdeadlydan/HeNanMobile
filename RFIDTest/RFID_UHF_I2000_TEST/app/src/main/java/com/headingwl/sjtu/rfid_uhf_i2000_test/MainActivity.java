package com.headingwl.sjtu.rfid_uhf_i2000_test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
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

import com.headingwl.sjtu.rfid_uhf_i2000_test.uti.SoundPlay;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Wakelock")
public class MainActivity extends Activity implements OnClickListener, RfidReaderEventListener {

    private static final String TAG = "MainActivity";
    private static final boolean ENABLE_LOG = false;
    private static final String LOG_PATH = "Log";
    private SoundPlay mSound;
    private List<String> TagList;
    private EditText editText;

    private int mOperationTime;
    private int mInventoryTime;
    private int mIdleTime;
    private int mPowerLevel;

    private ATRfidReader mReader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Info OnCreate()");
        String appName = SysUtil.getAppName(this);

        TagList = new ArrayList<String>();

        mSound = new SoundPlay(getApplicationContext());
        editText = (EditText)findViewById(R.id.editText);

        if (ENABLE_LOG)
            ATLog.startUp(LOG_PATH, appName);

        SysUtil.wakeLock(this, appName);
        WaitDialog.show(this, "连接RFID模块中", "请稍等", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                MainActivity.this.finish();
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
        ATLog.i(TAG, "INFO. onCreate()");
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

            try
            {
                saveOption();
                ATLog.i(TAG, String.valueOf(mReader.getPower()));
            }
            catch (ATRfidReaderException ax)
            {
                ATLog.i(TAG, ax.getMessage().toString());

            }

        }
        ATLog.i(TAG, "INFO. onStart()");
    }

    @Override
    protected void onStop() {
        ATRfidManager.sleep();
        ATLog.i(TAG, "INFO. onStop()");
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReader != null)
            mReader.setEventListener(this);
        ATLog.d(TAG, "INFO onResume()");
    }

    @Override
    protected void onPause() {
        if (mReader != null)
            mReader.removeEventListener(this);
        ATLog.i(TAG, "INFO. onPause()");
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                TagList.clear();
                editText.setText("");
                mReader.connect();
                try
                {

                    ATLog.i(TAG, mReader.getPowerRange().toString());
                    ATLog.i(TAG, String.valueOf(mReader.getPower()));
                }
                catch (ATRfidReaderException ax)
                {
                    ATLog.i(TAG, ax.getMessage().toString());
                }
                startAction();
                break;
            case R.id.btnStop:
                stopAction();
                mReader.stop();
                //mReader.disconnect();
                break;
        }
    }

    protected void stopAction() {
        ResultCode res;
        if ((res = mReader.stop()) != ResultCode.NoError) {
            ATLog.e(TAG, "ERROR. stopAction() - Failed to stop operation [%s]", res);
            return;
        }
        ATLog.i(TAG, "INFO. stopAction()");
    }

    // ------------------------------------------------------------------------
    // Reader Control Methods
    // ------------------------------------------------------------------------

    // Start Action
    protected void startAction() {

        ResultCode res;
        TagType tagType = TagType.Tag6C;//getTagType();


        if (true) {
            // Multi Reading
            switch (tagType) {
                case Tag6C:
                    if ((res = mReader.inventory6cTag()) != ResultCode.NoError) {
                        ATLog.e(TAG, "ERROR. startAction() - Failed to start inventory 6C tag [%s]",
                                res);
                        return;
                    }
                    break;
                case Tag6B:
                    if ((res = mReader.inventory6bTag()) != ResultCode.NoError) {
                        ATLog.e(TAG, "ERROR. startAction() - Failed to start inventory 6B tag [%s]",
                                res);
                        return;
                    }
                    break;
            }
        } else {
            // Single Reading
            switch (tagType) {
                case Tag6C:
                    if ((res = mReader.readEpc6cTag()) != ResultCode.NoError) {
                        ATLog.e(TAG,
                                "ERROR. startAction() - Failed to start read 6C tag [%s]", res);
                        return;
                    }
                    break;
                case Tag6B:
                    if ((res = mReader.readEpc6bTag()) != ResultCode.NoError) {
                        ATLog.e(TAG,
                                "ERROR. startAction() - Failed to start read 6B tag [%s]", res);
                        return;
                    }
                    break;
            }
        }
        ATLog.i(TAG, "INFO. startAction()");
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
                    ATLog.e(TAG, e, "ERROR. onReaderStateChanged(%s) - Failed to get firmware version", state);
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

        ATLog.i(TAG, "EVENT. onReaderStateChanged(%s)", state);
    }

    @Override
    public void onReaderActionChanged(ATRfidReader reader, final ActionState action) {
        if (action == ActionState.Stop) {
        }
        ATLog.i(TAG, "EVENT. onReaderActionchanged(%s)", action);
    }

    @Override
    public void onReaderReadTag(ATRfidReader reader, final String tag, final float rssi) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!TagList.contains(tag))
                {
                    TagList.add(tag);
                    editText.setText(TagList.toString());
                }
            }
        });
        mSound.playSuccess();
        ATLog.i(TAG, "EVENT. onReaderReadTag([%s], %.2f)", tag, rssi);
    }

    @Override
    public void onReaderResult(ATRfidReader reader, final ResultCode code, ActionState action, final String epc, final String data) {
        ATLog.i(TAG, "EVENT. onReaderResult(%s, %s, [%s], [%s]", code, action, epc, data);
    }


    private void saveOption() {
        WaitDialog.show(this, "Save Properties...\r\nPlease Wait...");

        mOperationTime = 0;
        mInventoryTime = 400;
        mIdleTime = 20;
        mPowerLevel = 130;

        new Thread(new Runnable() {

            @Override
            public void run() {
                // Set Operation Time
                try {
                    mReader.setOperationTime(mOperationTime);
                } catch (ATRfidReaderException e) {
                    ATLog.e(TAG, e, "ERROR. saveOption() - Failed to set operation Time");

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            WaitDialog.hide();
                        }
                    });
                    return;
                }
                ATLog.i(TAG, "INFO. saveOption() - [Operation Time : %d]", mOperationTime);

                // Set Inventory Time
                try {
                    mReader.setInventoryTime(mInventoryTime);
                } catch (ATRfidReaderException e) {
                    ATLog.e(TAG, e, "ERROR. saveOption() - Failed to set inventory Time");

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            WaitDialog.hide();
                        }
                    });
                    return;
                }
                ATLog.i(TAG, "INFO. saveOption() - [Inventory Time : %d]", mInventoryTime);

                // Set Idle Time
                try {
                    mReader.setIdleTime(mIdleTime);
                } catch (ATRfidReaderException e) {
                    ATLog.e(TAG, e,
                            "ERROR. saveOption() - Failed to set idle Time");

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            WaitDialog.hide();
                        }
                    });
                    return;
                }
                ATLog.i(TAG, "INFO. saveOption() - [Idle Time : %d]", mIdleTime);

                // Set Power Level
                try {
                    mReader.setPower(mPowerLevel);
                } catch (ATRfidReaderException e) {
                    ATLog.e(TAG, e, "ERROR. saveOption() - Failed to set power level");

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            WaitDialog.hide();
                        }
                    });
                    return;
                }
                ATLog.i(TAG, "INFO. saveOption() - [Power Level : %d]", mPowerLevel);

            }
        }).start();
    }
}
