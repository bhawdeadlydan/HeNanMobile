package tools;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.Iterator;
import java.util.List;

import rfid.service.POS;
import rfid.service.RFIDService;

/**
 * Created by shao on 2015/12/11.
 */
public class ConnectServer {

    private  TTransport transport;
    public RFIDService.Client openConnect(){
        try {
            // 设置调用的服务地址为本地，端口为 7777
            transport = new TSocket("192.168.1.178", 7777);
            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            RFIDService.Client client = new RFIDService.Client(protocol);
            return client;
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnect(){
        if(!transport.isOpen()){
            transport.close();
        }
    }
}
