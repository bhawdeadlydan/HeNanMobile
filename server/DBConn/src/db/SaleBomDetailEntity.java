package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/6.
 */
@Entity
@Table(name = " sale_bom_detail", schema = "mobile", catalog = "")
public class SaleBomDetailEntity {
    private String itemErpCode;
    private String itemErpName;
    private Integer itemErpQuantity;
    private String itemErpUnit;
    private String itemCode;
    private String itemName;
    private Integer configQuantity;
    private int id;
    private int saleBomsId;

    @Basic
    @Column(name = "ITEM_ERP_CODE")
    public String getItemErpCode() {
        return itemErpCode;
    }

    public void setItemErpCode(String itemErpCode) {
        this.itemErpCode = itemErpCode;
    }

    @Basic
    @Column(name = "ITEM_ERP_NAME")
    public String getItemErpName() {
        return itemErpName;
    }

    public void setItemErpName(String itemErpName) {
        this.itemErpName = itemErpName;
    }

    @Basic
    @Column(name = "ITEM_ERP_QUANTITY")
    public Integer getItemErpQuantity() {
        return itemErpQuantity;
    }

    public void setItemErpQuantity(Integer itemErpQuantity) {
        this.itemErpQuantity = itemErpQuantity;
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
    @Column(name = "CONFIG_QUANTITY")
    public Integer getConfigQuantity() {
        return configQuantity;
    }

    public void setConfigQuantity(Integer configQuantity) {
        this.configQuantity = configQuantity;
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
    @Column(name = "SALE_BOMS_ID")
    public int getSaleBomsId() {
        return saleBomsId;
    }

    public void setSaleBomsId(int saleBomsId) {
        this.saleBomsId = saleBomsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleBomDetailEntity that = (SaleBomDetailEntity) o;

        if (id != that.id) return false;
        if (saleBomsId != that.saleBomsId) return false;
        if (itemErpCode != null ? !itemErpCode.equals(that.itemErpCode) : that.itemErpCode != null) return false;
        if (itemErpName != null ? !itemErpName.equals(that.itemErpName) : that.itemErpName != null) return false;
        if (itemErpQuantity != null ? !itemErpQuantity.equals(that.itemErpQuantity) : that.itemErpQuantity != null)
            return false;
        if (itemErpUnit != null ? !itemErpUnit.equals(that.itemErpUnit) : that.itemErpUnit != null) return false;
        if (itemCode != null ? !itemCode.equals(that.itemCode) : that.itemCode != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (configQuantity != null ? !configQuantity.equals(that.configQuantity) : that.configQuantity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemErpCode != null ? itemErpCode.hashCode() : 0;
        result = 31 * result + (itemErpName != null ? itemErpName.hashCode() : 0);
        result = 31 * result + (itemErpQuantity != null ? itemErpQuantity.hashCode() : 0);
        result = 31 * result + (itemErpUnit != null ? itemErpUnit.hashCode() : 0);
        result = 31 * result + (itemCode != null ? itemCode.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (configQuantity != null ? configQuantity.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + saleBomsId;
        return result;
    }
}
