package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/12.
 */
public class ReceivingSubmitThread extends Thread {


    private  String sheetCode;
    private Handler handler;

    private boolean result;

    public ReceivingSubmitThread(String sheetCode,Handler handler){
        this.sheetCode=sheetCode;
        this.handler=handler;
    }

    @Override
    public void run() {

        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            result=client.confirmReceiving(sheetCode);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=result;
        handler.sendMessage(msg);
    }
}
