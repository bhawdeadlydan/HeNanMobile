package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import rfid.service.RFIDService;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/20.
 */
public class TmpSearchThread extends Thread {

    private String search;//暂存点名称
    private Handler handler;

    public TmpSearchThread(String search,Handler handler){
        this.search=search;
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
