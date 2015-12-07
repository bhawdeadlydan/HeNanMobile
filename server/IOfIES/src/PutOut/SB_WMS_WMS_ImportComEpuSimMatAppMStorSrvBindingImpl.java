/**
 * SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutOut;

import dao.DetailDao;
import dao.PosDao;
import db.DetailEntity;
import db.PosEntity;

import java.sql.Timestamp;

public class SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvBindingImpl implements PutOut.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv_PortType{
    public PutOut.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse process(PutOut.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest payload) throws java.rmi.RemoteException {
        System.out.println("出库");
        SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse r = new SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse();
        PosDao pdao = new PosDao();
        DetailDao ddao = new DetailDao();
        for(SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem positem : payload.getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection()) {
            PosEntity pos = new PosEntity();
            pos.setCompany(positem.getCOMPANY());
            pos.setWarehouseCode(positem.getWAREHOUSE_CODE());
            pos.setBillType(positem.getBILL_TYPE());
            pos.setCode(positem.getCODE());
            pos.setApplyDocCode(positem.getAPPLY_DOC_CODE());
            pos.setProjectCode(positem.getPROJECT_CODE());
            pos.setApplyUnit(positem.getAPPLY_UNIT());
            pos.setApplyPerson(positem.getAPPLY_PERSON());
            pos.setApplyDate(new Timestamp(positem.getAPPLY_DATE().getTimeInMillis()));
            pos.setConstructionUnit(positem.getCONSTRUCTION_UNIT());
            pos.setReceiver(positem.getRECEIVER());
            pos.setReceiverUidCode(positem.getRECEIVER_UID_CODE());
            pos.setApplyDocDesc(positem.getAPPLY_DOC_DESC());
            pos.setExpectedShipDate(new Timestamp(positem.getEXPECTED_SHIP_DATE().getTimeInMillis()));
            pos.setDockCode(positem.getDOCK_CODE());
            pos.setDisposition(positem.getDISPOSITION());
            pdao.addEntity(pos);
            for(DETAILS_Item dItem : positem.getDETAILS_Collection()) {
                DetailEntity detail = new DetailEntity();
                detail.setIsBom(dItem.getIS_BOM());
                detail.setSaleBomCode(dItem.getSALE_BOM_CODE());
                detail.setItemErpCode(dItem.getITEM_ERP_CODE());
                detail.setItemErpUnit(dItem.getITEM_ERP_UNIT());
                detail.setItemCode(dItem.getITEM_CODE());
                detail.setItemName(dItem.getITEM_NAME());
                detail.setItemUnitCode(dItem.getITEM_UNIT_CODE());
                detail.setExpectedQuantity(dItem.getEXPECTED_QUANTITY().intValueExact());
                detail.setPosApplyDocCode(pos.getApplyDocCode());
                ddao.addEntity(detail);
            }
        }
        return r;
    }

}
