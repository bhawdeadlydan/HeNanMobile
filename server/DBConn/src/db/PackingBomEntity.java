package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/6.
 */
@Entity
@Table(name = "packing_bom", schema = "mobile", catalog = "")
public class PackingBomEntity {
    private String packingBomCode;
    private String packingType;
    private String vendorCode;
    private String vendorName;
    private int id;
    private int bomId;

    @Basic
    @Column(name = "PACKING_BOM_CODE")
    public String getPackingBomCode() {
        return packingBomCode;
    }

    public void setPackingBomCode(String packingBomCode) {
        this.packingBomCode = packingBomCode;
    }

    @Basic
    @Column(name = "PACKING_TYPE")
    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    @Basic
    @Column(name = "VENDOR_CODE")
    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @Basic
    @Column(name = "VENDOR_NAME")
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
    @Column(name = "BOM_ID")
    public int getBomId() {
        return bomId;
    }

    public void setBomId(int bomId) {
        this.bomId = bomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackingBomEntity that = (PackingBomEntity) o;

        if (id != that.id) return false;
        if (bomId != that.bomId) return false;
        if (packingBomCode != null ? !packingBomCode.equals(that.packingBomCode) : that.packingBomCode != null)
            return false;
        if (packingType != null ? !packingType.equals(that.packingType) : that.packingType != null) return false;
        if (vendorCode != null ? !vendorCode.equals(that.vendorCode) : that.vendorCode != null) return false;
        if (vendorName != null ? !vendorName.equals(that.vendorName) : that.vendorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packingBomCode != null ? packingBomCode.hashCode() : 0;
        result = 31 * result + (packingType != null ? packingType.hashCode() : 0);
        result = 31 * result + (vendorCode != null ? vendorCode.hashCode() : 0);
        result = 31 * result + (vendorName != null ? vendorName.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + bomId;
        return result;
    }
}
