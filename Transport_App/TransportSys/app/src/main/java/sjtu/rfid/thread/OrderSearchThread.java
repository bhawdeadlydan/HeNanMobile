package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.RFIDService;
import rfid.service.transportInfo;
import sjtu.rfid.entity.ArrivalEntity;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/20.
 */
public class OrderSearchThread extends Thread {

    private Handler handler;

    private List<transportInfo> transportInfoList;

    public  OrderSearchThread(Handler handler){
        this.handler=handler;
    }
    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            transportInfoList=client.getTransportInfo();
            msg.what=1;
            msg.obj=transportInfoList;
            handler.sendMessage(msg);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
    }
}
