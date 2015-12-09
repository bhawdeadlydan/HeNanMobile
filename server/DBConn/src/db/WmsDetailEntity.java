package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/9.
 */
@Entity
@Table(name = "wms_detail", schema = "mobile", catalog = "")
public class WmsDetailEntity {
    private String isBom;
    private String saleBomCode;
    private String packingCode;
    private Double price;
    private String cartonOrderNum;
    private String cartonNum;
    private String itemCode;
    private String itemName;
    private String itemUnitCode;
    private Integer expectedQuantity;
    private String asnCode;
    private Integer allocationId;
    private int id;
    private String cNum;
    private String applyDocCode;

    @Basic
    @Column(name = "IS_BOM")
    public String getIsBom() {
        return isBom;
    }

    public void setIsBom(String isBom) {
        this.isBom = isBom;
    }

    @Basic
    @Column(name = "SALE_BOM_CODE")
    public String getSaleBomCode() {
        return saleBomCode;
    }

    public void setSaleBomCode(String saleBomCode) {
        this.saleBomCode = saleBomCode;
    }

    @Basic
    @Column(name = "PACKING_CODE")
    public String getPackingCode() {
        return packingCode;
    }

    public void setPackingCode(String packingCode) {
        this.packingCode = packingCode;
    }

    @Basic
    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "CARTON_ORDER_NUM")
    public String getCartonOrderNum() {
        return cartonOrderNum;
    }

    public void setCartonOrderNum(String cartonOrderNum) {
        this.cartonOrderNum = cartonOrderNum;
    }

    @Basic
    @Column(name = "CARTON_NUM")
    public String getCartonNum() {
        return cartonNum;
    }

    public void setCartonNum(String cartonNum) {
        this.cartonNum = cartonNum;
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
    @Column(name = "EXPECTED_QUANTITY")
    public Integer getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(Integer expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    @Basic
    @Column(name = "ASN_CODE")
    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    @Basic
    @Column(name = "ALLOCATION_id")
    public Integer getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(Integer allocationId) {
        this.allocationId = allocationId;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CartonNum")
    public String getcNum() {
        return cNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    @Basic
    @Column(name = "ApplyDocCode")
    public String getApplyDocCode() {
        return applyDocCode;
    }

    public void setApplyDocCode(String applyDocCode) {
        this.applyDocCode = applyDocCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WmsDetailEntity that = (WmsDetailEntity) o;

        if (id != that.id) return false;
        if (isBom != null ? !isBom.equals(that.isBom) : that.isBom != null) return false;
        if (saleBomCode != null ? !saleBomCode.equals(that.saleBomCode) : that.saleBomCode != null) return false;
        if (packingCode != null ? !packingCode.equals(that.packingCode) : that.packingCode != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (cartonOrderNum != null ? !cartonOrderNum.equals(that.cartonOrderNum) : that.cartonOrderNum != null)
            return false;
        if (cartonNum != null ? !cartonNum.equals(that.cartonNum) : that.cartonNum != null) return false;
        if (itemCode != null ? !itemCode.equals(that.itemCode) : that.itemCode != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemUnitCode != null ? !itemUnitCode.equals(that.itemUnitCode) : that.itemUnitCode != null) return false;
        if (expectedQuantity != null ? !expectedQuantity.equals(that.expectedQuantity) : that.expectedQuantity != null)
            return false;
        if (asnCode != null ? !asnCode.equals(that.asnCode) : that.asnCode != null) return false;
        if (allocationId != null ? !allocationId.equals(that.allocationId) : that.allocationId != null) return false;
        if (cNum != null ? !cNum.equals(that.cNum) : that.cNum != null) return false;
        if (applyDocCode != null ? !applyDocCode.equals(that.applyDocCode) : that.applyDocCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isBom != null ? isBom.hashCode() : 0;
        result = 31 * result + (saleBomCode != null ? saleBomCode.hashCode() : 0);
        result = 31 * result + (packingCode != null ? packingCode.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (cartonOrderNum != null ? cartonOrderNum.hashCode() : 0);
        result = 31 * result + (cartonNum != null ? cartonNum.hashCode() : 0);
        result = 31 * result + (itemCode != null ? itemCode.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemUnitCode != null ? itemUnitCode.hashCode() : 0);
        result = 31 * result + (expectedQuantity != null ? expectedQuantity.hashCode() : 0);
        result = 31 * result + (asnCode != null ? asnCode.hashCode() : 0);
        result = 31 * result + (allocationId != null ? allocationId.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (cNum != null ? cNum.hashCode() : 0);
        result = 31 * result + (applyDocCode != null ? applyDocCode.hashCode() : 0);
        return result;
    }
}
