package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.RFIDService;
import rfid.service.stagingInfo;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/20.
 */
public class TmpSearchThread extends Thread {

    private String search;//暂存点名称
    private Handler handler;


    private List<stagingInfo> stagingInfoList;

    public TmpSearchThread(String search,Handler handler){
        this.search=search;
        this.handler=handler;
    }


    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            stagingInfoList=client.getStagingInfo(search);
            msg.what=1;
            msg.obj=stagingInfoList;
            handler.sendMessage(msg);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }

    }
}
