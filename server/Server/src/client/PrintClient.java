package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by richard on 2015/12/13.
 */
public class PrintClient {
    private TTransport transport;
    private RfidPrinterService.Client client;
    private boolean connected;

    public PrintClient(){
        // 设置调用的服务地址为本地，端口为 7778
        transport = new TSocket("localhost", 7778);
        connected = true;
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new RfidPrinterService.Client(protocol);
        } catch (TTransportException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
            connected = false;
        }
    }

    public void close(){
        transport.close();
    }

    public boolean callPrinter(Data data){
        try {
            // 设置传输协议为 TBinaryProtocol
            if(connected)
                return client.callPrinter(data);
            else
                return false;
        } catch (TTransportException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
            close();
        } catch (TException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
            close();
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
