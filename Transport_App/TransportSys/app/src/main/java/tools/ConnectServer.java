package tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import rfid.service.RFIDService;
import sjtu.rfid.entity.ConfigData;

/**
 * Created by shao on 2015/12/11.
 */
public class ConnectServer {

    private  TTransport transport;

    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }
    public RFIDService.Client openConnect(){
        try {
            String ip= ConfigData.ip;
            Integer port=ConfigData.port;
            // 设置调用的服务地址为本地，端口为 7777
            transport = new TSocket(ip, port);
            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            RFIDService.Client client = new RFIDService.Client(protocol);
            return client;
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnect(){
        if(!transport.isOpen()){
            transport.close();
        }
    }
}
