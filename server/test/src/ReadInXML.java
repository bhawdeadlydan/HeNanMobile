/**
 * Created by richard on 2015/12/9.
 */
import dao.*;
import db.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ReadInXML {
    public void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();
        ASNDao asndao = new ASNDao();
        PackingBomDao pbdao = new PackingBomDao();
        BomDao bomdao = new BomDao();
        PackingDao pdao = new PackingDao();
        SaleBomDao sbdao = new SaleBomDao();
        SaleBomDetailDao sbddao = new SaleBomDetailDao();
        ItemDao idao = new ItemDao();
        WMSDetailDao wddao = new WMSDetailDao();
        try {
            Document document = saxReader.read(inputXml);
            Element request = document.getRootElement();
            Element iCollection = request.element("SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection");
            for (Iterator i1 = iCollection.elementIterator(); i1.hasNext();) {
                Element iItem = (Element) i1.next();
                AsnEntity asn = new AsnEntity();
                asn.setCompany(iItem.element("COMPANY").getText());
                asn.setOrgCode(iItem.element("ORG_CODE").getText());
                asn.setWarehouseCode(iItem.element("WAREHOUSE_CODE").getText());
                asn.setBillType(iItem.element("BILL_TYPE").getText());
                asn.setCode(iItem.element("CODE").getText());
                asn.setFlag(iItem.element("FLAG").getText());
                asn.setRelatedBill1(iItem.element("RELATED_BILL1").getText());
                asn.setRelatedBill2(iItem.element("RELATED_BILL2").getText());
                asn.setRelatedBill3(iItem.element("RELATED_BILL3").getText());
                asn.setProjectCode(iItem.element("PROJECT_CODE").getText());
                Timestamp timestamp = null, timestamp1 = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX");
                try{
                    Date parsedDate = dateFormat.parse(iItem.element("ORDER_DATE").getText());
                    timestamp = new java.sql.Timestamp(parsedDate.getTime());
                }catch(Exception e){//this generic but you can control another types of exception
                    //e.printStackTrace();
                    System.err.println(e.getMessage());
                }
                asn.setOrderDate(timestamp);
                try{
                    Date date = dateFormat.parse(iItem.element("EXPECTED_ARRIVE_DATE").getText());
                    timestamp1 = new java.sql.Timestamp(date.getTime());
                }catch(Exception e){//this generic but you can control another types of exception
                    //e.printStackTrace();
                    System.err.println(e.getMessage());
                }
                asn.setExpectedArriveDate(timestamp1);
                asn.setDock(iItem.element("DOCK").getText());
                asn.setBuyerName(iItem.element("BUYER_NAME").getText());
                asn.setBuyerFax(iItem.element("BUYER_FAX").getText());
                asn.setBuyerPhone(iItem.element("BUYER_PHONE").getText());
                asn.setBuyerEmail(iItem.element("BUYER_EMAIL").getText());
                asn.setMaterialsName(iItem.element("MATERIALS_NAME").getText());
                asn.setMaterialsEmail(iItem.element("MATERIALS_EMAIL").getText());
                asn.setMaterialsFax(iItem.element("MATERIALS_FAX").getText());
                asn.setMaterialsPhone(iItem.element("MATERIALS_PHONE").getText());
                asn.setVendorCode(iItem.element("VENDOR_CODE").getText());
                asn.setVendorName(iItem.element("VENDOR_NAME").getText());
                asn.setVendorContactName(iItem.element("VENDOR_CONTACT_NAME").getText());
                asn.setVendorContactEmail(iItem.element("VENDOR_CONTACT_EMAIL").getText());
                asn.setVendorContactFax(iItem.element("VENDOR_CONTACT_FAX").getText());
                asn.setVendorContactPhone(iItem.element("VENDOR_CONTACT_PHONE").getText());
                asn.setCarrierName(iItem.element("CARRIER_NAME").getText());
                asn.setCarrierContactName(iItem.element("CARRIER_CONTACT_NAME").getText());
                asn.setCarrierContactEmail(iItem.element("CARRIER_CONTACT_EMAIL").getText());
                asn.setCarrierContactFax(iItem.element("CARRIER_CONTACT_FAX").getText());
                asn.setCarrierContactPhone(iItem.element("CARRIER_CONTACT_PHONE").getText());
                asn.setDisposition(iItem.element("DISPOSITION").getText());
                asn.setApplyPerson(iItem.element("APPLY_PERSON").getText());
                asn.setPaid(0);
                asndao.addEntity(asn);
                Element Boms = iItem.element("BOMS_Collection");
                for (Iterator i2 = Boms.elementIterator(); i2.hasNext();) {
                    Element bItem = (Element) i2.next();
                    BomEntity bom = new BomEntity();
                    bom.setAsnCode(asn.getCode());
                    Integer bomid = (Integer) bomdao.addEntity(bom);
                    Element PackingBoms = bItem.element("PACKING_BOMS_Collection");
                    for(Iterator i3 = PackingBoms.elementIterator(); i3.hasNext();){
                        PackingBomEntity packingbom = new PackingBomEntity();
                        Element pbItem = (Element)i3.next();
                        packingbom.setPackingBomCode(pbItem.element("PACKING_BOM_CODE").getText());
                        packingbom.setPackingType(pbItem.element("PACKING_TYPE").getText());
                        packingbom.setVendorCode(pbItem.element("VENDOR_CODE").getText());
                        packingbom.setVendorName(pbItem.element("VENDOR_NAME").getText());
                        packingbom.setBomId(bomid);
                        Integer packingbomid = (Integer)pbdao.addEntity(packingbom);
                        Element Packings = pbItem.element("PACKINGS_Collection");
                        for(Iterator i4 = Packings.elementIterator(); i4.hasNext();){
                            PackingEntity packing = new PackingEntity();
                            Element pItem = (Element)i4.next();
                            packing.setPackingBomId(packingbomid);
                            packing.setPackingCode(pItem.element("PACKING_CODE").getText());
                            packing.setPackingName(pItem.element("PACKING_NAME").getText());
                            packing.setPackingCount(Integer.parseInt(pItem.element("PACKING_COUNT").getText()));
                            packing.setPackingLenUnit(pItem.element("PACKING_LEN_UNIT").getText());
                            packing.setPackingLen(Double.parseDouble(pItem.element("PACKING_LEN").getText()));
                            packing.setPackingWidth(Double.parseDouble(pItem.element("PACKING_WIDTH").getText()));
                            packing.setPackingHeight(Double.parseDouble(pItem.element("PACKING_HEIGHT").getText()));
                            packing.setPackingCapacityUom(pItem.element("PACKING_CAPACITY_UOM").getText());
                            packing.setPackingCapacity(Double.parseDouble(pItem.element("PACKING_CAPACITY").getText()));
                            packing.setPackingWeightUom(pItem.element("PACKING_WEIGHT_UOM").getText());
                            packing.setPackingWeight(Double.parseDouble(pItem.element("PACKING_WEIGHT").getText()));
                            pdao.addEntity(packing);
                        }
                    }
                    Element saleboms = bItem.element("SALE_BOMS_Collection");
                    for(Iterator i5 = saleboms.elementIterator(); i5.hasNext();){
                        SaleBomEntity salebom = new SaleBomEntity();
                        Element sbItem = (Element)i5.next();
                        salebom.setBomId(bomid);
                        salebom.setSaleBomCode(sbItem.element("SALE_BOM_CODE").getText());
                        salebom.setSaleBomName(sbItem.element("SALE_BOM_NAME").getText());
                        salebom.setSaleBomUnit(sbItem.element("SALE_BOM_UNIT").getText());
                        Integer salebomid = (Integer)sbdao.addEntity(salebom);
                        Element salebomdetails = sbItem.element("SALE_BOM_DETAILS_Collection").element("SALE_BOM_DETAILS_Item");
                        for(Iterator i6 = salebomdetails.elementIterator();i6.hasNext();){
                            SaleBomDetailEntity salebomdetail = new SaleBomDetailEntity();
                            Element sbdItem = (Element)i6.next();
                            salebomdetail.setSaleBomsId(salebomid);
                            salebomdetail.setItemErpCode(sbdItem.element("ITEM_ERP_CODE").getText());
                            salebomdetail.setItemErpName(sbdItem.element("ITEM_ERP_NAME").getText());
                            salebomdetail.setItemErpQuantity(Integer.parseInt(sbdItem.element("ITEM_ERP_QUANTITY").getText()));
                            salebomdetail.setItemErpUnit(sbdItem.element("ITEM_ERP_UNIT").getText());
                            //生成的类中没有下面三个属性
                            salebomdetail.setItemCode(sbdItem.element("ATTRIBUTE1").getText());
                            salebomdetail.setItemName(sbdItem.element("ATTRIBUTE2").getText());
                            salebomdetail.setConfigQuantity(1);

                            Integer sbdid = (Integer)sbddao.addEntity(salebomdetail);
                            Element itemscollection = sbdItem.element("ITEMS_Collection");
                            for(Iterator i7 = itemscollection.elementIterator(); i7.hasNext();){
                                ItemEntity item = new ItemEntity();
                                Element iItem1 = (Element)i7.next();
                                item.setSaleBomDetailId(sbdid);
                                item.setItemCode(iItem1.element("ITEM_CODE").getText());
                                item.setItemName(iItem1.element("ITEM_NAME").getText());
                                item.setConfigQuantity(Integer.parseInt(iItem1.element("CONFIG_QUANTITY").getText()));
                                item.setItemUnit(iItem1.element("ITEM_UNIT").getText());
                                idao.addEntity(item);
                            }
                        }
                    }
                }
                Element wmsdetails = iItem.element("WMS_DETAILS_Collection").element("WMS_DETAILS_Item").element("WMS_DETAIL_Collection");
                for(Iterator i8 = wmsdetails.elementIterator(); i8.hasNext();){
                    WmsDetailEntity wmsdetail = new WmsDetailEntity();
                    Element wdItem = (Element)i8.next();
                    wmsdetail.setAsnCode(asn.getCode());
                    wmsdetail.setIsBom(wdItem.element("IS_BOM").getText());
                    wmsdetail.setSaleBomCode(wdItem.element("SALE_BOM_CODE").getText());
                    wmsdetail.setPackingCode(wdItem.element("PACKING_CODE").getText());
                    wmsdetail.setPrice(Double.parseDouble(wdItem.element("PRICE").getText()));
                    wmsdetail.setCartonOrderNum(wdItem.element("CARTON_ORDER_NUM").getText());
                    wmsdetail.setCartonNum(wdItem.element("CARTON_NUM").getText());
                    wmsdetail.setItemCode(wdItem.element("ITEM_ERP_CODE").getText());
                    wmsdetail.setItemName(wdItem.element("ITEM_NAME").getText());
                    wmsdetail.setItemUnitCode(wdItem.element("ITEM_UNIT_CODE").getText());
                    wmsdetail.setExpectedQuantity(Integer.parseInt(wdItem.element("EXPECTED_QUANTITY").getText()));
                    wmsdetail.setAllocationId(0);//数据库中有allocation的外键，但是此时没有，所以用0替代
                /*设置箱号*/
                    wmsdetail.setcNum("");
                    wmsdetail.setApplyDocCode("");
                    wddao.addEntity(wmsdetail);
                }
            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String argv[]) {
        ReadInXML read = new ReadInXML();
        read.parserXml("test/src/xml/VD-SH-2015100000634_IES.xml");
    }
}
