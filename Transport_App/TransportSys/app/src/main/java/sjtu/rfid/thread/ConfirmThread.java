package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.Iterator;
import java.util.List;

import rfid.service.ASN;
import rfid.service.Good;
import rfid.service.POS;
import rfid.service.RFIDService;
import sjtu.rfid.entity.ConfirmEntity;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class ConfirmThread extends Thread {

    private Handler handler;
    private String applyOrder;


    private List<Good> goodsList;
    private POS pos;
    private ConfirmEntity confirmEntity;

    public ConfirmThread(Handler handler, String applyOrder){
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
            confirmEntity=new ConfirmEntity(pos,goodsList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }

        msg.what=1;
        msg.obj=confirmEntity;
        handler.sendMessage(msg);
    }
}
