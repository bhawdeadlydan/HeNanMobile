package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class ReceivingScanBoxThread extends Thread {

    private Handler handler;
    private String sheetCode;

    private List<Good> goodList;

    public ReceivingScanBoxThread(Handler handler,String sheetCode){
        this.handler=handler;
        this.sheetCode=sheetCode;
    }

    @Override
    public void run() {

        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            goodList=client.getGoodsListByCode(sheetCode);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=goodList;
        handler.sendMessage(msg);
    }
}
