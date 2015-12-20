package nfc;

import android.nfc.Tag;
import android.util.Log;

import nfc.NfcDataType.*;

/**
 * Created by L on 2015/12/13.
 * fix some bugs / rebuild
 * add writeConsInf Par WorkTeam 2015/12/21
 */
public class NfcTask {
	public static nfc.MifareClassicTools mifareClassicTools;

	public static String TAG = "NfcTask";

	public enum NfcTaskType {
		WriteData,
		ReadData
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
		if(num >= 0) {
			isNfcTaskRun = false;
			nfcTaskNum = 0;
			nfcTaskList = new NfcTaskContext[num];
		}
		mifareClassicTools = new MifareClassicTools();
	}

	public boolean addNfcTask(NfcTaskType nfcTaskType,NfcTaskName nfcTaskName,NfcDataTypeBase nfcTaskData) {
		if(nfcTaskList != null && nfcTaskNum<nfcTaskList.length) {
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
		UID uid = new NfcDataType().new UID(mifareClassicTools.getM1TagUID(tag));
		Log.i(TAG, "readUID:" + uid);
		return uid;
	}

	public static ItemInf readItemInf(Tag tag) {

		ItemInf itemInf = new NfcDataType().new ItemInf("","",0,0);

		/* read ERPCode from block 1 */
		String s =  NfcUtils.binToString(mifareClassicTools.readM1TagBlockByte(tag, 1));
		s += NfcUtils.binToString(mifareClassicTools.readM1TagBlockByte(tag, 2));
		s = s.replace("\0", "");
		itemInf.ERPCode = s.equals("") ? "NULL" : s;
		/* read ERPCode end */

		/* read projectCode from block 4 */
		s = NfcUtils.binToString(mifareClassicTools.readM1TagBlockByte(tag, 4)).replace("\0","");
		itemInf.projectCode = s.equals("") ? "NULL": s;
		/* read projectCode end */

		/* read timeStamp from block 5 */
		s = NfcUtils.binToString(mifareClassicTools.readM1TagBlockByte(tag, 5)).replace("\0", "");
		itemInf.timestamp = s.equals("") ? 0 : Long.decode(s);
		/* read timeStamp end */

		return itemInf;
	}

	public static REQInf readREQInf(Tag tag) {

		REQInf reqInf = new NfcDataType().new REQInf("","","","","",0);

		try{
			byte[] bTmp = new byte[48];
 			/* read workTeam name from block 8-10 */
			byte [][] bWorkTeam =new byte[3][16];
			bWorkTeam[0] = mifareClassicTools.readM1TagBlockByte(tag, 8);
			bWorkTeam[1] = mifareClassicTools.readM1TagBlockByte(tag, 9);
			bWorkTeam[2] = mifareClassicTools.readM1TagBlockByte(tag, 10);
			for(int i =0;i<48 ;i++)
				bTmp[i] = bWorkTeam[i/16][i%16];
			reqInf.WorkTeam = new String(bTmp).replace("\0", "");
			/* read workTeam name end */

			/* read REQ & timeStamp from block 12 */
			bTmp = mifareClassicTools.readM1TagBlockByte(tag, 12);

			//REQ was stored in bytes 0-9 of the block 12("-REQ-" replaced with "E")
			reqInf.REQ = NfcUtils.convertBinToASCII(bTmp).substring(0,18).replace("E", "-REQ-");
			if(!reqInf.REQ.contains("-REQ-"))
				reqInf.REQ = "";

			//timeStamp was converted to byte Array stored in byte 12-15 of the block
			reqInf.timestamp = (long)(bTmp[12]&0xff)*256*256*256 + (long)(bTmp[13]&0xff)*256*256 + (long)(bTmp[14]&0xff)*256 +(long)(bTmp[15]&0xff);
			/* read REQ & timeStamp end */

			/* read checkPerson & reqPerson from block 13 */
			bTmp = mifareClassicTools.readM1TagBlockByte(tag, 13);
			String tmp = new String(bTmp,"GBK").replace("\0", "");

			reqInf.CheckPerson = tmp.substring(0, tmp.indexOf(','));// with split ","
			reqInf.ReqPerson  =tmp.substring(tmp.indexOf(',') + 1);
			/* read checkPerson & reqPerson end */
		}
		catch (Exception e)
		{
			Log.i(TAG,e.getMessage());
		}
		Log.i(TAG, "readREQInf:" + reqInf.toString());
		return reqInf;
	}

	public static TransInf readTransInf(Tag tag ) {

		TransInf transInf = new NfcDataType().new TransInf("","","",0,0,0);

		try {
			/* read transPerson from block 14 */
			byte[] bTmp = mifareClassicTools.readM1TagBlockByte(tag, 14);
			transInf.TransPerson = new String(bTmp, "GBK").replace("\0", "");
			/* read transPerson end */

			/* read transStation & location from block 16,17,18,20 */
			byte[][] block = new byte[4][16];
			block[0] = mifareClassicTools.readM1TagBlockByte(tag, 16);
			block[1] = mifareClassicTools.readM1TagBlockByte(tag, 17);
			block[2] = mifareClassicTools.readM1TagBlockByte(tag, 18);
			block[3] = mifareClassicTools.readM1TagBlockByte(tag, 20);
			bTmp = new byte[64];
			for (int i = 0; i < bTmp.length; i++)
				bTmp[i] = block[i / 16][i % 16];

			String TS_LOC = new String(bTmp, "GBK");
			transInf.TransStation = TS_LOC.substring(0, TS_LOC.indexOf(","));
			transInf.Location = TS_LOC.substring(TS_LOC.indexOf(",")+1);
			/* TransStation and Location End */

			/* read timeStamp from block 21 */
			bTmp = mifareClassicTools.readM1TagBlockByte(tag, 21);
			byte[] timeStamp = new byte[4];
			timeStamp[0] = bTmp[0];
			timeStamp[1] = bTmp[1];
			timeStamp[2] = bTmp[2];
			timeStamp[3] = bTmp[3];
			transInf.timestamp = NfcUtils.bytesTolong4(timeStamp);
			/* read timeStamp end */
		}
		catch (Exception e)
		{
			Log.i(TAG,e.getMessage());
		}
		Log.i(TAG,"readTransInf" + transInf.toString());
		return transInf;
	}

	public static ConsInf readConsInf(Tag tag) {

		ConsInf consInf = new NfcDataType().new ConsInf("","","",0,0,0);

		try {
			/* read consPerson & location from block 22,24,25,26 */
			byte[] bTmp = new byte[64];
			byte[][] block = new byte[4][16];
			block[0] = mifareClassicTools.readM1TagBlockByte(tag, 22);
			block[1] = mifareClassicTools.readM1TagBlockByte(tag, 24);
			block[2] = mifareClassicTools.readM1TagBlockByte(tag, 25);
			block[3] = mifareClassicTools.readM1TagBlockByte(tag, 26);
			//copy to one array to covert to string
			for (int i = 0; i < bTmp.length; i++)
				bTmp[i] = block[i / 16][i % 16];
			//convert to string and remove "\0"
			String CP_LOC = new String(bTmp,"GBK").replace("\0", "");

			consInf.ConsPerson = CP_LOC.substring(0, CP_LOC.indexOf(","));
			consInf.Location = CP_LOC.substring(CP_LOC.indexOf(",")+1);
			/* read consPerson & location end */

			/* read workTeam name from block 28,29,30 */
			byte [][] bWorkTeam =new byte[3][16];
			bWorkTeam[0] = mifareClassicTools.readM1TagBlockByte(tag, 28);
			bWorkTeam[1] = mifareClassicTools.readM1TagBlockByte(tag, 29);
			bWorkTeam[2] = mifareClassicTools.readM1TagBlockByte(tag, 30);
			for(int i =0;i<48 ;i++)
				bTmp[i] = bWorkTeam[i/16][i%16];
			consInf.WorkTeam = new String(bTmp).replace("\0", "");
			/* read workTeam name end */

			/* read timeStamp from block 32 */
			bTmp = mifareClassicTools.readM1TagBlockByte(tag, 32);
			byte[] timeStamp = new byte[16];
			timeStamp[0] = bTmp[0];
			timeStamp[1] = bTmp[1];
			timeStamp[2] = bTmp[2];
			timeStamp[3] = bTmp[3];
			consInf.timestamp = NfcUtils.bytesTolong4(timeStamp);
			/* read timeStamp end */
		}
		catch (Exception e)
		{
			Log.i(TAG,e.getMessage());
		}
		Log.i(TAG,"readConsInf:" + consInf.toString());
		return consInf;
	}

	public static boolean writeItemInf(Tag tag,ItemInf itemInf) {
		Log.i(TAG,"writeItemInf:" + itemInf.toString());
		return true;
	}

	public static boolean writeREQInf(Tag tag,REQInf reqInf) {
		Log.i(TAG,"writeREQInf:" + reqInf.toString());

		boolean ret;
		try {
			byte[] bTmp;
			/* write workTeam to block 8-10 */
			byte[][] WorkTeam = new byte[3][16];
			bTmp = reqInf.WorkTeam.getBytes();
			for (int i = 0, j = 0; j < 3 && i < bTmp.length; ) {
				WorkTeam[j][i % 16] = bTmp[i];
				if ((++i) % 16 == 0)
					j++;
			}
			ret = mifareClassicTools.writeM1BlockByte(tag, 8, WorkTeam[0]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 9, WorkTeam[1]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 10, WorkTeam[2]);
			/* write workTeam end */

			/* write REQ & timestamp to block 12 */
			byte[] REQ_T = new byte[16];
			//replace "-REQ-" to "E"  write to memory as hex
			bTmp = NfcUtils.convertASCIIToBin(reqInf.REQ.replace("-REQ-", "E"));
			for (int i = 0; i < bTmp.length && i < 9; i++)
				REQ_T[i] = bTmp[i];
			//timeStamp storage into the byte 12-14 of the block
			REQ_T[12] = (byte) (reqInf.timestamp / (256 * 256 * 256));
			REQ_T[13] = (byte) (reqInf.timestamp / (256 * 256));
			REQ_T[14] = (byte) (reqInf.timestamp / 256);
			REQ_T[15] = (byte) (reqInf.timestamp);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 12, REQ_T);
			/* write REQ & timestamp end */

			/* write CheckPerson & ReqPerson to block 13 */
			bTmp = (reqInf.CheckPerson + "," + reqInf.ReqPerson).getBytes("GBK");
			byte[] CP_RP = new byte[16];
			for (int i = 0; i < bTmp.length && i < 16; i++)
				CP_RP[i] = bTmp[i];
			ret &= mifareClassicTools.writeM1BlockByte(tag, 13, CP_RP);
			/* write CheckPerson & ReqPerson end */
		}
		catch (Exception e)
		{
			ret = false;
			Log.i(TAG,e.getMessage());
		}

		return ret; /* return false if there was any write error  */
	}

