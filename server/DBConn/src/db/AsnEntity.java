package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/12.
 */
@Entity
@Table(name = "asn", schema = "mobile", catalog = "")
public class AsnEntity {
    private String company;
    private String orgCode;
    private String warehouseCode;
    private String billType;
    private Timestamp expectedArriveDate;
    private String code;
    private String flag;
    private String relatedBill1;
    private String relatedBill2;
    private String relatedBill3;
    private String projectCode;
    private Timestamp orderDate;
    private String dock;
    private String buyerName;
    private String buyerEmail;
    private String buyerFax;
    private String buyerPhone;
    private String materialsName;
    private String materialsEmail;
    private String materialsFax;
    private String materialsPhone;
    private String vendorCode;
    private String vendorName;
    private String vendorContactName;
    private String vendorContactEmail;
    private String vendorContactFax;
    private String vendorContactPhone;
    private String carrierName;
    private String carrierContactName;
    private String carrierContactEmail;
    private String carrierContactFax;
    private String carrierContactPhone;
    private String disposition;
    private Integer paid;
    private String applyPerson;
    private Timestamp paidDate;

    @Basic
    @Column(name = "COMPANY")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "ORG_CODE")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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
    @Column(name = "EXPECTED_ARRIVE_DATE")
    public Timestamp getExpectedArriveDate() {
        return expectedArriveDate;
    }

    public void setExpectedArriveDate(Timestamp expectedArriveDate) {
        this.expectedArriveDate = expectedArriveDate;
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
    @Column(name = "FLAG")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "RELATED_BILL1")
    public String getRelatedBill1() {
        return relatedBill1;
    }

    public void setRelatedBill1(String relatedBill1) {
        this.relatedBill1 = relatedBill1;
    }

    @Basic
    @Column(name = "RELATED_BILL2")
    public String getRelatedBill2() {
        return relatedBill2;
    }

    public void setRelatedBill2(String relatedBill2) {
        this.relatedBill2 = relatedBill2;
    }

    @Basic
    @Column(name = "RELATED_BILL3")
    public String getRelatedBill3() {
        return relatedBill3;
    }

    public void setRelatedBill3(String relatedBill3) {
        this.relatedBill3 = relatedBill3;
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
    @Column(name = "ORDER_DATE")
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "DOCK")
    public String getDock() {
        return dock;
    }

    public void setDock(String dock) {
        this.dock = dock;
    }

    @Basic
    @Column(name = "BUYER_NAME")
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Basic
    @Column(name = "BUYER_EMAIL")
    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    @Basic
    @Column(name = "BUYER_FAX")
    public String getBuyerFax() {
        return buyerFax;
    }

    public void setBuyerFax(String buyerFax) {
        this.buyerFax = buyerFax;
    }

    @Basic
    @Column(name = "BUYER_PHONE")
    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    @Basic
    @Column(name = "MATERIALS_NAME")
    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    @Basic
    @Column(name = "MATERIALS_EMAIL")
    public String getMaterialsEmail() {
        return materialsEmail;
    }

    public void setMaterialsEmail(String materialsEmail) {
        this.materialsEmail = materialsEmail;
    }

    @Basic
    @Column(name = "MATERIALS_FAX")
    public String getMaterialsFax() {
        return materialsFax;
    }

    public void setMaterialsFax(String materialsFax) {
        this.materialsFax = materialsFax;
    }

    @Basic
    @Column(name = "MATERIALS_PHONE")
    public String getMaterialsPhone() {
        return materialsPhone;
    }

    public void setMaterialsPhone(String materialsPhone) {
        this.materialsPhone = materialsPhone;
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

    @Basic
    @Column(name = "VENDOR_CONTACT_NAME")
    public String getVendorContactName() {
        return vendorContactName;
    }

    public void setVendorContactName(String vendorContactName) {
        this.vendorContactName = vendorContactName;
    }

    @Basic
    @Column(name = "VENDOR_CONTACT_EMAIL")
    public String getVendorContactEmail() {
        return vendorContactEmail;
    }

    public void setVendorContactEmail(String vendorContactEmail) {
        this.vendorContactEmail = vendorContactEmail;
    }

    @Basic
    @Column(name = "VENDOR_CONTACT_FAX")
    public String getVendorContactFax() {
        return vendorContactFax;
    }

    public void setVendorContactFax(String vendorContactFax) {
        this.vendorContactFax = vendorContactFax;
    }

    @Basic
    @Column(name = "VENDOR_CONTACT_PHONE")
    public String getVendorContactPhone() {
        return vendorContactPhone;
    }

    public void setVendorContactPhone(String vendorContactPhone) {
        this.vendorContactPhone = vendorContactPhone;
    }

    @Basic
    @Column(name = "CARRIER_NAME")
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @Basic
    @Column(name = "CARRIER_CONTACT_NAME")
    public String getCarrierContactName() {
        return carrierContactName;
    }

    public void setCarrierContactName(String carrierContactName) {
        this.carrierContactName = carrierContactName;
    }

    @Basic
    @Column(name = "CARRIER_CONTACT_EMAIL")
    public String getCarrierContactEmail() {
        return carrierContactEmail;
    }

    public void setCarrierContactEmail(String carrierContactEmail) {
        this.carrierContactEmail = carrierContactEmail;
    }

    @Basic
    @Column(name = "CARRIER_CONTACT_FAX")
    public String getCarrierContactFax() {
        return carrierContactFax;
    }

    public void setCarrierContactFax(String carrierContactFax) {
        this.carrierContactFax = carrierContactFax;
    }

    @Basic
    @Column(name = "CARRIER_CONTACT_PHONE")
    public String getCarrierContactPhone() {
        return carrierContactPhone;
    }

    public void setCarrierContactPhone(String carrierContactPhone) {
        this.carrierContactPhone = carrierContactPhone;
    }

    @Basic
    @Column(name = "DISPOSITION")
    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    @Basic
    @Column(name = "Paid")
    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    @Basic
    @Column(name = "Apply_Person")
    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    @Basic
    @Column(name = "Paid_Date")
    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsnEntity asnEntity = (AsnEntity) o;

        if (company != null ? !company.equals(asnEntity.company) : asnEntity.company != null) return false;
        if (orgCode != null ? !orgCode.equals(asnEntity.orgCode) : asnEntity.orgCode != null) return false;
        if (warehouseCode != null ? !warehouseCode.equals(asnEntity.warehouseCode) : asnEntity.warehouseCode != null)
            return false;
        if (billType != null ? !billType.equals(asnEntity.billType) : asnEntity.billType != null) return false;
        if (expectedArriveDate != null ? !expectedArriveDate.equals(asnEntity.expectedArriveDate) : asnEntity.expectedArriveDate != null)
            return false;
        if (code != null ? !code.equals(asnEntity.code) : asnEntity.code != null) return false;
        if (flag != null ? !flag.equals(asnEntity.flag) : asnEntity.flag != null) return false;
        if (relatedBill1 != null ? !relatedBill1.equals(asnEntity.relatedBill1) : asnEntity.relatedBill1 != null)
            return false;
        if (relatedBill2 != null ? !relatedBill2.equals(asnEntity.relatedBill2) : asnEntity.relatedBill2 != null)
            return false;
        if (relatedBill3 != null ? !relatedBill3.equals(asnEntity.relatedBill3) : asnEntity.relatedBill3 != null)
            return false;
        if (projectCode != null ? !projectCode.equals(asnEntity.projectCode) : asnEntity.projectCode != null)
            return false;
        if (orderDate != null ? !orderDate.equals(asnEntity.orderDate) : asnEntity.orderDate != null) return false;
        if (dock != null ? !dock.equals(asnEntity.dock) : asnEntity.dock != null) return false;
        if (buyerName != null ? !buyerName.equals(asnEntity.buyerName) : asnEntity.buyerName != null) return false;
        if (buyerEmail != null ? !buyerEmail.equals(asnEntity.buyerEmail) : asnEntity.buyerEmail != null) return false;
        if (buyerFax != null ? !buyerFax.equals(asnEntity.buyerFax) : asnEntity.buyerFax != null) return false;
        if (buyerPhone != null ? !buyerPhone.equals(asnEntity.buyerPhone) : asnEntity.buyerPhone != null) return false;
        if (materialsName != null ? !materialsName.equals(asnEntity.materialsName) : asnEntity.materialsName != null)
            return false;
        if (materialsEmail != null ? !materialsEmail.equals(asnEntity.materialsEmail) : asnEntity.materialsEmail != null)
            return false;
        if (materialsFax != null ? !materialsFax.equals(asnEntity.materialsFax) : asnEntity.materialsFax != null)
            return false;
        if (materialsPhone != null ? !materialsPhone.equals(asnEntity.materialsPhone) : asnEntity.materialsPhone != null)
            return false;
        if (vendorCode != null ? !vendorCode.equals(asnEntity.vendorCode) : asnEntity.vendorCode != null) return false;
        if (vendorName != null ? !vendorName.equals(asnEntity.vendorName) : asnEntity.vendorName != null) return false;
        if (vendorContactName != null ? !vendorContactName.equals(asnEntity.vendorContactName) : asnEntity.vendorContactName != null)
            return false;
        if (vendorContactEmail != null ? !vendorContactEmail.equals(asnEntity.vendorContactEmail) : asnEntity.vendorContactEmail != null)
            return false;
        if (vendorContactFax != null ? !vendorContactFax.equals(asnEntity.vendorContactFax) : asnEntity.vendorContactFax != null)
            return false;
        if (vendorContactPhone != null ? !vendorContactPhone.equals(asnEntity.vendorContactPhone) : asnEntity.vendorContactPhone != null)
            return false;
        if (carrierName != null ? !carrierName.equals(asnEntity.carrierName) : asnEntity.carrierName != null)
            return false;
        if (carrierContactName != null ? !carrierContactName.equals(asnEntity.carrierContactName) : asnEntity.carrierContactName != null)
            return false;
        if (carrierContactEmail != null ? !carrierContactEmail.equals(asnEntity.carrierContactEmail) : asnEntity.carrierContactEmail != null)
            return false;
        if (carrierContactFax != null ? !carrierContactFax.equals(asnEntity.carrierContactFax) : asnEntity.carrierContactFax != null)
            return false;
        if (carrierContactPhone != null ? !carrierContactPhone.equals(asnEntity.carrierContactPhone) : asnEntity.carrierContactPhone != null)
            return false;
        if (disposition != null ? !disposition.equals(asnEntity.disposition) : asnEntity.disposition != null)
            return false;
        if (paid != null ? !paid.equals(asnEntity.paid) : asnEntity.paid != null) return false;
        if (applyPerson != null ? !applyPerson.equals(asnEntity.applyPerson) : asnEntity.applyPerson != null)
            return false;
        if (paidDate != null ? !paidDate.equals(asnEntity.paidDate) : asnEntity.paidDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (warehouseCode != null ? warehouseCode.hashCode() : 0);
        result = 31 * result + (billType != null ? billType.hashCode() : 0);
        result = 31 * result + (expectedArriveDate != null ? expectedArriveDate.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (relatedBill1 != null ? relatedBill1.hashCode() : 0);
        result = 31 * result + (relatedBill2 != null ? relatedBill2.hashCode() : 0);
        result = 31 * result + (relatedBill3 != null ? relatedBill3.hashCode() : 0);
        result = 31 * result + (projectCode != null ? projectCode.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (dock != null ? dock.hashCode() : 0);
        result = 31 * result + (buyerName != null ? buyerName.hashCode() : 0);
        result = 31 * result + (buyerEmail != null ? buyerEmail.hashCode() : 0);
        result = 31 * result + (buyerFax != null ? buyerFax.hashCode() : 0);
        result = 31 * result + (buyerPhone != null ? buyerPhone.hashCode() : 0);
        result = 31 * result + (materialsName != null ? materialsName.hashCode() : 0);
        result = 31 * result + (materialsEmail != null ? materialsEmail.hashCode() : 0);
        result = 31 * result + (materialsFax != null ? materialsFax.hashCode() : 0);
        result = 31 * result + (materialsPhone != null ? materialsPhone.hashCode() : 0);
        result = 31 * result + (vendorCode != null ? vendorCode.hashCode() : 0);
        result = 31 * result + (vendorName != null ? vendorName.hashCode() : 0);
        result = 31 * result + (vendorContactName != null ? vendorContactName.hashCode() : 0);
        result = 31 * result + (vendorContactEmail != null ? vendorContactEmail.hashCode() : 0);
        result = 31 * result + (vendorContactFax != null ? vendorContactFax.hashCode() : 0);
        result = 31 * result + (vendorContactPhone != null ? vendorContactPhone.hashCode() : 0);
        result = 31 * result + (carrierName != null ? carrierName.hashCode() : 0);
        result = 31 * result + (carrierContactName != null ? carrierContactName.hashCode() : 0);
        result = 31 * result + (carrierContactEmail != null ? carrierContactEmail.hashCode() : 0);
        result = 31 * result + (carrierContactFax != null ? carrierContactFax.hashCode() : 0);
        result = 31 * result + (carrierContactPhone != null ? carrierContactPhone.hashCode() : 0);
        result = 31 * result + (disposition != null ? disposition.hashCode() : 0);
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        result = 31 * result + (applyPerson != null ? applyPerson.hashCode() : 0);
        result = 31 * result + (paidDate != null ? paidDate.hashCode() : 0);
        return result;
    }
}
