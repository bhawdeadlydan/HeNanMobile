package nfc;

import android.nfc.Tag;
import android.util.Log;
import nfc.NfcDataType.*;
/**
 * Created by L on 2015/12/13.
 */
public class NfcTask {

    public static String TAG = "NfcTask";

    public enum NfcTaskType{
        WriteData,
        ReadData;
    }

    public  enum NfcTaskName{
        UID,
        ItemInf,
        REQInf,
        TransInf,
        ConsInf
    }

    public class NfcTaskContext{
        public NfcTaskType nfcTaskType;
        public NfcTaskName nfcTaskName;
        public NfcDataTypeBase nfcTaskData;

        //public String nfcTaskData;

        protected NfcTaskContext(NfcTaskType nfcTaskType,NfcTaskName nfcTaskName,NfcDataTypeBase nfcDataTypeBase)
        {
            this.nfcTaskType = nfcTaskType;
            this.nfcTaskName = nfcTaskName;
            this.nfcTaskData = nfcDataTypeBase;
        }

    }

    protected int nfcTaskNum;
    public boolean isNfcTaskRun;

    NfcTaskContext[] nfcTaskList;

    protected NfcTask(int num) {

        if(num>=0)
        {
            isNfcTaskRun = false;
            nfcTaskNum = 0;
            nfcTaskList = new NfcTaskContext[num];
        }
    }

    public boolean addNfcTask(NfcTaskType nfcTaskType,NfcTaskName nfcTaskName,NfcDataTypeBase nfcTaskData){
        if(nfcTaskList!=null && nfcTaskNum<nfcTaskList.length){
            nfcTaskList[nfcTaskNum] = new NfcTaskContext(nfcTaskType,nfcTaskName,nfcTaskData);
            nfcTaskNum++;
            return true;
        }
        return false;
    }

    public boolean clearNfcTask(){
        nfcTaskNum = 0;
        isNfcTaskRun = false;
        return true;
    }

    public static UID readUID(Tag tag)
    {
        UID uid = new NfcTestData().uid;
        return uid;

    }

    public static ItemInf readItemInf(Tag tag)
    {
        ItemInf itemInf = new NfcTestData().itemInf;
        return itemInf;

    }

    public static REQInf readREQInf(Tag tag)
    {
        REQInf reqInf  = new NfcTestData().reqInf;
        return reqInf;
    }

    public static TransInf readTransInf(Tag tag )
    {
        TransInf transInf = new NfcTestData().transInf;
        return transInf;
    }

    public static ConsInf readConsInf(Tag tag)
    {
        ConsInf consInf = new NfcTestData().consInf;
        return consInf;
    }

    public static boolean writeItemInf(Tag tag,ItemInf itemInf)
    {
        Log.i(TAG,"Write:" + ((ItemInf)itemInf).toString());
        return true;
    }

    public static boolean writeREQInf(Tag tag,REQInf reqInf)
    {
        Log.i(TAG,"Write:" + reqInf.toString());
        return true;
    }

    public static boolean writeTransInf(Tag tag,TransInf transInf)
    {
        Log.i(TAG,"Write:" + transInf.toString());
        return true;
    }

    public boolean writeConsInf(Tag tag,ConsInf consInf)
    {
        Log.i(TAG,"Write:" + consInf.toString());
        return true;
    }

}
