package rfid.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by richard on 2015/12/9.
 */
public class RFIDServer {
    public static void main(String args[]){
        try {
            TServerSocket serverTransport = new TServerSocket(7777);
            Factory proFactory = new TBinaryProtocol.Factory();
            TProcessor processor = new RFIDService.Processor(new RFIDServiceImpl());
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Start server on port 7777...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
