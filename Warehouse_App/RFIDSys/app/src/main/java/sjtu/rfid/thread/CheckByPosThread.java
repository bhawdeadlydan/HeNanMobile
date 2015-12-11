package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class CheckByPosThread extends  Thread{

    private int goodPos;
    private Handler handler;

    private List<Good> goodList;

    public CheckByPosThread(int goodPos,Handler handler){
        this.goodPos=goodPos;
        this.handler=handler;
    }

    @Override
    public void run() {

        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            goodList=client.getGoodsByLocation(goodPos);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=goodList;
        handler.sendMessage(msg);
    }
}