	public static boolean writeTransInf(Tag tag,TransInf transInf) {
		Log.i(TAG,"writeTransInf:" + transInf.toString());

		boolean ret;
		try{
			byte[] bTmp;
			/* write transPerson to block 14 */
			byte[] transPerson = new byte[16];
			bTmp = transInf.TransPerson.getBytes("GBK");
			for (int i = 0; i < bTmp.length && i < 16; i++)
				transPerson[i] = bTmp[i];
			ret = mifareClassicTools.writeM1BlockByte(tag, 14, transPerson);
			/* write transPerson end */

			/* write transStation and location to block 16,17,18,20*/
			byte[][] TP_LOC = new byte[4][16];
			// connect transStation and location with split ","
			bTmp = (transInf.TransStation + "," + transInf.Location).getBytes("GBK");
			for (int i = 0, j = 0; j < 4 && i < bTmp.length && i < 64; ) {
				TP_LOC[j][i % 16] = bTmp[i];
				if ((++i) % 16 == 0)
					j++;
			}
			ret &= mifareClassicTools.writeM1BlockByte(tag, 16, TP_LOC[0]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 17, TP_LOC[1]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 18, TP_LOC[2]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 20, TP_LOC[3]);
			/* write transStation and location End */

			/* write timeStamp to block 21 */
			bTmp = NfcUtils.long4Tobytes(transInf.timestamp);
			byte[] timeStamp = new byte[16];
			for (int i = 0; i < bTmp.length && i < bTmp.length; i++)
				timeStamp[i] = bTmp[i];
			ret &= mifareClassicTools.writeM1BlockByte(tag, 21, timeStamp);
			/* write timeStamp End */
		}
		catch (Exception e)
		{
			ret = false;
			Log.i(TAG,e.getMessage());
		}

		return ret; /* return false if there was any write error  */
	}

