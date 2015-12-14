package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import java.util.Map;
import java.util.Set;

/**
 * Created by user on 12/15/2015.
 */
public class DeliveryScanBoxScanTagThread extends Thread {

    private boolean isReading;
    private Map<String, Set<String>> mDeliveryBoxesItemsList;
    private Handler mHandler;

    public DeliveryScanBoxScanTagThread(Map<String, Set<String>> mDeliveryBoxesItemsList,boolean isReading, Handler mHandler)
    {
        this.isReading = isReading;
        this.mHandler = mHandler;
        this.mDeliveryBoxesItemsList = mDeliveryBoxesItemsList;
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
