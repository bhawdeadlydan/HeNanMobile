package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rfid.service.RFIDService;
import rfid.service.check;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/12.
 */
public class CheckThread  extends Thread{


    private Map<String,check> checkList;
    private Handler handler;

    private boolean checkResult;

    public CheckThread(Map<String,check> checkList,Handler handler){
        this.checkList=checkList;
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        List<check> tmpList = new ArrayList<>();
        try{
            for( Map.Entry<String,check> entry : checkList.entrySet() ) {
                tmpList.add(entry.getValue());
            }
            checkResult=client.confirmInventory(tmpList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=checkResult;
        handler.sendMessage(msg);
    }
}
