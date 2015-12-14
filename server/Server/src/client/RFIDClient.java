package client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import rfid.service.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
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
            List<LocationInfo> list = client.getLocationListByItemErpCode("2510TP000009924_53009", true);
//            Good item = client.getGoodByCNum("2015121200000001");
//            List<> list = client.getLocationListByItemErpCode("");
//            List<Good> l = client.getGoodsListByCode("VD-SH-2015090000006", false);
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
            for(Iterator it = list.iterator();it.hasNext();){
                LocationInfo item = (LocationInfo)it.next();
                Field[] fields = item.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String mod = Modifier.toString(fields[i].getModifiers());
                    if(!mod.equals("public"))
                        continue;
                    System.out.print("成员变量" + i + "类型 : " + fields[i].getType().getName());
                    System.out.print("\t成员变量" + i + "变量名: " + fields[i].getName() + "\t");
                    System.out.println("成员变量" + i + "值: " + fields[i].get(item));
                }
                System.out.println("----------------------------------------");
            }
            /*for(Iterator it = l.iterator();it.hasNext();){
                Good item = (Good)it.next();
                Field[] fields = item.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String mod = Modifier.toString(fields[i].getModifiers());
                    if(!mod.equals("public"))
                        continue;
                    System.out.print("成员变量" + i + "类型 : " + fields[i].getType().getName());
                    System.out.print("\t成员变量" + i + "变量名: " + fields[i].getName() + "\t");
                    System.out.println("成员变量" + i + "值: " + fields[i].get(item));
                }
            }*/
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
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
