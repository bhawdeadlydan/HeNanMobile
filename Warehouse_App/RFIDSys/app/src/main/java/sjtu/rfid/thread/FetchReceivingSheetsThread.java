package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.Iterator;
import java.util.List;

import rfid.service.Good;
import rfid.service.RFIDService;

/**
 * Created by user on 12/10/2015.
 */
public class FetchReceivingSheetsThread extends Thread {

    private Handler mHandler;

    public FetchReceivingSheetsThread(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        try {
            TTransport transport = new TSocket("192.168.1.178", 7777);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            RFIDService.Client client = new RFIDService.Client(protocol);
//            List<Good> l2 = client.getGoodsListByApplyDocCode("2524-REQ-2015100000297");
            String s = client.getApplyDocCodeByCNum("1");
//            for(Iterator it = l2.iterator();it.hasNext();){
//                Good g = (Good)it.next();
//                System.out.println(g.getCode() + "+" + g.getNum());
//            }
            Message msg = mHandler.obtainMessage();
            msg.obj = s;
            mHandler.sendMessage(msg);
            System.out.print("123"+s+"!!!!!!!");
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
