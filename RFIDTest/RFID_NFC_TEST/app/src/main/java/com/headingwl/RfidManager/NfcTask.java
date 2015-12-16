package com.headingwl.RfidManager;

import android.nfc.Tag;
import android.util.Log;

import com.headingwl.RfidManager.NfcDataType.*;
/**
 * Created by L on 2015/12/13.
 */
public class NfcTask {
	public static MifareClassicTools mifareClassicTools;

	public static String TAG = "NfcTask";

	public enum NfcTaskType {
		WriteData,
		ReadData;
	}

	public  enum NfcTaskName {
		UID,
		ItemInf,
		REQInf,
		TransInf,
		ConsInf
	}

	public class NfcTaskContext {
		public NfcTaskType nfcTaskType;
		public NfcTaskName nfcTaskName;
		public NfcDataTypeBase nfcTaskData;

		protected NfcTaskContext(NfcTaskType nfcTaskType,NfcTaskName nfcTaskName,NfcDataTypeBase nfcDataTypeBase) {
			this.nfcTaskType = nfcTaskType;
			this.nfcTaskName = nfcTaskName;
			this.nfcTaskData = nfcDataTypeBase;
		}

	}

	protected int nfcTaskNum;
	public boolean isNfcTaskRun;
	NfcTaskContext[] nfcTaskList;

	protected NfcTask(int num) {
		if(num>=0) {
			isNfcTaskRun = false;
			nfcTaskNum = 0;
			nfcTaskList = new NfcTaskContext[num];
		}
		mifareClassicTools = new MifareClassicTools();
	}

	public boolean addNfcTask(NfcTaskType nfcTaskType,NfcTaskName nfcTaskName,NfcDataTypeBase nfcTaskData) {
		if(nfcTaskList!=null && nfcTaskNum<nfcTaskList.length) {
			nfcTaskList[nfcTaskNum] = new NfcTaskContext(nfcTaskType,nfcTaskName,nfcTaskData);
			nfcTaskNum++;
			return true;
		}
		return false;
	}

	public boolean clearNfcTask() {
		nfcTaskNum = 0;
		isNfcTaskRun = false;
		return true;
	}

	public static UID readUID(Tag tag) {
		UID uid = new NfcTestData().uid;
		return uid;
	}

	public static ItemInf readItemInf(Tag tag) {
		ItemInf itemInf = new NfcTestData().itemInf;

		String s =  NfcUtils.binToString(mifareClassicTools.readTagBlockByte(tag, 1));
		s += NfcUtils.binToString(mifareClassicTools.readTagBlockByte(tag, 2));

		s = s.replace("\0", "");
		itemInf.ERPCode = s.equals("") ? "NULL" : s;

		s = NfcUtils.binToString(mifareClassicTools.readTagBlockByte(tag, 4)).replace("\0","");

		itemInf.projectCode = s.equals("") ? "NULL": s;

		s = NfcUtils.binToString(mifareClassicTools.readTagBlockByte(tag, 5)).replace("\0", "");

		itemInf.timestamp = s.equals("") ? 0 : Long.decode(s);

		return itemInf;
	}

	public static REQInf readREQInf(Tag tag) {
		REQInf reqInf  = new NfcTestData().reqInf;
		return reqInf;
	}

	public static TransInf readTransInf(Tag tag ) {
		TransInf transInf = new NfcTestData().transInf;
		return transInf;
	}

	public static ConsInf readConsInf(Tag tag) {
		ConsInf consInf = new NfcTestData().consInf;
		return consInf;
	}

	public static boolean writeItemInf(Tag tag,ItemInf itemInf) {
		Log.i(TAG,"Write:" + itemInf.toString());
		return true;
	}

	public static boolean writeREQInf(Tag tag,REQInf reqInf) {
		Log.i(TAG,"Write:" + reqInf.toString());
		return true;
	}

	public static boolean writeTransInf(Tag tag,TransInf transInf) {
		Log.i(TAG,"Write:" + transInf.toString());
		return true;
	}

	public boolean writeConsInf(Tag tag,ConsInf consInf) {
		Log.i(TAG,"Write:" + consInf.toString());
		return true;
	}

}
