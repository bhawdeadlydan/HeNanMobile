package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.sql.Timestamp;

import rfid.service.RFIDService;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/12.
 */
public class CommitTransInfoThread extends Thread {

    private String charge;
    private String time;
    private String position;
    private String type;
    private String applyCode;
    private double lng;
    private double lat;
    private Handler handler;

    private boolean result;

    public CommitTransInfoThread(String charge,String time,String position,String type,String applyCode,double lng,double lat,Handler handler){
        this.charge=charge;
        this.time=time;
        this.position=position;
        this.type=type;
        this.applyCode=applyCode;
        this.lng=lng;
        this.lat=lat;
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        ConnectServer connectServer = new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            result=client.confirmArrive(charge,time,position,type,applyCode,lng,lat);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }

        msg.what=1;
        msg.obj=result;
        handler.sendMessage(msg);

    }
}
