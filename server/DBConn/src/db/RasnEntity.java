package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "rasn", schema = "mobile", catalog = "")
public class RasnEntity {
    private String company;
    private String warehouseCode;
    private String billType;
    private String code;
    private String receiveType;
    private String receiveUser;
    private Timestamp receiveDate;
    private String rasNcol;

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
    @Column(name = "RECEIVE_TYPE")
    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    @Basic
    @Column(name = "RECEIVE_USER")
    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
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
    @Column(name = "RASNcol")
    public String getRasNcol() {
        return rasNcol;
    }

    public void setRasNcol(String rasNcol) {
        this.rasNcol = rasNcol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RasnEntity that = (RasnEntity) o;

        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (warehouseCode != null ? !warehouseCode.equals(that.warehouseCode) : that.warehouseCode != null)
            return false;
        if (billType != null ? !billType.equals(that.billType) : that.billType != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (receiveType != null ? !receiveType.equals(that.receiveType) : that.receiveType != null) return false;
        if (receiveUser != null ? !receiveUser.equals(that.receiveUser) : that.receiveUser != null) return false;
        if (receiveDate != null ? !receiveDate.equals(that.receiveDate) : that.receiveDate != null) return false;
        if (rasNcol != null ? !rasNcol.equals(that.rasNcol) : that.rasNcol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (warehouseCode != null ? warehouseCode.hashCode() : 0);
        result = 31 * result + (billType != null ? billType.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (receiveType != null ? receiveType.hashCode() : 0);
        result = 31 * result + (receiveUser != null ? receiveUser.hashCode() : 0);
        result = 31 * result + (receiveDate != null ? receiveDate.hashCode() : 0);
        result = 31 * result + (rasNcol != null ? rasNcol.hashCode() : 0);
        return result;
    }
}
