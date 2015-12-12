package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;

import java.util.List;

import rfid.service.Good;
import rfid.service.LocationInfo;
import rfid.service.RFIDService;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by shao on 2015/12/11.
 */
public class CheckByMatThread extends Thread {

    private String CNum;
    private Handler handler;

    private Good good;
    private List<LocationInfo> locationInfoList;
    private CheckByMatEntity checkByMatEntity;

    public CheckByMatThread(String CNum,Handler handler){
        this.CNum=CNum;
        this.handler=handler;
    }

    @Override
    public void run() {

        Message msg=handler.obtainMessage();
        ConnectServer connectServer=new ConnectServer();
        RFIDService.Client client = connectServer.openConnect();
        try{
            good=client.getGoodByCNum(CNum);

            locationInfoList=client.getLocationListByItemErpCode(good.getCode());
            checkByMatEntity=new CheckByMatEntity(good,locationInfoList);
        }catch(TException e){
            msg.what=0;
            e.printStackTrace();
        }
        msg.what=1;
        msg.obj=checkByMatEntity;
        handler.sendMessage(msg);

    }
}
