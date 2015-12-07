/**
 * SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutIn;

import dao.*;
import db.*;

import java.sql.Timestamp;

public class SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingImpl implements PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrv_PortType{
    public PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvResponse process(PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest payload) throws java.rmi.RemoteException {
        SB_WMS_WMS_ImportComEpuSimMatStorSrvResponse r = new SB_WMS_WMS_ImportComEpuSimMatStorSrvResponse();
        ASNDao asndao = new ASNDao();
        PackingBomDao pbdao = new PackingBomDao();
        BomDao bomdao = new BomDao();
        PackingDao pdao = new PackingDao();
        SaleBomDao sbdao = new SaleBomDao();
        SaleBomDetailDao sbddao = new SaleBomDetailDao();
        ItemDao idao = new ItemDao();
        WMSDetailDao wddao = new WMSDetailDao();
        for(SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem asnitem : payload.getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection()) {
            AsnEntity asn = new AsnEntity();
            asn.setCompany(asnitem.getCOMPANY());
            asn.setOrgCode(asnitem.getORG_CODE());
            asn.setWarehouseCode(asnitem.getWAREHOUSE_CODE());
            asn.setBillType(asnitem.getBILL_TYPE());
            asn.setCode(asnitem.getCODE());
            asn.setFlag(asnitem.getFLAG());
            asn.setRelatedBill1(asnitem.getRELATED_BILL1());
            asn.setRelatedBill2(asnitem.getRELATED_BILL2());
            asn.setRelatedBill3(asnitem.getRELATED_BILL3());
            asn.setProjectCode(asnitem.getPROJECT_CODE());
            asn.setOrderDate(new Timestamp(asnitem.getORDER_DATE().getTimeInMillis()));
            asn.setExpectedArriveDate(new Timestamp(asnitem.getEXPECTED_ARRIVE_DATE().getTimeInMillis()));
            asn.setDock(asnitem.getDOCK());
            asn.setBuyerName(asnitem.getBUYER_NAME());
            asn.setBuyerFax(asnitem.getBUYER_FAX());
            asn.setBuyerPhone(asnitem.getBUYER_PHONE());
            asn.setBuyerEmail(asnitem.getBUYER_EMAIL());
            asn.setMaterialsName(asnitem.getMATERIALS_NAME());
            asn.setMaterialsEmail(asnitem.getMATERIALS_EMAIL());
            asn.setMaterialsFax(asnitem.getMATERIALS_FAX());
            asn.setMaterialsPhone(asnitem.getMATERIALS_PHONE());
            asn.setVendorCode(asnitem.getVENDOR_CODE());
            asn.setVendorName(asnitem.getVENDOR_NAME());
            asn.setVendorContactName(asnitem.getVENDOR_CONTACT_NAME());
            asn.setVendorContactEmail(asnitem.getVENDOR_CONTACT_EMAIL());
            asn.setVendorContactFax(asnitem.getVENDOR_CONTACT_FAX());
            asn.setVendorContactPhone(asnitem.getVENDOR_CONTACT_PHONE());
            asn.setCarrierName(asnitem.getCARRIER_NAME());
            asn.setCarrierContactName(asnitem.getCARRIER_CONTACT_NAME());
            asn.setCarrierContactEmail(asnitem.getCARRIER_CONTACT_EMAIL());
            asn.setCarrierContactFax(asnitem.getCARRIER_CONTACT_FAX());
            asn.setCarrierContactPhone(asnitem.getCARRIER_CONTACT_PHONE());
            asn.setDisposition(asnitem.getDISPOSITION());
            asndao.addEntity(asn);
            for(BOMS_Item bItem : asnitem.getBOMS_Collection()) {
                BomEntity bom = new BomEntity();
                bom.setAsnCode(asnitem.getCODE());
                Integer bomid = (Integer) bomdao.addEntity(bom);
                for(PACKING_BOMS_Item pbItem : bItem.getPACKING_BOMS_Collection()) {
                    PackingBomEntity packingbom = new PackingBomEntity();
                    packingbom.setPackingBomCode(pbItem.getPACKING_BOM_CODE());
                    packingbom.setPackingType(pbItem.getPACKING_TYPE());
                    packingbom.setVendorCode(pbItem.getVENDOR_CODE());
                    packingbom.setVendorName(pbItem.getVENDOR_NAME());
                    packingbom.setBomId(bomid);
                    Integer packingbomid = (Integer)pbdao.addEntity(packingbom);
                    for(PACKINGS_Item pItem : pbItem.getPACKINGS_Collection()) {
                        PackingEntity packing = new PackingEntity();
                        packing.setPackingBomId(packingbomid);
                        packing.setPackingCode(pItem.getPACKING_CODE());
                        packing.setPackingName(pItem.getPACKING_NAME());
                        packing.setPackingCount(pItem.getPACKING_COUNT().intValueExact());
                        packing.setPackingLenUnit(pItem.getPACKING_LEN_UNIT());
                        packing.setPackingLen(pItem.getPACKING_LEN().doubleValue());
                        packing.setPackingWidth(pItem.getPACKING_WIDTH().doubleValue());
                        packing.setPackingHeight(pItem.getPACKING_HEIGHT().doubleValue());
                        packing.setPackingCapacityUom(pItem.getPACKING_CAPACITY_UOM());
                        packing.setPackingCapacity(pItem.getPACKING_CAPACITY().doubleValue());
                        packing.setPackingWeightUom(pItem.getPACKING_WEIGHT_UOM());
                        packing.setPackingWeight(pItem.getPACKING_WEIGHT().doubleValue());
                        pdao.addEntity(packing);
                    }
                }
                for(SALE_BOMS_Item sbItem : bItem.getSALE_BOMS_Collection()) {
                    SaleBomEntity salebom = new SaleBomEntity();
                    salebom.setBomId(bomid);
                    salebom.setSaleBomCode(sbItem.getSALE_BOM_CODE());
                    salebom.setSaleBomName(sbItem.getSALE_BOM_NAME());
                    salebom.setSaleBomUnit(sbItem.getSALE_BOM_UNIT());
                    Integer salebomid = (Integer)sbdao.addEntity(salebom);
                    for(SALE_BOM_DETAIL_Item sbdItem : sbItem.getSALE_BOM_DETAILS_Collection()[0]) {
                        SaleBomDetailEntity salebomdetail = new SaleBomDetailEntity();
                        salebomdetail.setSaleBomsId(salebomid);
                        salebomdetail.setItemErpCode(sbdItem.getITEM_ERP_CODE());
                        salebomdetail.setItemErpName(sbdItem.getITEM_ERP_NAME());
                        salebomdetail.setItemErpQuantity(sbdItem.getITEM_ERP_QUANTITY().intValueExact());
                        salebomdetail.setItemErpUnit(sbdItem.getITEM_ERP_UNIT());
                        //生成的类中没有下面三个属性
                        salebomdetail.setItemCode(sbdItem.getATTRIBUTE1());
                        salebomdetail.setItemName(sbdItem.getATTRIBUTE2());
                        salebomdetail.setConfigQuantity(1);

                        Integer sbdid = (Integer)sbddao.addEntity(salebomdetail);
                        for(ITEMS_Item iItem : sbdItem.getITEMS_Collection()) {
                            ItemEntity item = new ItemEntity();
                            item.setSaleBomDetailId(sbdid);
                            item.setItemCode(iItem.getITEM_CODE());
                            item.setItemName(iItem.getITEM_NAME());
                            item.setConfigQuantity(iItem.getCONFIG_QUANTITY().intValueExact());
                            item.setItemUnit(iItem.getITEM_UNIT());
                            idao.addEntity(item);
                        }
                    }
                }
            }
            for(WMS_DETAIL_Item wdItem : asnitem.getWMS_DETAILS_Collection()[0][0]) {
                WmsDetailEntity wmsdetail = new WmsDetailEntity();
                wmsdetail.setAsnCode(asn.getCode());
                wmsdetail.setIsBom(wdItem.getIS_BOM());
                wmsdetail.setSaleBomCode(wdItem.getSALE_BOM_CODE());
                wmsdetail.setPackingCode(wdItem.getPACKING_CODE());
                wmsdetail.setPrice(wdItem.getPRICE().doubleValue());
                wmsdetail.setCartonOrderNum(wdItem.getCARTON_ORDER_NUM());
                wmsdetail.setCartonNum(wdItem.getCARTON_NUM());
                wmsdetail.setItemCode(wdItem.getITEM_CODE());
                wmsdetail.setItemName(wdItem.getITEM_NAME());
                wmsdetail.setItemUnitCode(wdItem.getITEM_UNIT_CODE());
                wmsdetail.setExpectedQuantity(wdItem.getEXPECTED_QUANTITY().intValueExact());
                wmsdetail.setAllocationId(0);//数据库中有allocation的外键，但是此时没有，所以用0替代
                wddao.addEntity(wmsdetail);
            }
        }
        return r;
    }

}
