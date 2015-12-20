package nfc;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.util.Log;

import java.io.IOException;

/**
 * Created by L on 2015/12/14.
 */
public class MifareClassicTools {
	private static String TAG = "MifareClassicTools";

	public static NfcAdapter nfcAdapter;
	public static Tag tag;

	public void setNfcAdapter(NfcAdapter nfc) {
		if(nfc!=null)
			nfcAdapter = nfc;
	}

	protected MifareClassicTools(){

	}

	public boolean isNfcEnable() {
		return nfcAdapter!=null && nfcAdapter.isEnabled();
	}

	public boolean isTagExist() {
		return tag != null;
	}

	public byte[] readM1TagBlockByte(Tag tag,int block) {

		byte[] blockData = null;
		MifareClassic mfc = MifareClassic.get(tag);
		try {
			if(mfc!= null) {
				if(mfc.isConnected())
					mfc.close();
				mfc.connect();
				int type = mfc.getType();
				int blockCount = mfc.getBlockCount();
				if (block < blockCount && type == MifareClassic.TYPE_CLASSIC) {
					if (mfc.authenticateSectorWithKeyA(block/4, MifareClassic.KEY_DEFAULT)) {
						Log.i(TAG,"Auth succeed");
						blockData = mfc.readBlock(block);
					} else {
						Log.i(TAG,"Auth failed");
					}
				}
				mfc.close();
			}
		} catch (Exception e) {
			Log.i(TAG,e.getMessage());
		}
		return blockData;
	}

	public String readM1TagBlockASCII(Tag tag,int block) {
		return NfcUtils.convertBinToASCII(readM1TagBlockByte(tag, block));
	}

	public String readM1SectorASCIIFromIntent(Intent intent,int Sector) {
		Log.i(TAG,"readM1SectorASCIIFromIntent");
		if(intent.getAction() != NfcAdapter.ACTION_TAG_DISCOVERED)
			return "";
		String blockData = "";
		Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		blockData = readM1SectorASCII(tagFromIntent, Sector);
		return blockData;
	}

	public String getM1TagUID(Tag tag) {
		MifareClassic mfc = MifareClassic.get(tag);
		String uid = NfcUtils.convertBinToASCII(mfc.getTag().getId());
		Log.i(TAG,"getM1TagUID:" + uid);
		return uid;
	}

	public String getM1UidFromIntent(Intent intent) {
		Log.i(TAG,"getM1UidFromIntent");
		if(intent.getAction() != NfcAdapter.ACTION_TAG_DISCOVERED)
			return "";
		Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		return getM1TagUID(tagFromIntent);
	}

	public byte[][] readM1SectorByteArray(Tag tag, int sector) {
		Log.i(TAG,"getM1SectorByteArray");
		byte [][] sectorData = null;
		MifareClassic mfc = MifareClassic.get(tag);
		try {
			mfc.connect();
			int type = mfc.getType();
			int sectorCount = mfc.getSectorCount();
			if (sector< sectorCount && type == MifareClassic.TYPE_CLASSIC)
			{
				if (mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT)) {
					sectorData = new byte[4][16];
					for (int i=0; i<=3; i++)
						sectorData[i] =mfc.readBlock(i);
				}
			}
			mfc.close();
		} catch (Exception e) {
			sectorData = null;
			Log.i(TAG, e.getMessage());
		}
		return sectorData;
	}

	public String readM1SectorASCII(Tag tag,int sector) {
		String sectorDataString = "";
		byte [][]  sectorDataByte = readM1SectorByteArray(tag, sector);
		if(sectorDataByte!=null) {
			for(int i=0; i<=3; i++)
				sectorDataString += NfcUtils.convertBinToASCII(sectorDataByte[i]);
		}
		Log.i(TAG,"readM1SectorASCII:" + sector +"\n" +sectorDataString);
		return sectorDataString;
	}


	public  boolean writeM1BlockByte(Tag tag,int block,byte[] blockDat)
	{
		Log.i(TAG,"writeM1BlockByte");
		MifareClassic mfc = MifareClassic.get(tag);
		try
		{
			if(mfc!=null){
				if(mfc.isConnected())	/* maybe it's redundant*/
					mfc.close();
				mfc.connect();
				int type = mfc.getType();
				int blockCount = mfc.getBlockCount();
				if (block < blockCount && type == MifareClassic.TYPE_CLASSIC) {
					if (mfc.authenticateSectorWithKeyA(block/4, MifareClassic.KEY_DEFAULT)) {
						Log.i(TAG,"Auth succeed");
						mfc.writeBlock(block,blockDat);
						mfc.close();
						return true;
					}
					else {
						Log.i(TAG,"Auth failed");
					}
				}
				mfc.close();
			}
		}
		catch(Exception e)
		{
			Log.i(TAG, e.getMessage());
		}
		return false;
	}

	public boolean writeM1SectorByte(Tag tag,int sector,byte[][] blockDat)
	{
		Log.i(TAG,"writeM1SectorByte");
		MifareClassic mfc = MifareClassic.get(tag);
		try
		{
			if(mfc!=null){
				if(mfc.isConnected())
					mfc.close();
				mfc.connect();
				int type = mfc.getType();
				int sectorCount = mfc.getSectorCount();
				if (sector < sectorCount && type == MifareClassic.TYPE_CLASSIC) {
					if (mfc.authenticateSectorWithKeyB(sector, MifareClassic.KEY_DEFAULT)) {
						Log.i(TAG, "Auth succeed");
						mfc.writeBlock(sector * 4, blockDat[0]);
						mfc.writeBlock(sector * 4 + 1,blockDat[0]);
						mfc.writeBlock(sector * 4 + 2,blockDat[0]);
					} else {
						Log.i(TAG,"Auth failed");
					}
				}
				mfc.close();
			}
		}
		catch(Exception e)
		{
			Log.i(TAG, e.getMessage());
			return false;
		}
		return true;
	}
}
