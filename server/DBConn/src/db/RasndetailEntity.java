package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "rasndetail", schema = "mobile", catalog = "")
public class RasndetailEntity {
    private String cartonOrderNum;
    private Timestamp receiveDate;
    private String itemCode;
    private String itemName;
    private String itemUnitCode;
    private Integer receiveQuantity;
    private int id;

    @Basic
    @Column(name = "CARTON_ORDER_NUM")
    public String getCartonOrderNum() {
        return cartonOrderNum;
    }

    public void setCartonOrderNum(String cartonOrderNum) {
        this.cartonOrderNum = cartonOrderNum;
    }

    @Basic
    @Column(name = "RECEIVE_DATE")
    public Timestamp getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Timestamp receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Basic
    @Column(name = "ITEM_CODE")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Basic
    @Column(name = "ITEM_NAME")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "ITEM_UNIT_CODE")
    public String getItemUnitCode() {
        return itemUnitCode;
    }

    public void setItemUnitCode(String itemUnitCode) {
        this.itemUnitCode = itemUnitCode;
    }

    @Basic
    @Column(name = "RECEIVE_QUANTITY")
    public Integer getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(Integer receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RasndetailEntity that = (RasndetailEntity) o;

        if (id != that.id) return false;
        if (cartonOrderNum != null ? !cartonOrderNum.equals(that.cartonOrderNum) : that.cartonOrderNum != null)
            return false;
        if (receiveDate != null ? !receiveDate.equals(that.receiveDate) : that.receiveDate != null) return false;
        if (itemCode != null ? !itemCode.equals(that.itemCode) : that.itemCode != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemUnitCode != null ? !itemUnitCode.equals(that.itemUnitCode) : that.itemUnitCode != null) return false;
        if (receiveQuantity != null ? !receiveQuantity.equals(that.receiveQuantity) : that.receiveQuantity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartonOrderNum != null ? cartonOrderNum.hashCode() : 0;
        result = 31 * result + (receiveDate != null ? receiveDate.hashCode() : 0);
        result = 31 * result + (itemCode != null ? itemCode.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemUnitCode != null ? itemUnitCode.hashCode() : 0);
        result = 31 * result + (receiveQuantity != null ? receiveQuantity.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
