package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class PutInStorageThread extends Thread {

    private String CNum;
    private Handler handler;

    private Good good;

    public PutInStorageThread(Handler handler,String CNum) {
        this.handler=handler;
        this.CNum=CNum;
    }

    @Override
    public void run() {
        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            good=client.getGoodByCNum(CNum);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=good;
        handler.sendMessage(msg);
    }
}
