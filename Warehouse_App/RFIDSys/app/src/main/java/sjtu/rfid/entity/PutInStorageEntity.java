package sjtu.rfid.entity;

import rfid.service.Good;

/**
 * Created by shao on 2015/12/16.
 */
public class PutInStorageEntity {

    private Good good;
    private String asnCode;
    private String epc;

    public PutInStorageEntity(Good good, String asnCode,String epc) {
        this.good = good;
        this.asnCode = asnCode;
        this.epc = epc;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    public String getEPC() { return epc; }

    public void setEpc(String epc) { this.epc = epc; }
}
