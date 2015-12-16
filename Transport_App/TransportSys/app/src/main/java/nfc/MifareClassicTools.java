package nfc;

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

    public static void setNfcAdapter(NfcAdapter nfc){
        if(nfc!=null)
            nfcAdapter = nfc;
    }

    public static boolean isNfcEnable()
    {
        return nfcAdapter!=null && nfcAdapter.isEnabled();
    }

    public static boolean isTagExist()
    {
        return tag != null;
    }

    public static String readTagBlockASCII(Tag tag,int block)
    {
        String blockData = "";

        MifareClassic mfc = MifareClassic.get(tag);
        try {
            if(mfc!= null)
            {
                mfc.connect();
                int type = mfc.getType();
                int blockCount = mfc.getBlockCount();
                if (block < blockCount && type == MifareClassic.TYPE_CLASSIC)
                {
                    if (mfc.authenticateSectorWithKeyA(block, MifareClassic.KEY_DEFAULT))
                    {
                        Log.i(TAG,"Auth succeed");
                        blockData = NfcUtils.convertBinToASCII(mfc.readBlock(block)) +"\n";
                    }
                    else
                    {
                        Log.i(TAG,"Auth failed");
                        blockData = "";
                    }
                }
            }
        }
        catch (Exception e)
        {
            blockData="";
            Log.i(TAG,e.getMessage());
        }
        return blockData;
    }

    public String readSectorFromIntent(Intent intent,int Sector)
    {
        String blockData = "";
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        blockData = getM1SectorASCII(tagFromIntent, Sector);
        Log.i(TAG,"Sector:" + Sector +"\n" +blockData);
        return blockData;
    }

    public static String getTagUID(Tag tag){

        MifareClassic mfc = MifareClassic.get(tag);
        return NfcUtils.convertBinToASCII(mfc.getTag().getId());
    }

    public static String getUidFromIntent(Intent intent)
    {
        if(intent.getAction() != NfcAdapter.ACTION_TAG_DISCOVERED)
            return "";
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        return getTagUID(tagFromIntent);
    }

    protected static byte[][] getM1SectorByteArray(Tag tag, int sector){

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
                    for (int i=0;i<=3;i++)
                        sectorData[i] =mfc.readBlock(i);
                    return sectorData;
                }
            }
        }
        catch (Exception e)
        {
            sectorData = null;
            Log.i(TAG, e.getMessage());
        }
        return sectorData;
    }

    public static String getM1SectorASCII(Tag tag,int sector)
    {
        String sectorDataString = "";

        byte [][]  sectorDataByte = getM1SectorByteArray(tag, sector);
        if(sectorDataByte!=null)
        {
            for(int i=0;i<=3;i++)
                sectorDataString += NfcUtils.convertBinToASCII(sectorDataByte[i]);
        }
        return sectorDataString;
    }
}
