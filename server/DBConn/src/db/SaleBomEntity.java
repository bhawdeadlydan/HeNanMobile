package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "sale_bom", schema = "mobile", catalog = "")
public class SaleBomEntity {
    private String saleBomCode;
    private String saleBomName;
    private String saleBomUnit;
    private int id;
    private int bomId;

    @Basic
    @Column(name = "SALE_BOM_CODE")
    public String getSaleBomCode() {
        return saleBomCode;
    }

    public void setSaleBomCode(String saleBomCode) {
        this.saleBomCode = saleBomCode;
    }

    @Basic
    @Column(name = "SALE_BOM_NAME")
    public String getSaleBomName() {
        return saleBomName;
    }

    public void setSaleBomName(String saleBomName) {
        this.saleBomName = saleBomName;
    }

    @Basic
    @Column(name = "SALE_BOM_UNIT")
    public String getSaleBomUnit() {
        return saleBomUnit;
    }

    public void setSaleBomUnit(String saleBomUnit) {
        this.saleBomUnit = saleBomUnit;
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

        SaleBomEntity that = (SaleBomEntity) o;

        if (id != that.id) return false;
        if (bomId != that.bomId) return false;
        if (saleBomCode != null ? !saleBomCode.equals(that.saleBomCode) : that.saleBomCode != null) return false;
        if (saleBomName != null ? !saleBomName.equals(that.saleBomName) : that.saleBomName != null) return false;
        if (saleBomUnit != null ? !saleBomUnit.equals(that.saleBomUnit) : that.saleBomUnit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleBomCode != null ? saleBomCode.hashCode() : 0;
        result = 31 * result + (saleBomName != null ? saleBomName.hashCode() : 0);
        result = 31 * result + (saleBomUnit != null ? saleBomUnit.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + bomId;
        return result;
    }
}
