package com.headingwl.RfidManager;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.util.Log;

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

	public byte[] readTagBlockByte(Tag tag,int block) {

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
					if (mfc.authenticateSectorWithKeyB(block/4, MifareClassic.KEY_DEFAULT)) {
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

	public String readTagBlockASCII(Tag tag,int block) {
		String blockData = NfcUtils.convertBinToASCII(readTagBlockByte(tag, block));
		return blockData;
	}

	public String readSectorFromIntent(Intent intent,int Sector) {
		String blockData = "";
		Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		blockData = getM1SectorASCII(tagFromIntent, Sector);
		Log.i(TAG,"Sector:" + Sector +"\n" +blockData);
		return blockData;
	}

	public String getTagUID(Tag tag) {
		MifareClassic mfc = MifareClassic.get(tag);
		return NfcUtils.convertBinToASCII(mfc.getTag().getId());
	}

	public String getUidFromIntent(Intent intent) {
		if(intent.getAction() != NfcAdapter.ACTION_TAG_DISCOVERED)
			return "";
		Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		return getTagUID(tagFromIntent);
	}

	protected static byte[][] getM1SectorByteArray(Tag tag, int sector) {
		byte [][] sectorData = null;
		MifareClassic mfc = MifareClassic.get(tag);
		try {
			mfc.connect();
			int type = mfc.getType();
			int sectorCount = mfc.getSectorCount();
			if (sector< sectorCount && type == MifareClassic.TYPE_CLASSIC) {

				if (mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT)) {
					sectorData = new byte[4][16];
					for (int i=0; i<=3; i++)
						sectorData[i] =mfc.readBlock(i);
					return sectorData;
				}
			}
		} catch (Exception e) {
			sectorData = null;
			Log.i(TAG, e.getMessage());
		}
		return sectorData;
	}

	public String getM1SectorASCII(Tag tag,int sector) {
		String sectorDataString = "";
		byte [][]  sectorDataByte = getM1SectorByteArray(tag, sector);
		if(sectorDataByte!=null) {
			for(int i=0; i<=3; i++)
				sectorDataString += NfcUtils.convertBinToASCII(sectorDataByte[i]);
		}
		return sectorDataString;
	}

	public  boolean writeM1BlockByte(Tag tag,int block,byte[] blockDat)
	{
		Log.i(TAG,"writeM1BlockByte");
		MifareClassic mfc = MifareClassic.get(tag);
		try
		{
			if(mfc!=null){
				if(mfc.isConnected())
					mfc.close();
				mfc.connect();
				int type = mfc.getType();
				int blockCount = mfc.getBlockCount();
				if (block < blockCount && type == MifareClassic.TYPE_CLASSIC) {
					if (mfc.authenticateSectorWithKeyB(block/4, MifareClassic.KEY_DEFAULT)) {
						Log.i(TAG,"Auth succeed");
						mfc.writeBlock(block,blockDat);
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
