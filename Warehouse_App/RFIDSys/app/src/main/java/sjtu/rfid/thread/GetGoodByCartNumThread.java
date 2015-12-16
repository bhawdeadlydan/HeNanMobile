package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/17.
 */
public class GetGoodByCartNumThread extends Thread {

    String epc;
    Handler mHandler;
    ConnectServer server;
    RFIDService.Client client;

    public GetGoodByCartNumThread(String epc, Handler mHandler) {
        this.epc = epc;
        this.mHandler = mHandler;
    }


    @Override
    public void run() {
        server = new ConnectServer();
        client = server.openConnect();
        try
        {
            Good g = client.getGoodByCNum(epc);
            Message msg = mHandler.obtainMessage();
            msg.what = 3;
            msg.obj = g;
            mHandler.sendMessage(msg);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            server.closeConnect();
        }
    }
}
