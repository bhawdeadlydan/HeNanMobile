package rfid.service;

import dao.*;
import db.*;
import org.apache.thrift.TException;

import java.sql.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by richard on 2015/12/9.
 */
public class RFIDServiceImpl implements RFIDService.Iface{
    @Override
    public List<ASN> getReceivingSheets() throws TException {
        ASNDao dao = new ASNDao();
        List list = dao.findByProperty("ASNEntity", "Paid", "0");
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
        boolean isbom = wdao.isBom(Code);
        ArrayList<Good> l = new ArrayList<>();
        if(isbom){
            List list = wdao.getBomDistinctGoods(Code);
            for(Iterator it = list.iterator(); it.hasNext();){
                Object[] objs = (Object[]) it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum((Integer)objs[1]);
                good.setExpected_Quantity((Integer)objs[2]);
                good.setIs_Bom(true);
                String[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
                good.setDetail(str[0]);
                good.setUnit(str[1]);
                l.add(good);
            }
        }
        else{
            List list = wdao.getERPDistinctGoods(Code);
            for(Iterator it = list.iterator();it.hasNext();){
                Object[] objs = (Object[])it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum((Integer)objs[1]);
                good.setExpected_Quantity((Integer)objs[2]);
                good.setIs_Bom(false);
                good.setDetail((String)objs[3]);
                good.setUnit((String)objs[4]);
                l.add(good);
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
        DetailDao ddao = new DetailDao();
        ASNDao adao = new ASNDao();
        boolean isbom = ddao.isBom(ApplyDocCode);
        ArrayList<Good> l = new ArrayList<>();
        if(isbom) {
            List list = ddao.getBomDistinctGoods(ApplyDocCode);
            for(Iterator it = list.iterator(); it.hasNext();){
                Object[] objs = (Object[]) it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum((Integer)objs[1]);
                good.setIs_Bom(true);
                String[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
                good.setDetail(str[0]);
                good.setUnit(str[1]);
                l.add(good);
            }
        }
        else{
            List list = ddao.getERPDistinctGoods(ApplyDocCode);
            for(Iterator it = list.iterator();it.hasNext();){
                Object[] objs = (Object[])it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum((Integer)objs[1]);
                good.setIs_Bom(false);
                good.setDetail((String)objs[2]);
                good.setUnit((String)objs[3]);
                l.add(good);
            }
        }
        return l;
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
        WMSDetailDao dao = new WMSDetailDao();
        ASNDao adao = new ASNDao();
        Object[] objs = dao.getGoodByCNum(CNum);
        Good good = new Good();
        String salebomcode = (String) objs[0];
        String itemcode = (String) objs[1];
        String isbom = (String) objs[2];
        String itemname = (String) objs[3];
        boolean bom = isbom.toUpperCase().equals("Y");
        if(bom) {
            good.setCode(salebomcode);
            String[] str = adao.getDesAndUnitBySaleBomCode(salebomcode);
            good.setDetail(str[0]);
            good.setIs_Bom(true);
        }
        else{
            good.setCode(itemcode);
            good.setDetail(itemname);
            good.setIs_Bom(false);
        }
        return good;
    }

    @Override
    public List<Integer> getLocationListByItemErpCode(String ItemERPCode) throws TException {
        WMSDetailDao dao = new WMSDetailDao();
        List<Integer> l = dao.getLocationIDsByItemERPCode(ItemERPCode);
        return l;
    }

    @Override
    public List<Good> getGoodsByLocation(int Location) throws TException {
        WMSDetailDao wdao = new WMSDetailDao();
        ASNDao adao = new ASNDao();
        ArrayList<Good> l = new ArrayList<>();
        List bomlist = wdao.getBomGoodsByLocation(Location), erplist = wdao.getERPGoodsByLocation(Location);
        for(Iterator it = bomlist.iterator();it.hasNext();){
            Good good = new Good();
            good.setIs_Bom(true);
            Object[] objs = (Object[])it.next();
            good.setCode((String)objs[0]);
            good.setNum((Integer)objs[1]);
            String[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
            good.setDetail(str[0]);
            good.setUnit(str[1]);
            l.add(good);
        }
        for(Iterator it = erplist.iterator(); it.hasNext();){
            Good good = new Good();
            Object[] objs = (Object[])it.next();
            good.setCode((String)objs[0]);
            good.setNum((Integer)objs[1]);
            good.setIs_Bom(false);
            good.setDetail((String)objs[2]);
            good.setUnit((String)objs[3]);
            l.add(good);
        }
        return l;
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
