package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.POS;
import rfid.service.RFIDService;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/18.
 */
public class SheetsThread extends Thread {

    Handler handler;
    private List<POS> posList;

    public SheetsThread(Handler mHandler) {
        this.handler = mHandler;
    }

    @Override
    public  void run() {
        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            posList=client.getOutApplySheets();
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=posList;
        handler.sendMessage(msg);
    }

}
