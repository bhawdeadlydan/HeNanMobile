package com.headingwl.sjtu.rfid_uhf_i2000_test.uti;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.headingwl.sjtu.rfid_uhf_i2000_test.R;

public class SoundPlay {

	private SoundPool mSound;
	private int mSuccess;
	private int mFail;

	public SoundPlay(Context context) {
		mSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		mSuccess = mSound.load(context, R.raw.success, 1);
		mFail = mSound.load(context, R.raw.fail, 1);
	}

	public void playSuccess() {
		mSound.play(mSuccess, 1, 1, 0, 0, 1);
	}

	public void playFail() {
		mSound.play(mFail, 1, 1, 0, 0, 1);
	}
}
