package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import org.apache.thrift.TException;

import java.util.Map;

import rfid.service.RFIDService;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/20.
 */
public class TmpConfirmThread extends  Thread {

    private Handler handler;
    private String person;
    private String unit;
    private String addr;
    Map<String,Integer> mItemDetailList;


    public TmpConfirmThread(Handler handler,String person,String unit,String addr,Map<String,Integer> mItemDetailList){
        this.handler=handler;
        this.person = person;
        this.unit = unit;
        this.addr = addr;
        this.mItemDetailList = mItemDetailList;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            for( Map.Entry<String,Integer> entry : mItemDetailList.entrySet() ) {
                client.stagingSiteCheckout(person,addr,unit,entry.getKey(),entry.getValue());
            }
            msg.what=1;
            handler.sendMessage(msg);

        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
    }
}
