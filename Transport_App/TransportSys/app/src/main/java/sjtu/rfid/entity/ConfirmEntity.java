package sjtu.rfid.entity;

import java.util.List;

import rfid.service.ASN;
import rfid.service.Good;
import rfid.service.POS;

/**
 * Created by shao on 2015/12/11.
 */
public class ConfirmEntity {

    private POS pos;
    private List<Good> goodsList;

    public ConfirmEntity(POS pos, List<Good> goodsList) {
        this.pos = pos;
        this.goodsList = goodsList;
    }


    public POS getPos() {
        return pos;
    }

    public void setPos(POS pos) {
        this.pos = pos;
    }

    public List<Good> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Good> goodsList) {
        this.goodsList = goodsList;
    }
}
