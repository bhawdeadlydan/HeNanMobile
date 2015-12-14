package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.Set;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by user on 12/15/2015.
 */
public class CheckByMatScanTagThread extends Thread{

    private String itemCode;
    private boolean isReading;
    private int expectedCnt;
    private int readedCnt;
    private ConnectServer connectServer;
    private RFIDService.Client client;
    private Set<String> readedBoxes;
    private Handler mHandler;


    public CheckByMatScanTagThread(String itemCode,boolean isReading,int expectedCnt, Handler mHandler) {
        this.itemCode = itemCode;
        this.isReading = isReading;
        this.expectedCnt = expectedCnt;
        connectServer=new ConnectServer();
        client = connectServer.openConnect();
        this.mHandler = mHandler;

    }

    @Override
    public void run() {

        while( isReading && readedCnt < expectedCnt ) {
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
        }

    }

    public void SetIsReading(Boolean f) {
        this.isReading = f;
    }

    public boolean isWantedItem(String epc) {
        try {
            Good good = client.getGoodByCNum(epc);
                if( good.getCode() == itemCode && !readedBoxes.contains(epc)) {
                    readedBoxes.add(epc);
                    return true;
                }
                return false;
        } catch (TException e) {
            e.printStackTrace();
        }
        return true;
    }
}
