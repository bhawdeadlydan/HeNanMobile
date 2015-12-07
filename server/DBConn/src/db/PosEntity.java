package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "pos", schema = "mobile", catalog = "")
public class PosEntity {
    private String company;
    private String warehouseCode;
    private String billType;
    private String code;
    private String applyDocCode;
    private String projectCode;
    private String applyUnit;
    private String applyPerson;
    private Timestamp applyDate;
    private String constructionUnit;
    private String receiver;
    private String receiverUidCode;
    private String applyDocDesc;
    private Timestamp expectedShipDate;
    private String dockCode;
    private String disposition;

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

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "APPLY_DOC_CODE")
    public String getApplyDocCode() {
        return applyDocCode;
    }

    public void setApplyDocCode(String applyDocCode) {
        this.applyDocCode = applyDocCode;
    }

    @Basic
    @Column(name = "PROJECT_CODE")
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "APPLY_UNIT")
    public String getApplyUnit() {
        return applyUnit;
    }

    public void setApplyUnit(String applyUnit) {
        this.applyUnit = applyUnit;
    }

    @Basic
    @Column(name = "APPLY_PERSON")
    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    @Basic
    @Column(name = "APPLY_DATE")
    public Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    @Basic
    @Column(name = "CONSTRUCTION_UNIT")
    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    @Basic
    @Column(name = "RECEIVER")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "RECEIVER_UID_CODE")
    public String getReceiverUidCode() {
        return receiverUidCode;
    }

    public void setReceiverUidCode(String receiverUidCode) {
        this.receiverUidCode = receiverUidCode;
    }

    @Basic
    @Column(name = "APPLY_DOC_DESC")
    public String getApplyDocDesc() {
        return applyDocDesc;
    }

    public void setApplyDocDesc(String applyDocDesc) {
        this.applyDocDesc = applyDocDesc;
    }

    @Basic
    @Column(name = "EXPECTED_SHIP_DATE")
    public Timestamp getExpectedShipDate() {
        return expectedShipDate;
    }

    public void setExpectedShipDate(Timestamp expectedShipDate) {
        this.expectedShipDate = expectedShipDate;
    }

    @Basic
    @Column(name = "DOCK_CODE")
    public String getDockCode() {
        return dockCode;
    }

    public void setDockCode(String dockCode) {
        this.dockCode = dockCode;
    }

    @Basic
    @Column(name = "DISPOSITION")
    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosEntity posEntity = (PosEntity) o;

        if (company != null ? !company.equals(posEntity.company) : posEntity.company != null) return false;
        if (warehouseCode != null ? !warehouseCode.equals(posEntity.warehouseCode) : posEntity.warehouseCode != null)
            return false;
        if (billType != null ? !billType.equals(posEntity.billType) : posEntity.billType != null) return false;
        if (code != null ? !code.equals(posEntity.code) : posEntity.code != null) return false;
        if (applyDocCode != null ? !applyDocCode.equals(posEntity.applyDocCode) : posEntity.applyDocCode != null)
            return false;
        if (projectCode != null ? !projectCode.equals(posEntity.projectCode) : posEntity.projectCode != null)
            return false;
        if (applyUnit != null ? !applyUnit.equals(posEntity.applyUnit) : posEntity.applyUnit != null) return false;
        if (applyPerson != null ? !applyPerson.equals(posEntity.applyPerson) : posEntity.applyPerson != null)
            return false;
        if (applyDate != null ? !applyDate.equals(posEntity.applyDate) : posEntity.applyDate != null) return false;
        if (constructionUnit != null ? !constructionUnit.equals(posEntity.constructionUnit) : posEntity.constructionUnit != null)
            return false;
        if (receiver != null ? !receiver.equals(posEntity.receiver) : posEntity.receiver != null) return false;
        if (receiverUidCode != null ? !receiverUidCode.equals(posEntity.receiverUidCode) : posEntity.receiverUidCode != null)
            return false;
        if (applyDocDesc != null ? !applyDocDesc.equals(posEntity.applyDocDesc) : posEntity.applyDocDesc != null)
            return false;
        if (expectedShipDate != null ? !expectedShipDate.equals(posEntity.expectedShipDate) : posEntity.expectedShipDate != null)
            return false;
        if (dockCode != null ? !dockCode.equals(posEntity.dockCode) : posEntity.dockCode != null) return false;
        if (disposition != null ? !disposition.equals(posEntity.disposition) : posEntity.disposition != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (warehouseCode != null ? warehouseCode.hashCode() : 0);
        result = 31 * result + (billType != null ? billType.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (applyDocCode != null ? applyDocCode.hashCode() : 0);
        result = 31 * result + (projectCode != null ? projectCode.hashCode() : 0);
        result = 31 * result + (applyUnit != null ? applyUnit.hashCode() : 0);
        result = 31 * result + (applyPerson != null ? applyPerson.hashCode() : 0);
        result = 31 * result + (applyDate != null ? applyDate.hashCode() : 0);
        result = 31 * result + (constructionUnit != null ? constructionUnit.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (receiverUidCode != null ? receiverUidCode.hashCode() : 0);
        result = 31 * result + (applyDocDesc != null ? applyDocDesc.hashCode() : 0);
        result = 31 * result + (expectedShipDate != null ? expectedShipDate.hashCode() : 0);
        result = 31 * result + (dockCode != null ? dockCode.hashCode() : 0);
        result = 31 * result + (disposition != null ? disposition.hashCode() : 0);
        return result;
    }
}
