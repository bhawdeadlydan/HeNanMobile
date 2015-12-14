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
    public PutInStorageScanBoxThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run(){
        Message msg = handler.obtainMessage();
        msg.what = 1;
        String CNum = scan();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
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
