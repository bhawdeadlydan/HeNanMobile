package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.RFIDService;
import rfid.service.check;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/12.
 */
public class CheckThread  extends Thread{


    private List<check> checkList;
    private Handler handler;

    private boolean checkResult;

    public CheckThread(List<check> checkList,Handler handler){
        this.checkList=checkList;
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            checkResult=client.confirmInventory(checkList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=checkResult;
        handler.sendMessage(msg);
    }
}
