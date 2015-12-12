package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/12.
 */
public class DeliverySubmitThread extends Thread {

    private String applyCode;
    private List<String> CNums;
    private Handler handler;

    private boolean result;

    public DeliverySubmitThread(String applyCode,List<String> CNums,Handler handler){
        this.CNums=CNums;
        this.applyCode=applyCode;
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            result=client.confirmRetrieval(applyCode,CNums);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=result;
        handler.sendMessage(msg);
    }
}
