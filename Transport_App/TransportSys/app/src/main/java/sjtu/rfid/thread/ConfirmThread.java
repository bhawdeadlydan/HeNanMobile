package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.Iterator;
import java.util.List;

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
    private String CNum;

    private String applyOrder;
    private List<Good> goodsList;
    private ConfirmEntity confirmEntity;

    public ConfirmThread(Handler handler, String CNum){
        this.handler=handler;
        this.CNum=CNum;
    }


    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            applyOrder=client.getApplyDocCodeByCNum(CNum);
            goodsList=client.getGoodsListByApplyDocCode(applyOrder);
            confirmEntity=new ConfirmEntity(applyOrder,goodsList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }

        msg.what=1;
        msg.obj=confirmEntity;
        handler.sendMessage(msg);
    }
}
