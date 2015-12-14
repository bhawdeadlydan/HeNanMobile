package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by user on 12/15/2015.
 */
public class PutInStorageScanBoxThread extends Thread {
    private Handler handler;
    private ConnectServer connectServer;
    RFIDService.Client client;
    public PutInStorageScanBoxThread(Handler handler) {
        this.handler = handler;
        connectServer=new ConnectServer();
        client = connectServer.openConnect();
    }

    @Override
    public void run(){
        Message msg = handler.obtainMessage();
        msg.what = 1;
        String CNum = scan();
        try{
            Good good = client.getGoodByCNum(CNum);
            msg.obj = good;
            handler.sendMessage(msg);
        }catch(TException e){
            msg.what=-1;
            e.printStackTrace();
        }
        finally {
            connectServer.closeConnect();
        }
    }

    public String scan(){
        String epc = "";
        return epc;
    }
}
