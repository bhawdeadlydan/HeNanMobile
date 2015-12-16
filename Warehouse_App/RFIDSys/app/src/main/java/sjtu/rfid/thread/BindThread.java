package sjtu.rfid.thread;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/12.
 */
public class BindThread extends Thread {

    private List<String> CNumList;
    private int goodPos;
    private Handler handler;

    private boolean result;

    public BindThread(List<String> CNumList,int goodPos,Handler handler){
        this.CNumList=CNumList;
        this.goodPos=goodPos;
        this.handler=handler;
    }

    @Override
    public void run() {

        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            result=client.bindLocationAndGoods(goodPos,CNumList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=result;
        handler.sendMessage(msg);
    }
}
