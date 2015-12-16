package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.ASN;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class ReceivingSheetsThread extends Thread {

    private Handler handler;

    private List<ASN> asnList;

    public ReceivingSheetsThread(Handler handler){
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            asnList=client.getReceivingSheets();
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=asnList;
        handler.sendMessage(msg);
    }
}
