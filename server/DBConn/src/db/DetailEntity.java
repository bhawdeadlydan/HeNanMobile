package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "detail", schema = "mobile", catalog = "")
public class DetailEntity {
    private String isBom;
    private String saleBomCode;
    private String itemErpCode;
    private String itemErpUnit;
    private String itemCode;
    private String itemName;
    private String itemUnitCode;
    private Integer expectedQuantity;
    private String posApplyDocCode;
    private int id;

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
    @Column(name = "ITEM_ERP_CODE")
    public String getItemErpCode() {
        return itemErpCode;
    }

    public void setItemErpCode(String itemErpCode) {
        this.itemErpCode = itemErpCode;
    }

    @Basic
    @Column(name = "ITEM_ERP_UNIT")
    public String getItemErpUnit() {
        return itemErpUnit;
    }

    public void setItemErpUnit(String itemErpUnit) {
        this.itemErpUnit = itemErpUnit;
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
    @Column(name = "POS_APPLY_DOC_CODE")
    public String getPosApplyDocCode() {
        return posApplyDocCode;
    }

    public void setPosApplyDocCode(String posApplyDocCode) {
        this.posApplyDocCode = posApplyDocCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        DetailEntity that = (DetailEntity) o;

        if (id != that.id) return false;
        if (isBom != null ? !isBom.equals(that.isBom) : that.isBom != null) return false;
        if (saleBomCode != null ? !saleBomCode.equals(that.saleBomCode) : that.saleBomCode != null) return false;
        if (itemErpCode != null ? !itemErpCode.equals(that.itemErpCode) : that.itemErpCode != null) return false;
        if (itemErpUnit != null ? !itemErpUnit.equals(that.itemErpUnit) : that.itemErpUnit != null) return false;
        if (itemCode != null ? !itemCode.equals(that.itemCode) : that.itemCode != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemUnitCode != null ? !itemUnitCode.equals(that.itemUnitCode) : that.itemUnitCode != null) return false;
        if (expectedQuantity != null ? !expectedQuantity.equals(that.expectedQuantity) : that.expectedQuantity != null)
            return false;
        if (posApplyDocCode != null ? !posApplyDocCode.equals(that.posApplyDocCode) : that.posApplyDocCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isBom != null ? isBom.hashCode() : 0;
        result = 31 * result + (saleBomCode != null ? saleBomCode.hashCode() : 0);
        result = 31 * result + (itemErpCode != null ? itemErpCode.hashCode() : 0);
        result = 31 * result + (itemErpUnit != null ? itemErpUnit.hashCode() : 0);
        result = 31 * result + (itemCode != null ? itemCode.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemUnitCode != null ? itemUnitCode.hashCode() : 0);
        result = 31 * result + (expectedQuantity != null ? expectedQuantity.hashCode() : 0);
        result = 31 * result + (posApplyDocCode != null ? posApplyDocCode.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
