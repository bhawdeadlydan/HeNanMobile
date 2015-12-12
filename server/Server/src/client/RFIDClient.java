package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import rfid.service.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by richard on 2015/12/9.
 */
public class RFIDClient {
    public static void main(String[] args) {
        try {
            // 设置调用的服务地址为本地，端口为 7777
            TTransport transport = new TSocket("localhost", 7777);
            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            RFIDService.Client client = new RFIDService.Client(protocol);
            List<Good> l = client.getGoodsListByCode("VD-SH-2015090000006", true);
           /* List<ASN> list = client.getReceivingSheets();
            for(Iterator it = list.iterator();it.hasNext();) {
                ASN pos = (ASN) it.next();
                System.out.println(pos.getCode());
            }*/
//            client.confirmReceiving("VD-SH-2015090000006");
//            List<Good> l = client.getGoodsListByApplyDocCode("2524-REQ-2015100000297");
            /*List<POS> list = client.getApplySheets();
            for(Iterator it = list.iterator();it.hasNext();){
                POS pos = (POS)it.next();
                System.out.println(pos.getApply_Doc_Code());
            }*/
//            List<LocationInfo> list = client.getLocationListByItemErpCode("10041645");
            for(Iterator it = l.iterator();it.hasNext();){
                Good g = (Good)it.next();
                System.out.println(g.getCode() + "+" + g.getNum() + "+"+g.getDetail());
            }
//            for(Iterator it = list.iterator();it.hasNext();){
//                LocationInfo linfo  = (LocationInfo) it.next();
//                System.out.println(linfo.getID() + "," +linfo.getArea() + "," + linfo.getLocation());
//            }
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
