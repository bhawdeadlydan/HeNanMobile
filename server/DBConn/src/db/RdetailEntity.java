package db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by richard on 2015/12/6.
 */
@Entity
@Table(name = "rdetail", schema = "mobile", catalog = "")
public class RdetailEntity {
    private String saleBomCode;
    private String itemErpCode;
    private String itemErpUnit;
    private String itemCode;
    private String itemUnit;
    private Double shipQuantity;
    private String rposCode;

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
    @Column(name = "ITEM_UNIT")
    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    @Basic
    @Column(name = "SHIP_QUANTITY")
    public Double getShipQuantity() {
        return shipQuantity;
    }

    public void setShipQuantity(Double shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    @Basic
    @Column(name = "RPOS_CODE")
    public String getRposCode() {
        return rposCode;
    }

    public void setRposCode(String rposCode) {
        this.rposCode = rposCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RdetailEntity that = (RdetailEntity) o;

        if (saleBomCode != null ? !saleBomCode.equals(that.saleBomCode) : that.saleBomCode != null) return false;
        if (itemErpCode != null ? !itemErpCode.equals(that.itemErpCode) : that.itemErpCode != null) return false;
        if (itemErpUnit != null ? !itemErpUnit.equals(that.itemErpUnit) : that.itemErpUnit != null) return false;
        if (itemCode != null ? !itemCode.equals(that.itemCode) : that.itemCode != null) return false;
        if (itemUnit != null ? !itemUnit.equals(that.itemUnit) : that.itemUnit != null) return false;
        if (shipQuantity != null ? !shipQuantity.equals(that.shipQuantity) : that.shipQuantity != null) return false;
        if (rposCode != null ? !rposCode.equals(that.rposCode) : that.rposCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleBomCode != null ? saleBomCode.hashCode() : 0;
        result = 31 * result + (itemErpCode != null ? itemErpCode.hashCode() : 0);
        result = 31 * result + (itemErpUnit != null ? itemErpUnit.hashCode() : 0);
        result = 31 * result + (itemCode != null ? itemCode.hashCode() : 0);
        result = 31 * result + (itemUnit != null ? itemUnit.hashCode() : 0);
        result = 31 * result + (shipQuantity != null ? shipQuantity.hashCode() : 0);
        result = 31 * result + (rposCode != null ? rposCode.hashCode() : 0);
        return result;
    }
}
