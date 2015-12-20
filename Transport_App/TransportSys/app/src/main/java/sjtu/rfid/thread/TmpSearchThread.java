package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.RFIDService;
import rfid.service.inStagingInfo;
import rfid.service.stagingInfo;
import sjtu.rfid.entity.TmpSearchEntity;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/20.
 */
public class TmpSearchThread extends Thread {

    private String search;//暂存点名称
    private Handler handler;


    private List<inStagingInfo> in;
    private List<stagingInfo> out;
    private TmpSearchEntity tmpSearchEntity;

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
            in=client.getInStagingInfo(search);
            out=client.getStagingInfo(search);
            tmpSearchEntity=new TmpSearchEntity(in,out);
            msg.what=1;
            msg.obj=tmpSearchEntity;
            handler.sendMessage(msg);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }

    }
}
