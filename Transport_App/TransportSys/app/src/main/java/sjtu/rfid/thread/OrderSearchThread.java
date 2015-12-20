package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import rfid.service.RFIDService;
import sjtu.rfid.entity.ArrivalEntity;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/20.
 */
public class OrderSearchThread extends Thread {

    private Handler handler;

    public  OrderSearchThread(Handler handler){
        this.handler=handler;
    }
    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
//        try{
//            pos=client.getPOSInfoByApplyDocCode(applyOrder);
//            goodsList=client.getGoodsListByApplyDocCode(applyOrder);
//            arrivalEntity=new ArrivalEntity(pos,goodsList);
//        }catch(TException e){
//            msg.what=0;
//            e.printStackTrace();
//        }
//
//        msg.what=1;
//        msg.obj=arrivalEntity;
//        handler.sendMessage(msg);
    }
}
