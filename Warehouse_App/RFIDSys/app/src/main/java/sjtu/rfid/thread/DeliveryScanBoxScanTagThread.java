package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import rfid.service.Good;
import rfid.service.RFIDClient;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by user on 12/15/2015.
 */
public class DeliveryScanBoxScanTagThread extends Thread {

    private boolean isReading;
    private Map<String, Set<String>> mDeliveryBoxesItemsList;
    private Handler mHandler;
    private ConnectServer connectServer;
    private RFIDService.Client client;
    private List<Good> list;
    private Set<String> boxSet;

    public DeliveryScanBoxScanTagThread(Map<String, Set<String>> mDeliveryBoxesItemsList,boolean isReading, Handler mHandler)
    {
        this.isReading = isReading;
        this.mHandler = mHandler;
        this.mDeliveryBoxesItemsList = mDeliveryBoxesItemsList;
        connectServer=new ConnectServer();
        client = connectServer.openConnect();
    }

    @Override
    public void run() {
        while( isReading ) {
            Message msg=mHandler.obtainMessage();
            msg.what = 1;
//            mHandler.sendMessage(msg);
        }
        connectServer.closeConnect();
    }

    public boolean isReading() {
        return isReading;
    }

    public void setIsReading(boolean isReading) {
        this.isReading = isReading;
    }

    public boolean judgeHasReaded(String epc) {

        try {
            Good good = client.getGoodByCNum(epc);
            String matCode = good.getCode();
            if( (mDeliveryBoxesItemsList.containsKey(matCode)) && !mDeliveryBoxesItemsList.get(matCode).contains(epc) ) {
                mDeliveryBoxesItemsList.get(matCode).add(epc);
                return true;
            } else {
                return false;
            }
        } catch (TException e) {
            e.printStackTrace();
        }
        return false;
    }
}
