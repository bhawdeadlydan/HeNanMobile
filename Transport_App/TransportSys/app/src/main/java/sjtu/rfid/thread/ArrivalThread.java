package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.Good;
import rfid.service.POS;
import rfid.service.RFIDService;
import sjtu.rfid.entity.ArrivalEntity;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class ArrivalThread extends Thread {

    private Handler handler;
    private String applyOrder;

    private POS pos;
    private List<Good> goodsList;
    private ArrivalEntity arrivalEntity;

    public ArrivalThread(Handler handler, String applyOrder){
        this.handler=handler;
        this.applyOrder=applyOrder;
    }


    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            pos=client.getPOSInfoByApplyDocCode(applyOrder);
            goodsList=client.getGoodsListByApplyDocCode(applyOrder);
            arrivalEntity=new ArrivalEntity(pos,goodsList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }

        msg.what=1;
        msg.obj=arrivalEntity;
        handler.sendMessage(msg);
    }
}
