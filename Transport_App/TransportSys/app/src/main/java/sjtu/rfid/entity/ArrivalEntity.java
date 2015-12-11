package sjtu.rfid.entity;

import java.util.List;

import rfid.service.Good;

/**
 * Created by shao on 2015/12/11.
 */
public class ArrivalEntity {

    private String applyCode;
    private List<Good> goodsList;

    public ArrivalEntity(String applyCode, List<Good> goodsList) {
        this.applyCode = applyCode;
        this.goodsList = goodsList;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public List<Good> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Good> goodsList) {
        this.goodsList = goodsList;
    }
}
