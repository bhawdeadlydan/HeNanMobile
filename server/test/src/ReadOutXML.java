import dao.*;
import db.DetailEntity;
import db.PosEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by richard on 2015/12/10.
 */
public class ReadOutXML {
    public void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();
        PosDao pdao = new PosDao();
        DetailDao ddao = new DetailDao();
        Document document = null;
        try {
            document = saxReader.read(inputXml);
            Element request = document.getRootElement();
            Element iCollection = request.element("SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection");
            for(Iterator i1 = iCollection.elementIterator();i1.hasNext();){
                Element positem = (Element) i1.next();
                PosEntity pos = new PosEntity();
                pos.setCompany(positem.element("COMPANY").getText());
                pos.setWarehouseCode(positem.element("WAREHOUSE_CODE").getText());
                pos.setBillType(positem.element("BILL_TYPE").getText());
                pos.setCode(positem.element("CODE").getText());
                pos.setApplyDocCode(positem.element("APPLY_DOC_CODE").getText());
                pos.setProjectCode(positem.element("PROJECT_CODE").getText());
                pos.setApplyUnit(positem.element("APPLY_UNIT").getText());
                pos.setApplyPerson(positem.element("APPLY_PERSON").getText());
                Timestamp timestamp = null, timestamp1 = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX");
                try{
                    Date parsedDate = dateFormat.parse(positem.element("APPLY_DATE").getText());
                    timestamp = new java.sql.Timestamp(parsedDate.getTime());
                }catch(Exception e){//this generic but you can control another types of exception
                    //e.printStackTrace();
                    System.err.println(e.getMessage());
                }
                pos.setApplyDate(timestamp);
                pos.setConstructionUnit(positem.element("CONSTRUCTION_UNIT").getText());
                pos.setReceiver(positem.element("RECEIVER").getText());
                pos.setReceiverUidCode(positem.element("RECEIVER_UID_CODE").getText());
                pos.setApplyDocDesc(positem.element("APPLY_DOC_DESC").getText());
                try{
                    Date date = dateFormat.parse(positem.element("EXPECTED_SHIP_DATE").getText());
                    timestamp1 = new java.sql.Timestamp(date.getTime());
                }catch(Exception e){//this generic but you can control another types of exception
                    e.printStackTrace();
//                    System.err.println(e.getMessage());
                }
                pos.setExpectedShipDate(timestamp1);
                pos.setDockCode(positem.element("DOCK_CODE").getText());
                pos.setDisposition(positem.element("DISPOSITION").getText());
                pos.setSent(0);
                Timestamp time = new Timestamp(new Date().getTime());
                pos.setSentDate(time);
                pdao.addEntity(pos);
                Element dCollection = positem.element("DETAILS_Collection");
                for(Iterator i2 = dCollection.elementIterator();i2.hasNext();){
                    Element dItem = (Element)i2.next();
                    DetailEntity detail = new DetailEntity();
                    detail.setIsBom(dItem.element("IS_BOM").getText());
                    detail.setSaleBomCode(dItem.element("SALE_BOM_CODE").getText());
                    detail.setItemErpCode(dItem.element("ITEM_ERP_CODE").getText());
                    detail.setItemErpUnit(dItem.element("ITEM_ERP_UNIT").getText());
                    detail.setItemCode(dItem.element("ITEM_CODE").getText());
                    detail.setItemName(dItem.element("ITEM_NAME").getText());
                    detail.setItemUnitCode(dItem.element("ITEM_UNIT_CODE").getText());
                    detail.setExpectedQuantity(Integer.parseInt(dItem.element("EXPECTED_QUANTITY").getText()));
                    detail.setPosApplyDocCode(pos.getApplyDocCode());
                    ddao.addEntity(detail);
                }
            }
        } catch (DocumentException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String args[]) {
        ReadOutXML read = new ReadOutXML();
        read.parserXml("test/src/xml/2524-REQ-2015100000297_IES.xml");
    }
}
