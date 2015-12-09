package rfid.service;

import org.apache.thrift.TException;

import java.util.List;

/**
 * Created by richard on 2015/12/9.
 */
public class RFIDServiceImpl implements RFIDService.Iface{
    @Override
    public List<ASN> getReceivingSheets() throws TException {
        return null;
    }

    @Override
    public List<Good> getGoodsListByCode(String Code) throws TException {
        return null;
    }

    @Override
    public String getCodeByCNum(String CNum) throws TException {
        System.out.println(CNum);
        return "123";
    }

    @Override
    public boolean bindLocationAndGoods(int LocationID, List<String> CNums) throws TException {
        return false;
    }

    @Override
    public List<POS> getApplySheets() throws TException {
        return null;
    }

    @Override
    public List<Good> getGoodsListByApplyDocCode(String ApplyDocCode) throws TException {
        return null;
    }

    @Override
    public boolean confirmRetrieval(String ApplyDocCode, List<String> CNums) throws TException {
        return false;
    }

    @Override
    public boolean confirmReceiving(String Code) throws TException {
        return false;
    }

    @Override
    public Good getGoodByCNum(String CNum) throws TException {
        return null;
    }

    @Override
    public List<Integer> getLocationListByItemErpCode(String ItemERPCode) throws TException {
        return null;
    }

    @Override
    public List<Good> getGoodsByLocation(int Location) throws TException {
        return null;
    }

    @Override
    public String getApplyDocCodeByCNum(String CNum) throws TException {
        return null;
    }

    @Override
    public boolean confirmInventory(String Location, String MaterialCode, int RealNum, String Time) throws TException {
        return false;
    }

    @Override
    public boolean confirmArrive(String charge, String Time, String Position, String Type, String PosApplyDocCode, double longitude, double latitude) throws TException {
        return false;
    }
}
