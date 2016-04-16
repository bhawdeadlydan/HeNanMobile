package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.rfidsys.CheckByPosActivity;
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
            CheckItem item = new CheckItem(g,epc);
            msg.what = 3;
            msg.obj = item;
            mHandler.sendMessage(msg);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            server.closeConnect();
        }
    }

    public class CheckItem {
        public Good g;
        public String epc;
        public CheckItem(Good good, String epcstr) {
            this.g = good;
            this.epc = epcstr;
        }
    }
}
