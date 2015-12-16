package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "rpos", schema = "mobile", catalog = "")
public class RposEntity {
    private String company;
    private String warehouseCode;
    private String billType;
    private String code;
    private String shipUser;
    private Timestamp shipDate;

    @Basic
    @Column(name = "COMPANY")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "WAREHOUSE_CODE")
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    @Basic
    @Column(name = "BILL_TYPE")
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "SHIP_USER")
    public String getShipUser() {
        return shipUser;
    }

    public void setShipUser(String shipUser) {
        this.shipUser = shipUser;
    }

    @Basic
    @Column(name = "SHIP_DATE")
    public Timestamp getShipDate() {
        return shipDate;
    }

    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RposEntity that = (RposEntity) o;

        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (warehouseCode != null ? !warehouseCode.equals(that.warehouseCode) : that.warehouseCode != null)
            return false;
        if (billType != null ? !billType.equals(that.billType) : that.billType != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (shipUser != null ? !shipUser.equals(that.shipUser) : that.shipUser != null) return false;
        if (shipDate != null ? !shipDate.equals(that.shipDate) : that.shipDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (warehouseCode != null ? warehouseCode.hashCode() : 0);
        result = 31 * result + (billType != null ? billType.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (shipUser != null ? shipUser.hashCode() : 0);
        result = 31 * result + (shipDate != null ? shipDate.hashCode() : 0);
        return result;
    }
}
