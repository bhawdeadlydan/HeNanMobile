package rfid.service;

import dao.*;
import db.*;
import org.apache.thrift.TException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by richard on 2015/12/9.
 */
public class RFIDServiceImpl implements RFIDService.Iface{
    @Override
    public List<ASN> getReceivingSheets() throws TException {
        ASNDao dao = new ASNDao();
        List list = dao.findUnPaid();
        if(list == null)
            return null;
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

    /*
    *项目编号
    *入库单号
    *物料名称
    *物料编码
    *数量
    *单位
    *epc，箱号
    *VENDOR_NAME厂商名称
    * */
    @Override
    public List<Good> getGoodsListByCode(String Code, boolean printable) throws TException {
        WMSDetailDao wdao = new WMSDetailDao();
        ASNDao adao = new ASNDao();
        boolean isbom = wdao.isBom(Code);
        ArrayList<Good> l = new ArrayList<>();
        if(isbom){
            List list = wdao.getBomDistinctGoods(Code);
            if(list == null)
                return null;
            for(Iterator it = list.iterator(); it.hasNext();){
                Object[] objs = (Object[]) it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum(Integer.parseInt(objs[1].toString()));
                good.setExpected_Quantity(Integer.parseInt(objs[2].toString()));
                good.setIs_Bom(true);
                Object[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
                good.setDetail(str[0].toString());
                good.setUnit(str[1].toString());
                good.setCartonNums(wdao.getBomCartonNumsBySaleBomCode((String)objs[0], Code));
                l.add(good);
            }
        }
        else{
            List list = wdao.getERPDistinctGoods(Code);
            if(list == null)
                return null;
            for(Iterator it = list.iterator();it.hasNext();){
                Object[] objs = (Object[])it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum(Integer.parseInt(objs[1].toString()));
                good.setExpected_Quantity(Integer.parseInt(objs[2].toString()));
                good.setIs_Bom(false);
                good.setDetail((String)objs[3]);
                good.setUnit((String)objs[4]);
                good.setCartonNums(wdao.getERPCartonNumsBySaleBomCode((String)objs[0], Code));
                l.add(good);
            }
        }
        return l;
    }

    @Override
    public boolean printTag(String Code) throws TException {
        WMSDetailDao wdao = new WMSDetailDao();
        ASNDao adao = new ASNDao();
        boolean isbom = wdao.isBom(Code);
        ArrayList<Good> l = new ArrayList<>();
        ArrayList<ArrayList<String[]>> batchGoods = new ArrayList<>();
        if(isbom){
            List list = wdao.getBomDistinctGoods(Code);
            if(list == null)
                return true;
            for(Iterator it = list.iterator(); it.hasNext();){
                Object[] objs = (Object[]) it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum(Integer.parseInt(objs[1].toString()));
                good.setExpected_Quantity(Integer.parseInt(objs[2].toString()));
                good.setIs_Bom(true);
                Object[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
                good.setDetail(str[0].toString());
                good.setUnit(str[1].toString());
                good.setCartonNums(wdao.getBomCartonNumsBySaleBomCode((String)objs[0], Code));
                l.add(good);

                //print
                ArrayList<String[]> goods = new ArrayList<>();
                List<Object[]> goodlist = wdao.getBomCartonsByItemCode((String)objs[0], Code);
                for(Iterator i = goodlist.iterator();i.hasNext();){
                    Object[] os = (Object[])i.next();
                    String[] gooditem = new String[5];
                    gooditem[0] = good.getDetail();
                    gooditem[1] = good.getCode();
                    gooditem[2] = os[1].toString();
                    gooditem[3] = good.getUnit();
                    gooditem[4] = os[0].toString();
                    goods.add(gooditem);
                }
                batchGoods.add(goods);
            }
        }
        else{
            List list = wdao.getERPDistinctGoods(Code);
            if(list == null)
                return true;
            for(Iterator it = list.iterator();it.hasNext();){
                Object[] objs = (Object[])it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum(Integer.parseInt(objs[1].toString()));
                good.setExpected_Quantity(Integer.parseInt(objs[2].toString()));
                good.setIs_Bom(false);
                good.setDetail((String)objs[3]);
                good.setUnit((String)objs[4]);
                good.setCartonNums(wdao.getERPCartonNumsBySaleBomCode((String)objs[0], Code));
                l.add(good);

                //print
                ArrayList<String[]> goods = new ArrayList<>();
                List<Object[]> goodlist = wdao.getERPCartonsByItemCode((String)objs[0], Code);
                for(Iterator i = goodlist.iterator();i.hasNext();){
                    Object[] os = (Object[])i.next();
                    String[] gooditem = new String[5];
                    gooditem[0] = good.getDetail();
                    gooditem[1] = good.getCode();
                    gooditem[2] = os[1].toString();
                    gooditem[3] = good.getUnit();
                    gooditem[4] = os[0].toString();
                    goods.add(gooditem);
                }
                batchGoods.add(goods);
            }
        }
        Object[] info = adao.getASNInfo(Code);
        new Thread(new PrintThread(info[0].toString(),Code,info[1].toString(), batchGoods)).start();
        return true;
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
        List list = dao.findUnSent();
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
    public List<POS> getOutApplySheets() throws TException {
        PosDao dao = new PosDao();
        List list = dao.findSent();
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
    public POS getPOSInfoByApplyDocCode(String ApplyDocCode) throws TException {
        PosDao dao = new PosDao();
        POS pos = new POS();
        List list = dao.findOne(ApplyDocCode);
        if(list != null){
            Iterator it = list.iterator();
            PosEntity entity = (PosEntity) it.next();
            pos.setApply_Doc_Code(entity.getApplyDocCode());
            pos.setApply_Person(entity.getApplyPerson());
            pos.setApply_Unit(entity.getApplyUnit());
            pos.setProject_Code(entity.getProjectCode());
            pos.setApply_Date(entity.getApplyDate().toString());
            pos.setReceiver(entity.getReceiver());
        }
        return pos;
    }

    @Override
    public List<Good> getGoodsListByApplyDocCode(String ApplyDocCode) throws TException {
        DetailDao ddao = new DetailDao();
        ASNDao adao = new ASNDao();
        WMSDetailDao wdao = new WMSDetailDao();
        boolean isbom = ddao.isBom(ApplyDocCode);
        ArrayList<Good> l = new ArrayList<>();
        if(isbom) {
            List list = ddao.getBomDistinctGoods(ApplyDocCode);
            for(Iterator it = list.iterator(); it.hasNext();){
                Object[] objs = (Object[]) it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum(Integer.parseInt(objs[1].toString()));
                good.setIs_Bom(true);
                Object[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
                good.setDetail(str[0].toString());
                good.setUnit(str[1].toString());
                l.add(good);
            }
        }
        else{
            List list = ddao.getERPDistinctGoods(ApplyDocCode);
            String detail = "";
            for(Iterator it = list.iterator();it.hasNext();){
                Object[] objs = (Object[])it.next();
                Good good = new Good();
                good.setCode((String)objs[0]);
                good.setNum(Integer.parseInt(objs[1].toString()));
                good.setIs_Bom(false);
                detail = wdao.getERPDetailByERPCode((String)objs[0]);
                good.setDetail(detail);
                good.setUnit((String)objs[3]);
                l.add(good);
            }
        }
        return l;
    }

    @Override
    public boolean confirmRetrieval(String ApplyDocCode, List<String> CNums) throws TException {
        PosDao pdao = new PosDao();
        pdao.Retrieval(ApplyDocCode, 1);
        WMSDetailDao wdao = new WMSDetailDao();
        for(Iterator<String> it = CNums.iterator(); it.hasNext();){
            String CNum = it.next();
            wdao.bind(-1, CNum);
            wdao.bindGoodAndApplyDocCode(CNum,ApplyDocCode);
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
        String salebomcode = objs[0].toString();
        String itemcode = objs[1].toString();
        String isbom = objs[2].toString();
        String itemname = objs[3].toString();
        Integer num = Integer.parseInt(objs[4].toString());
        String asncode = objs[6].toString();
        good.setNum(num);
        good.setInCode(asncode);
        good.setProjectCode(adao.getProjectCodeByCode(asncode));
        boolean bom = isbom.toUpperCase().equals("Y");
        if(bom) {
            good.setCode(salebomcode);
            Object[] str = adao.getDesAndUnitBySaleBomCode(salebomcode);
            good.setDetail(str[0].toString());
            good.setUnit(str[1].toString());
            good.setIs_Bom(true);
        }
        else{
            good.setCode(itemcode);
            good.setDetail(itemname);
            good.setIs_Bom(false);
            good.setUnit(objs[5].toString());
        }
        return good;
    }

    @Override
    public List<LocationInfo> getLocationListByItemErpCode(String ItemERPCode, boolean isBom) throws TException {
        WMSDetailDao dao = new WMSDetailDao();
        AllocationDao adao = new AllocationDao();
        List<Object[]> l;
        if(isBom)
            l = dao.getLocationIDsByBomCode(ItemERPCode);
        else
            l = dao.getLocationIDsByItemERPCode(ItemERPCode);
        if(l == null)
            return null;
        ArrayList<LocationInfo> list = new ArrayList<>();
        for(Iterator it = l.iterator(); it.hasNext();){
            Object[] objs = (Object[])it.next();
            LocationInfo linfo = new LocationInfo();
            int locationID = Integer.parseInt(objs[0].toString());
            linfo.setID(locationID);
            linfo.setNum(Integer.parseInt(objs[1].toString()));
            Object[] obs = adao.getLocationInfoByID(linfo.getID());
            linfo.setArea("");
            linfo.setLocation(0);
            linfo.setCartonNums(null);
            if(obs != null){
                linfo.setArea(obs[0].toString());
                linfo.setLocation(Integer.parseInt(obs[1].toString()));
                linfo.setCartonNums(isBom ? dao.getCNumsByLocationAndBomCode(ItemERPCode, locationID) :
                        dao.getCNumsByLocationAndItemCode(ItemERPCode, locationID));
            }
            list.add(linfo);
        }
        return list;
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
            good.setNum(Integer.parseInt(objs[1].toString()));
            Object[] str = adao.getDesAndUnitBySaleBomCode((String)objs[0]);
            good.setDetail(str[0].toString());
            good.setUnit(str[1].toString());
            good.setCartonNums(wdao.getBomCartonNumsBySaleBomCode1(good.getCode(), Location));
            l.add(good);
        }
        for(Iterator it = erplist.iterator(); it.hasNext();){
            Good good = new Good();
            Object[] objs = (Object[])it.next();
            good.setCode((String)objs[0]);
            good.setNum(Integer.parseInt(objs[1].toString()));
            good.setIs_Bom(false);
            good.setDetail((String)objs[2]);
            good.setUnit((String)objs[3]);
            good.setCartonNums(wdao.getERPCartonNumsBySaleBomCode1(good.getCode(),Location));
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
    public boolean confirmInventory(List<check> checks) throws TException {
        if(checks.isEmpty())
            return true;
        CheckDao dao = new CheckDao();
        for(Iterator it = checks.iterator();it.hasNext();){
            check c = (check)it.next();
            CheckEntity ch = new CheckEntity();
            ch.setMaterialCode(c.getMaterialCode());
            ch.setPosition(c.getLocation());
            ch.setRealNum(c.getRealNum());
            Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(c.getTime());
                timestamp = new java.sql.Timestamp(parsedDate.getTime());

            }catch(Exception e){
                System.err.println(e.getMessage());
//                e.printStackTrace();
                return false;
            }
            ch.setTime(timestamp);
            dao.addEntity(ch);
        }
        return true;
    }

    @Override
    public boolean confirmArrive(String charge, String Time, String Position, String Type, String PosApplyDocCode, double longitude, double latitude) throws TException {
        TransportEntity transport = new TransportEntity();
        transport.setCharge(charge);
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(Time);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());

        }catch(Exception e){
//            e.printStackTrace();
            System.err.println(e.getMessage());
            return false;
        }
        transport.setTime(timestamp);
        transport.setPosition(Position);
        transport.setType(Type);
        transport.setPosApplyDocCode(PosApplyDocCode);
        transport.setLongitude(longitude);
        transport.setLatitude(latitude);
        TransportDao dao = new TransportDao();
        dao.addEntity(transport);
        PosDao pdao = new PosDao();
        pdao.Retrieval(PosApplyDocCode, 2);
        return true;
    }

    @Override
    public boolean stagingSiteCheckout(String applyPerson, String constructPos, String constructUnit, String materialCode, int num) throws TException {
        StagingSiteDao dao = new StagingSiteDao();
        StagingsiteEntity stagingSite = new StagingsiteEntity();
        stagingSite.setApplyPerson(applyPerson);
        stagingSite.setConstructPos(constructPos);
        stagingSite.setConstructUnit(constructUnit);
        stagingSite.setMaterialCode(materialCode);
        stagingSite.setNum(num);
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        stagingSite.setTime(timestamp);
        dao.addEntity(stagingSite);
        return true;
    }

    @Override
    public List<transportInfo> getTransportInfo() throws TException {
        TransportDao dao = new TransportDao();
        List<Object[]> list = dao.getTransportInfo();
        ArrayList<transportInfo> l = new ArrayList<>();
        for(Iterator it = list.iterator(); it.hasNext();){
            Object[] objs = (Object[]) it.next();
            transportInfo tInfo = new transportInfo();
            tInfo.setTime("");
            tInfo.setPosition("");
            tInfo.setPerson("");
            tInfo.setConstructUnit("");
            if(objs[0] != null)
                tInfo.setTime(objs[0].toString());
            if(objs[1] != null)
                tInfo.setPosition(objs[1].toString());
            if(objs[2] != null)
                tInfo.setPerson(objs[2].toString());
            if(objs[3] != null)
                tInfo.setConstructUnit(objs[3].toString());
            l.add(tInfo);
        }
        return l;
    }

    @Override
    public List<stagingInfo> getStagingInfo(String constructUnit) throws TException {
        StagingSiteDao dao = new StagingSiteDao();
        List list = dao.getStagingSiteInfo(constructUnit);
        ArrayList<stagingInfo> l = new ArrayList<>();
        for(Iterator it = list.iterator();it.hasNext();){
            StagingsiteEntity stagingsite = (StagingsiteEntity)it.next();
            stagingInfo sInfo = new stagingInfo();
            sInfo.setApplyPerson(stagingsite.getApplyPerson());
            sInfo.setConstructPos(stagingsite.getConstructPos());
            sInfo.setConstructUnit(stagingsite.getConstructUnit());
            sInfo.setTime(stagingsite.getTime().toString());
            sInfo.setMaterialCode(stagingsite.getMaterialCode());
            sInfo.setNum(stagingsite.getNum());
            l.add(sInfo);
        }
        return l;
    }

}
