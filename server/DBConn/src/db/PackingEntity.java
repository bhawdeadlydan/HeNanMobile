package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "packing", schema = "mobile", catalog = "")
public class PackingEntity {
    private String packingCode;
    private String packingName;
    private Integer packingCount;
    private String packingLenUnit;
    private Double packingLen;
    private Double packingWidth;
    private Double packingHeight;
    private String packingCapacityUom;
    private Double packingCapacity;
    private String packingWeightUom;
    private Double packingWeight;
    private int packingBomId;
    private int id;

    @Basic
    @Column(name = "PACKING_CODE")
    public String getPackingCode() {
        return packingCode;
    }

    public void setPackingCode(String packingCode) {
        this.packingCode = packingCode;
    }

    @Basic
    @Column(name = "PACKING_NAME")
    public String getPackingName() {
        return packingName;
    }

    public void setPackingName(String packingName) {
        this.packingName = packingName;
    }

    @Basic
    @Column(name = "PACKING_COUNT")
    public Integer getPackingCount() {
        return packingCount;
    }

    public void setPackingCount(Integer packingCount) {
        this.packingCount = packingCount;
    }

    @Basic
    @Column(name = "PACKING_LEN_UNIT")
    public String getPackingLenUnit() {
        return packingLenUnit;
    }

    public void setPackingLenUnit(String packingLenUnit) {
        this.packingLenUnit = packingLenUnit;
    }

    @Basic
    @Column(name = "PACKING_LEN")
    public Double getPackingLen() {
        return packingLen;
    }

    public void setPackingLen(Double packingLen) {
        this.packingLen = packingLen;
    }

    @Basic
    @Column(name = "PACKING_WIDTH")
    public Double getPackingWidth() {
        return packingWidth;
    }

    public void setPackingWidth(Double packingWidth) {
        this.packingWidth = packingWidth;
    }

    @Basic
    @Column(name = "PACKING_HEIGHT")
    public Double getPackingHeight() {
        return packingHeight;
    }

    public void setPackingHeight(Double packingHeight) {
        this.packingHeight = packingHeight;
    }

    @Basic
    @Column(name = "PACKING_CAPACITY_UOM")
    public String getPackingCapacityUom() {
        return packingCapacityUom;
    }

    public void setPackingCapacityUom(String packingCapacityUom) {
        this.packingCapacityUom = packingCapacityUom;
    }

    @Basic
    @Column(name = "PACKING_CAPACITY")
    public Double getPackingCapacity() {
        return packingCapacity;
    }

    public void setPackingCapacity(Double packingCapacity) {
        this.packingCapacity = packingCapacity;
    }

    @Basic
    @Column(name = "PACKING_WEIGHT_UOM")
    public String getPackingWeightUom() {
        return packingWeightUom;
    }

    public void setPackingWeightUom(String packingWeightUom) {
        this.packingWeightUom = packingWeightUom;
    }

    @Basic
    @Column(name = "PACKING_WEIGHT")
    public Double getPackingWeight() {
        return packingWeight;
    }

    public void setPackingWeight(Double packingWeight) {
        this.packingWeight = packingWeight;
    }

    @Basic
    @Column(name = "PACKING_BOM_ID")
    public int getPackingBomId() {
        return packingBomId;
    }

    public void setPackingBomId(int packingBomId) {
        this.packingBomId = packingBomId;
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

        PackingEntity that = (PackingEntity) o;

        if (packingBomId != that.packingBomId) return false;
        if (id != that.id) return false;
        if (packingCode != null ? !packingCode.equals(that.packingCode) : that.packingCode != null) return false;
        if (packingName != null ? !packingName.equals(that.packingName) : that.packingName != null) return false;
        if (packingCount != null ? !packingCount.equals(that.packingCount) : that.packingCount != null) return false;
        if (packingLenUnit != null ? !packingLenUnit.equals(that.packingLenUnit) : that.packingLenUnit != null)
            return false;
        if (packingLen != null ? !packingLen.equals(that.packingLen) : that.packingLen != null) return false;
        if (packingWidth != null ? !packingWidth.equals(that.packingWidth) : that.packingWidth != null) return false;
        if (packingHeight != null ? !packingHeight.equals(that.packingHeight) : that.packingHeight != null)
            return false;
        if (packingCapacityUom != null ? !packingCapacityUom.equals(that.packingCapacityUom) : that.packingCapacityUom != null)
            return false;
        if (packingCapacity != null ? !packingCapacity.equals(that.packingCapacity) : that.packingCapacity != null)
            return false;
        if (packingWeightUom != null ? !packingWeightUom.equals(that.packingWeightUom) : that.packingWeightUom != null)
            return false;
        if (packingWeight != null ? !packingWeight.equals(that.packingWeight) : that.packingWeight != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packingCode != null ? packingCode.hashCode() : 0;
        result = 31 * result + (packingName != null ? packingName.hashCode() : 0);
        result = 31 * result + (packingCount != null ? packingCount.hashCode() : 0);
        result = 31 * result + (packingLenUnit != null ? packingLenUnit.hashCode() : 0);
        result = 31 * result + (packingLen != null ? packingLen.hashCode() : 0);
        result = 31 * result + (packingWidth != null ? packingWidth.hashCode() : 0);
        result = 31 * result + (packingHeight != null ? packingHeight.hashCode() : 0);
        result = 31 * result + (packingCapacityUom != null ? packingCapacityUom.hashCode() : 0);
        result = 31 * result + (packingCapacity != null ? packingCapacity.hashCode() : 0);
        result = 31 * result + (packingWeightUom != null ? packingWeightUom.hashCode() : 0);
        result = 31 * result + (packingWeight != null ? packingWeight.hashCode() : 0);
        result = 31 * result + packingBomId;
        result = 31 * result + id;
        return result;
    }
}
