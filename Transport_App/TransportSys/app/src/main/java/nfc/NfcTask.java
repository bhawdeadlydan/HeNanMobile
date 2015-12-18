package nfc;

import android.nfc.Tag;
import android.util.Log;

import nfc.NfcDataType.*;
import nfc.MifareClassicTools.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;

/**
 * Created by L on 2015/12/13.
 */
public class NfcTask {
	public static nfc.MifareClassicTools mifareClassicTools;

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

	public static REQInf readREQInf(Tag tag) throws UnsupportedEncodingException {
		REQInf reqInf  = new NfcTestData().reqInf;

		byte [][] block =new byte[3][16];
		byte [] c = new byte[48];
		block[0] = mifareClassicTools.readTagBlockByte(tag, 8);
		block[1] = mifareClassicTools.readTagBlockByte(tag, 9);
		block[2] = mifareClassicTools.readTagBlockByte(tag, 10);
		for(int i =0;i<48 ;i++)
			c[i] = block[i/16][i%16];
		reqInf.WorkTeam = new String(c).replace("\0", "");

		block[0] = mifareClassicTools.readTagBlockByte(tag, 12);

		reqInf.CheckPerson = new String(block[0]).replace("\0", "");
		reqInf.REQ = NfcUtils.convertBinToASCII(block[0]).substring(0,18).replace("E", "-REQ-");

		reqInf.timestamp = (long)(block[0][12]&0xff)*256*256*256 + (long)(block[0][13]&0xff)*256*256 + (long)(block[0][14]&0xff)*256 +(long)(block[0][15]&0xff) ;

		block[0] = mifareClassicTools.readTagBlockByte(tag, 13);
		String tmp = new String(block[0],"GBK").replace("\0", "");
		reqInf.CheckPerson = tmp.substring(0,tmp.indexOf(','));
		reqInf.ReqPerson  =tmp.substring(tmp.indexOf(',')+1);
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

	public static boolean writeREQInf(Tag tag,REQInf reqInf) throws UnsupportedEncodingException {
		Log.i(TAG,"Write:" + reqInf.toString());

		/*
		reqInf.WorkTeam="平顶山分公司\\网络部\\工程建设中心";
		reqInf.CheckPerson = "复核人";
		reqInf.ReqPerson  ="申请人";
		reqInf.REQ = "2524-REQ-2015110000012";
		reqInf.timestamp = 1450085000;
		*/

		reqInf.REQ = reqInf.REQ.replace("-REQ-", "E");
		String tmp =reqInf.CheckPerson + "," + reqInf.ReqPerson;
		byte[] bREQ = NfcUtils.convertASCIIToBin(reqInf.REQ);

		byte [] dCP = tmp.getBytes("GBK");
		byte[] bTmp = new byte[16];

		for(int i=0;i<bREQ.length&&i<9;i++)
			bTmp[i]=bREQ[i];

		bTmp[12] = (byte)(reqInf.timestamp/(256*256*256));
		bTmp[13] = (byte)(reqInf.timestamp/(256*256));
		bTmp[14] = (byte)(reqInf.timestamp/256);
		bTmp[15] = (byte)(reqInf.timestamp);

		mifareClassicTools.writeM1BlockByte(tag, 12, bTmp);

		bTmp = new byte[16];
		for(int i=0;i<dCP.length&i<16;i++)
			bTmp[i] = dCP[i];

		mifareClassicTools.writeM1BlockByte(tag, 13, bTmp);

/******************************************************/
		byte [][] b = new byte[3][16];
		byte[] c = reqInf.WorkTeam.getBytes();
		for(int i =0,j=0;i<c.length;){
			b[j][i%16] = c[i];
			if((++i)%16 == 0)
				j++;
		}
		mifareClassicTools.writeM1BlockByte(tag, 8, b[0]);
		mifareClassicTools.writeM1BlockByte(tag,9,b[1]);
		mifareClassicTools.writeM1BlockByte(tag,10,b[2]);
/******************************************************/
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
