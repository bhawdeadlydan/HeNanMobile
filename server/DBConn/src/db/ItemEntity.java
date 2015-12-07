package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "item", schema = "mobile", catalog = "")
public class ItemEntity {
    private String itemCode;
    private String itemName;
    private Integer configQuantity;
    private String itemUnit;
    private String iteMcol;
    private int saleBomDetailId;
    private int id;

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

    @Basic
    @Column(name = "ITEM_UNIT")
    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    @Basic
    @Column(name = "ITEMcol")
    public String getIteMcol() {
        return iteMcol;
    }

    public void setIteMcol(String iteMcol) {
        this.iteMcol = iteMcol;
    }

    @Basic
    @Column(name = " SALE_BOM_DETAIL_ID")
    public int getSaleBomDetailId() {
        return saleBomDetailId;
    }

    public void setSaleBomDetailId(int saleBomDetailId) {
        this.saleBomDetailId = saleBomDetailId;
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

        ItemEntity that = (ItemEntity) o;

        if (saleBomDetailId != that.saleBomDetailId) return false;
        if (id != that.id) return false;
        if (itemCode != null ? !itemCode.equals(that.itemCode) : that.itemCode != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (configQuantity != null ? !configQuantity.equals(that.configQuantity) : that.configQuantity != null)
            return false;
        if (itemUnit != null ? !itemUnit.equals(that.itemUnit) : that.itemUnit != null) return false;
        if (iteMcol != null ? !iteMcol.equals(that.iteMcol) : that.iteMcol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemCode != null ? itemCode.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (configQuantity != null ? configQuantity.hashCode() : 0);
        result = 31 * result + (itemUnit != null ? itemUnit.hashCode() : 0);
        result = 31 * result + (iteMcol != null ? iteMcol.hashCode() : 0);
        result = 31 * result + saleBomDetailId;
        result = 31 * result + id;
        return result;
    }
}
