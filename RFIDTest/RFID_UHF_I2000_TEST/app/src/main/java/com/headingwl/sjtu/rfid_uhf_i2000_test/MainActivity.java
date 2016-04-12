package com.headingwl.sjtu.rfid_uhf_i2000_test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


import com.atid.lib.dev.ATRfidManager;
import com.atid.lib.dev.ATRfidReader;
import com.atid.lib.dev.event.RfidReaderEventListener;
import com.atid.lib.dev.rfid.exception.ATRfidReaderException;
import com.atid.lib.dev.rfid.param.RangeValue;
import com.atid.lib.dev.rfid.type.ActionState;
import com.atid.lib.dev.rfid.type.ConnectionState;
import com.atid.lib.dev.rfid.type.GlobalBandType;
import com.atid.lib.dev.rfid.type.ResultCode;
import com.atid.lib.dev.rfid.type.TagType;
import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.dialog.WaitDialog;
import com.atid.lib.util.SysUtil;

import com.headingwl.sjtu.rfid_uhf_i2000_test.uti.SoundPlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@SuppressLint("Wakelock")
public class MainActivity extends Activity implements OnClickListener, RfidReaderEventListener {

	private static final String TAG = "MainActivity";
	private static final boolean ENABLE_LOG = false;
	private static final String LOG_PATH = "Log";
	private static final int MAX_POWER_LEVEL = 300;


	private SoundPlay mSound;
	private List<String> TagList;
	private EditText editText;
	private Button btnset;
	private SeekBar seekBar;

	private int mOperationTime;
	private int mInventoryTime;
	private int mIdleTime;
	private int mPowerLevel;


	private boolean[] mFreqChanUses;
	private String[] mFreqChanNames;
	private RangeValue mPowerRange;
	private GlobalBandType mGlobalBand;

	private ATRfidReader mReader = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i(TAG, "Info OnCreate()");
		String appName = SysUtil.getAppName(this);

		TagList = new ArrayList<String>();

		mSound = new SoundPlay(getApplicationContext());


		mPowerRange = null;
		mGlobalBand = GlobalBandType.Korea;
		mPowerLevel = MAX_POWER_LEVEL;
		mOperationTime = 0;
		mInventoryTime = 0;
		mIdleTime = 0;
		mFreqChanNames = null;
		mFreqChanUses = null;


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

		btnset = (Button)findViewById(R.id.btnset);
		btnset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveOption();
			}
		});
		ATLog.i(TAG, "INFO. onCreate()");

		seekBar = (SeekBar)findViewById(R.id.seekBar);

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				try {
					mReader.setPower((seekBar.getProgress() + 1) * 10);

					Log.i(TAG, String.valueOf(mReader.getPower()));
				} catch (ATRfidReaderException e) {
					e.printStackTrace();
				}

			}
		});


		//initReader();
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//
		if ((keyCode == KeyEvent.KEYCODE_SOFT_RIGHT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT
				|| keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) && event.getRepeatCount() <= 0
				&& mReader.getAction() == ActionState.Stop && mReader.getState() == ConnectionState.Connected) {

			ATLog.i(TAG, "INFO. onKeyDown(%d, %d)", keyCode, event.getAction());

			startAction(true);

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_SOFT_RIGHT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT
				|| keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) && event.getRepeatCount() <= 0
				&& mReader.getAction() != ActionState.Stop && mReader.getState() == ConnectionState.Connected) {

			ATLog.i(TAG, "INFO. onKeyUp(%d, %d)", keyCode, event.getAction());

			stopAction();

			return true;
		}

		return super.onKeyUp(keyCode, event);
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
			//saveOption();
			TagList.clear();
			editText.setText("");
			startAction(true);
			break;
		case R.id.btnStop:
			stopAction();
			//mReader.disconnect();
			break;
		case R.id.btnset:
			saveOption();
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
	protected void startAction(boolean type) {

		ResultCode res;
		TagType tagType = TagType.Tag6C;//getTagType();

		if (type) {
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
				if(!TagList.contains(tag)) {
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


	private String getFreqString(int freq) {
		if (freq > 1000000000)
			return String.format(Locale.US, "%.1fGHz", (double) freq / 1000000000.0);
		else if (freq > 1000000)
			return String.format(Locale.US, "%.1fMHz", (double) freq / 1000000.0);
		else if (freq > 1000)
			return String.format(Locale.US, "%.1fKHz", (double) freq / 1000.0);
		return String.format(Locale.US, "%d", freq);
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

}
