package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by user on 12/15/2015.
 */
public class PrintThread extends Thread {

    String sheetCode;
    Handler mHandler;
    boolean result;

    public PrintThread(String sheetCode, Handler mHandler) {
        this.sheetCode = sheetCode;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        Message msg=mHandler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try {
            result = client.printTag(sheetCode);

        } catch (TException e) {
            e.printStackTrace();
        }
        if( result )
            msg.what=1;
        else
            msg.what=0;
        mHandler.sendMessage(msg);
    }

}
