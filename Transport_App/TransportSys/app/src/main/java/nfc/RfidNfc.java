package nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.*;
import android.content.Context;
import android.nfc.tech.MifareClassic;
import android.util.Log;

import nfc.NfcDataType.*;

import nfc.NfcTask.*;

import java.lang.ref.WeakReference;

/**
 * Created by L on 2015/12/9.
 */

public class RfidNfc implements NfcAdapter.ReaderCallback{

    private String version = "1.0.0";
    private static String TAG = "RfidNfc";
    PendingIntent mPendingIntent;
    NfcAdapter nfcAdapter;
    public NfcTask nfcTask;

    IntentFilter[] mFilters;
    String[][] mTechLists;

    public static int READER_FLAGS =
            NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;

    public WeakReference<TagUidCallBack> mTagUidCallBack;

    public interface TagUidCallBack{
        /* */
        void onTagUidGet(NfcDataTypeBase uid);          /*获取标签UID*/
        void onBlockGet(String block);                  /*获取数据块*/
        void onSectorGet(String sector);                /*获取扇区*/

        void getItemInf(NfcDataTypeBase ItemInf);       /*获得物品信息*/
        void getREQInf(NfcDataTypeBase REQInf);         /*获得申领单/出库信息*/
        void getTransInf(NfcDataTypeBase TransInf);     /*获得运输信息*/
        void getConsInf(NfcDataTypeBase ConsInf);       /*获得施工点信息*/
                                                        /*标签读取返回信息*/
        void onReadTag(NfcTaskName nfcTaskName, NfcDataTypeBase NfcDataTypeBase);
                                                        /*写标签任务完成*/
        void onWriteTaskEnd(NfcTaskName nfcTaskName, NfcDataTypeBase nfcDataTypeBase, boolean ret);

    }

    public RfidNfc(TagUidCallBack tagUidCallBack) {
        mTagUidCallBack = new WeakReference<TagUidCallBack>(tagUidCallBack);
    }

    public String GetVersion()
    {
        return this.version;
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        Log.i(TAG, "New tag discovered");
        MifareClassicTools.tag = tag;
        processTask(tag);
    }

    public void enableReaderMode(Activity activity )
    {
        Log.i(TAG, "enabling reader mode");
        if (nfcAdapter != null) {
            nfcAdapter.enableReaderMode(activity, this, READER_FLAGS, null);
        }
    }

    public void onResume(Activity activity)
    {

        if(nfcAdapter!= null) {
            enableForefroundDispatch(activity);
            enableReaderMode(activity);
            MifareClassicTools.setNfcAdapter(nfcAdapter);
        }

    }

    public void onPause(Activity activity) {
        if(nfcAdapter!=null) {
            disableForefroundDispatch(activity);
            disableReaderMode(activity);
        }
    }

    public void disableReaderMode(Activity activity) {
        Log.i(TAG, "Disabling reader mode");
        if (nfcAdapter != null) {
            nfcAdapter.disableReaderMode(activity);
        }
    }

    public boolean hfInit(Context context)
    {
        mPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        mFilters = new IntentFilter[]{tagDetected};
        mTechLists = new String[][]{
                        //new String[]{ Ndef.class.getName()},
                        new String[]{MifareClassic.class.getName()},
                        //new String[]{NdefFormatable.class.getName()}
                };

        nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        if(nfcAdapter == null)
        {
            //Toast.makeText(context,"设备不支持NFC！",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"设备不支持NFC！");
            return false;
        }
        else if(!nfcAdapter.isEnabled())
        {
            //Toast.makeText(context,"请先打开NFC功能！",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"请先打开NFC功能！");
            return false;
        }
        //Toast.makeText(context,"NFC初始化成功！", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "NFC初始化成功！");
        MifareClassicTools.setNfcAdapter(nfcAdapter);
        nfcTask = new NfcTask(10);
        return true;
    }


    public void processTask(Tag tag) {

        if(tag != null)
            tag = MifareClassicTools.tag;
        NfcTestData nfcTestData = new NfcTestData();
        try
        {
            while (nfcTask.nfcTaskNum > 0) {
            nfcTask.isNfcTaskRun = true;
            switch (nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskType) {
                case WriteData:
                    switch (nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName) {
                        case ItemInf:
                            mTagUidCallBack.get().onWriteTaskEnd(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName,
                                            nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData,
                                                nfcTask.writeItemInf(tag, (ItemInf) nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData));
                            break;
                        case REQInf:
                            mTagUidCallBack.get().onWriteTaskEnd(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName,
                                            nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData,
                                                nfcTask.writeREQInf(tag, (REQInf) nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData));
                            break;
                        case TransInf:
                            mTagUidCallBack.get().onWriteTaskEnd(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName,
                                            nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData,
                                                nfcTask.writeTransInf(tag, (TransInf) nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData));
                            break;
                        case ConsInf:
                            mTagUidCallBack.get().onWriteTaskEnd(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName,
                                            nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData,
                                                nfcTask.writeConsInf(tag, (ConsInf) nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskData));
                            break;
                    }
                    break;
                case ReadData:
                    switch (nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskType) {
                        case ReadData:
                            switch (nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName) {
                                case UID:
                                    mTagUidCallBack.get().onReadTag(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName, NfcTask.readUID(tag));
                                    break;
                                case ItemInf:
                                    mTagUidCallBack.get().onReadTag(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName, NfcTask.readItemInf(tag));
                                    break;
                                case REQInf:
                                    mTagUidCallBack.get().onReadTag(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName, NfcTask.readREQInf(tag));
                                    break;
                                case TransInf:
                                    mTagUidCallBack.get().onReadTag(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName, NfcTask.readTransInf(tag));
                                    break;
                                case ConsInf:
                                    mTagUidCallBack.get().onReadTag(nfcTask.nfcTaskList[nfcTask.nfcTaskNum - 1].nfcTaskName, NfcTask.readConsInf(tag));
                                    break;
                            }
                            break;
                    }
            }
            nfcTask.isNfcTaskRun = false;
            nfcTask.nfcTaskNum = nfcTask.nfcTaskNum > 0 ? nfcTask.nfcTaskNum-1 : 0;
        }
        }
        catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    public void enableForefroundDispatch(Activity activity)
    {
        if(nfcAdapter != null)
            nfcAdapter.enableForegroundDispatch(activity, mPendingIntent, mFilters, mTechLists);
    }

    public void disableForefroundDispatch(Activity activity)
    {
        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(activity);
    }

}
