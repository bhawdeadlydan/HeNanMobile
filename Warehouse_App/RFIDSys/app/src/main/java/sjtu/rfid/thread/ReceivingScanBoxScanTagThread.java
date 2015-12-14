package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.BaseExpandableListAdapter;

import java.util.Map;
import java.util.Set;

import sjtu.rfid.tools.ReceivingSheetsExpandableAdapter;
import sjtu.rfid.tools.ReceivingSheetsScanBoxExpandableAdapter;

/**
 * Created by user on 12/15/2015.
 */
public class ReceivingScanBoxScanTagThread extends Thread {

    private boolean isReading;
    private Map<String, Set<String>> mReceivingBoxesItemsList;
    private Handler mHandler;

    public ReceivingScanBoxScanTagThread(Map<String, Set<String>> mReceivingBoxesItemsList,boolean isReading, Handler mHandler)
    {
        this.isReading = isReading;
        this.mHandler = mHandler;
        this.mReceivingBoxesItemsList = mReceivingBoxesItemsList;
    }
    @Override
    public void run() {
        while( isReading ) {
            Message msg=mHandler.obtainMessage();
            msg.what = 1;
//            mHandler.sendMessage(msg);
        }
    }

    public boolean isReading() {
        return isReading;
    }

    public void setIsReading(boolean isReading) {
        this.isReading = isReading;
    }
}
