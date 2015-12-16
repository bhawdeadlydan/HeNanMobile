package sjtu.rfid.thread;

import android.os.Handler;

import org.apache.thrift.TException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rfid.service.Good;
import rfid.service.RFIDService;
import sjtu.rfid.tools.ConnectServer;

/**
 * Created by user on 12/15/2015.
 */
public class CheckByPosScanTagThread extends Thread {
    List<Map<String,String>> mCheckByPosList;
    Handler mHandler;
    Set<String> boxSet;
    boolean isReading = false;
    ConnectServer connectServer;
    Set<String> itemCate;

    RFIDService.Client client;

    public CheckByPosScanTagThread(Handler mHandler,List<Map<String,String>> mCheckByPosList,boolean isReading,Set<String> itemCate) {
        this.mHandler = mHandler;
        boxSet = new HashSet<>();
        this.isReading = false;
        connectServer=new ConnectServer();
        client = connectServer.openConnect();
        this.itemCate = itemCate;
    }

    public void setIsReading(boolean f){
        this.isReading = false;
    }

    @Override
    public void run()
    {

    }

    public void scan() {
        String epc = "1";
        try {
            Good good = client.getGoodByCNum(epc);
            if( itemCate.contains(good.getCode()) && !boxSet.contains(epc) ) {
                boxSet.add(epc);
                for( Map map : mCheckByPosList ) {
                    if( map.get("matCode").equals(good.getCode())) {
                        int expctedCnt = Integer.valueOf((String)map.get("expectedCount"));
                        int realCnt = Integer.valueOf((String)map.get("realCount"));
                        if( expctedCnt > realCnt ) {
                            realCnt++;
                            map.put("realCount",String.valueOf(realCnt));
                        }
                    }
                }
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }

}
