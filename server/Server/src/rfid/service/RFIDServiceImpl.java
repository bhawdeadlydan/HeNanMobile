package rfid.service;

import dao.*;
import db.*;
import javafx.geometry.Pos;
import org.apache.thrift.TException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by richard on 2015/12/9.
 */
public class RFIDServiceImpl implements RFIDService.Iface{
    @Override
    public List<ASN> getReceivingSheets() throws TException {
        ASNDao dao = new ASNDao();
        List list = dao.findByProperty("ASNEntity", "Paid", "false");
        ArrayList<ASN> l = new ArrayList<>();
        for(Iterator it = list.iterator(); it.hasNext();) {
            AsnEntity entity = (AsnEntity) it.next();
            ASN asn = new ASN();
            asn.setCode(entity.getCode());
            asn.setProject_Code(entity.getProjectCode());
            asn.setOrder_Date(entity.getOrderDate().toString());
            asn.setVendor_Name(entity.getVendorName());
            asn.setApply_Person(entity.getApplyPerson());
            asn.setReleated_Bill1(entity.getRelatedBill1());
            l.add(asn);
        }
        return l;
    }

    @Override
    public List<Good> getGoodsListByCode(String Code) throws TException {
        WMSDetailDao wdao = new WMSDetailDao();
        ASNDao adao = new ASNDao();
        List list = wdao.findByProperty("WmsDetailEntity", "asnCode",Code);
        ArrayList<Good> l = new ArrayList<>();
        int num = adao.getCartonNumByCode(Code);
        for(Iterator it = list.iterator(); it.hasNext();){
            WmsDetailEntity entity = (WmsDetailEntity)it.next();
            Good good = new Good();
            good.setIs_Bom(entity.getIsBom().toUpperCase() == "Y");
            if(good.Is_Bom){
                good.setCode(entity.getSaleBomCode());
                good.setNum(num);
                good.setDetail(adao.getDescribeByCode(Code));
                good.setUnit(adao.getUnitByCode(Code));
                good.setExpected_Quantity();
            }
            else{
                good.
            }
        }
        return l;
    }

    @Override
    public String getCodeByCNum(String CNum) throws TException {
        String Code;
        WMSDetailDao dao = new WMSDetailDao();
        Code = dao.getCodeByCNum(CNum);
        return Code;
    }

    @Override
    public boolean bindLocationAndGoods(int LocationID, List<String> CNums) throws TException {
        WMSDetailDao dao = new WMSDetailDao();
        for(Iterator<String> it = CNums.iterator(); it.hasNext();){
            dao.bind(LocationID, it.next());
        }
        return true;
    }

    @Override
    public List<POS> getApplySheets() throws TException {
        PosDao dao = new PosDao();
        List list = dao.findByProperty("PosEntity", "sent", "false");
        ArrayList<POS> l = new ArrayList<>();
        for(Iterator it = list.iterator(); it.hasNext();) {
            PosEntity entity = (PosEntity) it.next();
            POS pos = new POS();
            pos.setApply_Doc_Code(entity.getApplyDocCode());
            pos.setApply_Person(entity.getApplyPerson());
            pos.setApply_Unit(entity.getApplyUnit());
            pos.setProject_Code(entity.getProjectCode());
            pos.setApply_Date(entity.getApplyDate().toString());
            pos.setReceiver(entity.getReceiver());
            l.add(pos);
        }
        return l;
    }

    @Override
    public List<Good> getGoodsListByApplyDocCode(String ApplyDocCode) throws TException {
        return null;
    }

    @Override
    public boolean confirmRetrieval(String ApplyDocCode, List<String> CNums) throws TException {
        PosDao pdao = new PosDao();
        pdao.Retrieval(ApplyDocCode);
        WMSDetailDao wdao = new WMSDetailDao();
        for(Iterator<String> it = CNums.iterator(); it.hasNext();){
            wdao.bind(0, it.next());
        }
        return true;
    }

    @Override
    public boolean confirmReceiving(String Code) throws TException {
        ASNDao dao = new ASNDao();
        dao.Receiving(Code);
        return true;
    }

    @Override
    public Good getGoodByCNum(String CNum) throws TException {
        return null;
    }

    @Override
    public List<Integer> getLocationListByItemErpCode(String ItemERPCode) throws TException {
        ArrayList<Integer> l = new ArrayList<>();
        return l;
    }

    @Override
    public List<Good> getGoodsByLocation(int Location) throws TException {
        return null;
    }

    @Override
    public String getApplyDocCodeByCNum(String CNum) throws TException {
        WMSDetailDao dao = new WMSDetailDao();
        return dao.getApplyDocCodeByCNum(CNum);
    }

    @Override
    public boolean confirmInventory(String Location, String MaterialCode, int RealNum, String Time) throws TException {
        CheckEntity check = new CheckEntity();
        check.setMaterialCode(MaterialCode);
        check.setPosition(Location);
        check.setRealNum(RealNum);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(Time);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());

        }catch(Exception e){
            e.printStackTrace();
        }
        check.setTime(timestamp);
        check.setExpectedNum();
        CheckDao dao = new CheckDao();
        dao.addEntity(check);
        return true;
    }

    @Override
    public boolean confirmArrive(String charge, String Time, String Position, String Type, String PosApplyDocCode, double longitude, double latitude) throws TException {
        TransportEntity transport = new TransportEntity();
        transport.setCharge(charge);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(Time);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());

        }catch(Exception e){
            e.printStackTrace();
        }
        transport.setTime(timestamp);
        transport.setPosition(Position);
        transport.setType(Type);
        transport.setPosApplyDocCode(PosApplyDocCode);
        transport.setLongitude(longitude);
        transport.setLatitude(latitude);
        TransportDao dao = new TransportDao();
        dao.addEntity(transport);
        return true;
    }
}
