package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import client.RFIDClient;
import rfid.service.POS;
import rfid.service.RFIDService;
import tools.ConnectServer;

/**
 * Created by shao on 2015/12/10.
 */
public class TestThread extends Thread{


    private double lng;
    private double lat;
    private String result="";
    private Handler handler;

    public TestThread(double lng,double lat,Handler handler){
        this.lat=lat;
        this.lng=lng;
        this.handler=handler;
    }
    public TestThread(Handler handler){
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        try{
            ConnectServer connectServe=new ConnectServer();
            RFIDService.Client client= connectServe.openConnect();
            if(client==null)
                return;
            String s="";
            List<POS> list = client.getApplySheets();
            for(Iterator it = list.iterator();it.hasNext();){
                POS pos = (POS)it.next();
                s+=pos.getApply_Doc_Code();
            }
            connectServe.closeConnect();
            msg.obj="#"+s+"#";
            handler.sendMessage(msg);
        }catch (TException e) {
            e.printStackTrace();
        }
    }
}