	public static boolean writeConsInf(Tag tag,ConsInf consInf) {
		Log.i(TAG,"writeConsInf:" + consInf.toString());

		boolean ret;
		try {

			byte[] bTmp;

			/* write consPerson and location to block 22,24,25,26 */
			byte[][] CP_LOC = new byte[4][16];
			// connect consPerson and location with split ","
			bTmp = (consInf.ConsPerson + "," + consInf.Location).getBytes("GBK");
			for (int i = 0, j = 0; j < 4 && i < bTmp.length; ) {
				CP_LOC[j][i % 16] = bTmp[i];
				if ((++i) % 16 == 0)
					j++;
			}
			ret  = mifareClassicTools.writeM1BlockByte(tag, 22, CP_LOC[0]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 24, CP_LOC[1]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 25, CP_LOC[2]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 26, CP_LOC[3]);
			/* write consPerson and location end */

			/* write workTeam to block 28,28,30 */
			byte[][] WorkTeam = new byte[3][16];
			bTmp = consInf.WorkTeam.getBytes();
			for (int i = 0, j = 0; j < 3 && i < bTmp.length; ) {
				WorkTeam[j][i % 16] = bTmp[i];
				if ((++i) % 16 == 0)
					j++;
			}
			ret &= mifareClassicTools.writeM1BlockByte(tag, 28, WorkTeam[0]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 29, WorkTeam[1]);
			ret &= mifareClassicTools.writeM1BlockByte(tag, 30, WorkTeam[2]);
			/* write workTeam end */

			/* write timeStamp to block 32 */
			bTmp = NfcUtils.long4Tobytes(consInf.timestamp);
			byte[] timeStamp = new byte[16];
			for (int i = 0; i < bTmp.length && i < 16; i++)
				timeStamp[i] = bTmp[i];
			ret &= mifareClassicTools.writeM1BlockByte(tag, 32, timeStamp);
			/* write timeStamp end */
		}
		catch (Exception e)
		{
			ret = false;
			Log.i(TAG,e.getMessage());
		}
		return ret;
	}

}
