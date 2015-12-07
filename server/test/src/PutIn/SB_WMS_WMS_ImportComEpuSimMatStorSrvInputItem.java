/**
 * SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutIn;

public class SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem  implements java.io.Serializable {
    private java.lang.String COMPANY;

    private java.lang.String ORG_CODE;

    private java.lang.String WAREHOUSE_CODE;

    private java.lang.String BILL_TYPE;

    private java.lang.String CODE;

    private java.lang.String FLAG;

    private java.lang.String RELATED_BILL1;

    private java.lang.String RELATED_BILL2;

    private java.lang.String RELATED_BILL3;

    private java.lang.String PROJECT_CODE;

    private java.util.Calendar ORDER_DATE;

    private java.util.Calendar EXPECTED_ARRIVE_DATE;

    private java.lang.String DOCK;

    private java.lang.String APPLY_PERSON;

    private java.lang.String BUYER_NAME;

    private java.lang.String BUYER_EMAIL;

    private java.lang.String BUYER_FAX;

    private java.lang.String BUYER_PHONE;

    private java.lang.String MATERIALS_NAME;

    private java.lang.String MATERIALS_EMAIL;

    private java.lang.String MATERIALS_FAX;

    private java.lang.String MATERIALS_PHONE;

    private java.lang.String VENDOR_CODE;

    private java.lang.String VENDOR_NAME;

    private java.lang.String VENDOR_CONTACT_NAME;

    private java.lang.String VENDOR_CONTACT_EMAIL;

    private java.lang.String VENDOR_CONTACT_FAX;

    private java.lang.String VENDOR_CONTACT_PHONE;

    private java.lang.String CARRIER_NAME;

    private java.lang.String CARRIER_CONTACT_NAME;

    private java.lang.String CARRIER_CONTACT_EMAIL;

    private java.lang.String CARRIER_CONTACT_FAX;

    private java.lang.String CARRIER_CONTACT_PHONE;

    private java.lang.String DISPOSITION;

    private java.lang.String ATTRIBUTE1;

    private java.lang.String ATTRIBUTE2;

    private java.lang.String ATTRIBUTE3;

    private java.lang.String ATTRIBUTE4;

    private java.lang.String ATTRIBUTE5;

    private java.lang.String ATTRIBUTE6;

    private java.lang.String ATTRIBUTE7;

    private java.lang.String ATTRIBUTE8;

    private java.lang.String ATTRIBUTE9;

    private java.lang.String ATTRIBUTE10;

    private java.lang.String ATTRIBUTE11;

    private java.lang.String ATTRIBUTE12;

    private java.lang.String ATTRIBUTE13;

    private java.lang.String ATTRIBUTE14;

    private java.lang.String ATTRIBUTE15;

    private PutIn.BOMS_Item[] BOMS_Collection;

    private PutIn.WMS_DETAIL_Item[][][] WMS_DETAILS_Collection;

    public SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem() {
    }

    public SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem(
            java.lang.String COMPANY,
            java.lang.String ORG_CODE,
            java.lang.String WAREHOUSE_CODE,
            java.lang.String BILL_TYPE,
            java.lang.String CODE,
            java.lang.String FLAG,
            java.lang.String RELATED_BILL1,
            java.lang.String RELATED_BILL2,
            java.lang.String RELATED_BILL3,
            java.lang.String PROJECT_CODE,
            java.util.Calendar ORDER_DATE,
            java.util.Calendar EXPECTED_ARRIVE_DATE,
            java.lang.String DOCK,
            java.lang.String APPLY_PERSON,
            java.lang.String BUYER_NAME,
            java.lang.String BUYER_EMAIL,
            java.lang.String BUYER_FAX,
            java.lang.String BUYER_PHONE,
            java.lang.String MATERIALS_NAME,
            java.lang.String MATERIALS_EMAIL,
            java.lang.String MATERIALS_FAX,
            java.lang.String MATERIALS_PHONE,
            java.lang.String VENDOR_CODE,
            java.lang.String VENDOR_NAME,
            java.lang.String VENDOR_CONTACT_NAME,
            java.lang.String VENDOR_CONTACT_EMAIL,
            java.lang.String VENDOR_CONTACT_FAX,
            java.lang.String VENDOR_CONTACT_PHONE,
            java.lang.String CARRIER_NAME,
            java.lang.String CARRIER_CONTACT_NAME,
            java.lang.String CARRIER_CONTACT_EMAIL,
            java.lang.String CARRIER_CONTACT_FAX,
            java.lang.String CARRIER_CONTACT_PHONE,
            java.lang.String DISPOSITION,
            java.lang.String ATTRIBUTE1,
            java.lang.String ATTRIBUTE2,
            java.lang.String ATTRIBUTE3,
            java.lang.String ATTRIBUTE4,
            java.lang.String ATTRIBUTE5,
            java.lang.String ATTRIBUTE6,
            java.lang.String ATTRIBUTE7,
            java.lang.String ATTRIBUTE8,
            java.lang.String ATTRIBUTE9,
            java.lang.String ATTRIBUTE10,
            java.lang.String ATTRIBUTE11,
            java.lang.String ATTRIBUTE12,
            java.lang.String ATTRIBUTE13,
            java.lang.String ATTRIBUTE14,
            java.lang.String ATTRIBUTE15,
            PutIn.BOMS_Item[] BOMS_Collection,
            PutIn.WMS_DETAIL_Item[][][] WMS_DETAILS_Collection) {
           this.COMPANY = COMPANY;
           this.ORG_CODE = ORG_CODE;
           this.WAREHOUSE_CODE = WAREHOUSE_CODE;
           this.BILL_TYPE = BILL_TYPE;
           this.CODE = CODE;
           this.FLAG = FLAG;
           this.RELATED_BILL1 = RELATED_BILL1;
           this.RELATED_BILL2 = RELATED_BILL2;
           this.RELATED_BILL3 = RELATED_BILL3;
           this.PROJECT_CODE = PROJECT_CODE;
           this.ORDER_DATE = ORDER_DATE;
           this.EXPECTED_ARRIVE_DATE = EXPECTED_ARRIVE_DATE;
           this.DOCK = DOCK;
           this.APPLY_PERSON = APPLY_PERSON;
           this.BUYER_NAME = BUYER_NAME;
           this.BUYER_EMAIL = BUYER_EMAIL;
           this.BUYER_FAX = BUYER_FAX;
           this.BUYER_PHONE = BUYER_PHONE;
           this.MATERIALS_NAME = MATERIALS_NAME;
           this.MATERIALS_EMAIL = MATERIALS_EMAIL;
           this.MATERIALS_FAX = MATERIALS_FAX;
           this.MATERIALS_PHONE = MATERIALS_PHONE;
           this.VENDOR_CODE = VENDOR_CODE;
           this.VENDOR_NAME = VENDOR_NAME;
           this.VENDOR_CONTACT_NAME = VENDOR_CONTACT_NAME;
           this.VENDOR_CONTACT_EMAIL = VENDOR_CONTACT_EMAIL;
           this.VENDOR_CONTACT_FAX = VENDOR_CONTACT_FAX;
           this.VENDOR_CONTACT_PHONE = VENDOR_CONTACT_PHONE;
           this.CARRIER_NAME = CARRIER_NAME;
           this.CARRIER_CONTACT_NAME = CARRIER_CONTACT_NAME;
           this.CARRIER_CONTACT_EMAIL = CARRIER_CONTACT_EMAIL;
           this.CARRIER_CONTACT_FAX = CARRIER_CONTACT_FAX;
           this.CARRIER_CONTACT_PHONE = CARRIER_CONTACT_PHONE;
           this.DISPOSITION = DISPOSITION;
           this.ATTRIBUTE1 = ATTRIBUTE1;
           this.ATTRIBUTE2 = ATTRIBUTE2;
           this.ATTRIBUTE3 = ATTRIBUTE3;
           this.ATTRIBUTE4 = ATTRIBUTE4;
           this.ATTRIBUTE5 = ATTRIBUTE5;
           this.ATTRIBUTE6 = ATTRIBUTE6;
           this.ATTRIBUTE7 = ATTRIBUTE7;
           this.ATTRIBUTE8 = ATTRIBUTE8;
           this.ATTRIBUTE9 = ATTRIBUTE9;
           this.ATTRIBUTE10 = ATTRIBUTE10;
           this.ATTRIBUTE11 = ATTRIBUTE11;
           this.ATTRIBUTE12 = ATTRIBUTE12;
           this.ATTRIBUTE13 = ATTRIBUTE13;
           this.ATTRIBUTE14 = ATTRIBUTE14;
           this.ATTRIBUTE15 = ATTRIBUTE15;
           this.BOMS_Collection = BOMS_Collection;
           this.WMS_DETAILS_Collection = WMS_DETAILS_Collection;
    }


    /**
     * Gets the COMPANY value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return COMPANY
     */
    public java.lang.String getCOMPANY() {
        return COMPANY;
    }


    /**
     * Sets the COMPANY value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param COMPANY
     */
    public void setCOMPANY(java.lang.String COMPANY) {
        this.COMPANY = COMPANY;
    }


    /**
     * Gets the ORG_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ORG_CODE
     */
    public java.lang.String getORG_CODE() {
        return ORG_CODE;
    }


    /**
     * Sets the ORG_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ORG_CODE
     */
    public void setORG_CODE(java.lang.String ORG_CODE) {
        this.ORG_CODE = ORG_CODE;
    }


    /**
     * Gets the WAREHOUSE_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return WAREHOUSE_CODE
     */
    public java.lang.String getWAREHOUSE_CODE() {
        return WAREHOUSE_CODE;
    }


    /**
     * Sets the WAREHOUSE_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param WAREHOUSE_CODE
     */
    public void setWAREHOUSE_CODE(java.lang.String WAREHOUSE_CODE) {
        this.WAREHOUSE_CODE = WAREHOUSE_CODE;
    }


    /**
     * Gets the BILL_TYPE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return BILL_TYPE
     */
    public java.lang.String getBILL_TYPE() {
        return BILL_TYPE;
    }


    /**
     * Sets the BILL_TYPE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param BILL_TYPE
     */
    public void setBILL_TYPE(java.lang.String BILL_TYPE) {
        this.BILL_TYPE = BILL_TYPE;
    }


    /**
     * Gets the CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return CODE
     */
    public java.lang.String getCODE() {
        return CODE;
    }


    /**
     * Sets the CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param CODE
     */
    public void setCODE(java.lang.String CODE) {
        this.CODE = CODE;
    }


    /**
     * Gets the FLAG value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return FLAG
     */
    public java.lang.String getFLAG() {
        return FLAG;
    }


    /**
     * Sets the FLAG value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param FLAG
     */
    public void setFLAG(java.lang.String FLAG) {
        this.FLAG = FLAG;
    }


    /**
     * Gets the RELATED_BILL1 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return RELATED_BILL1
     */
    public java.lang.String getRELATED_BILL1() {
        return RELATED_BILL1;
    }


    /**
     * Sets the RELATED_BILL1 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param RELATED_BILL1
     */
    public void setRELATED_BILL1(java.lang.String RELATED_BILL1) {
        this.RELATED_BILL1 = RELATED_BILL1;
    }


    /**
     * Gets the RELATED_BILL2 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return RELATED_BILL2
     */
    public java.lang.String getRELATED_BILL2() {
        return RELATED_BILL2;
    }


    /**
     * Sets the RELATED_BILL2 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param RELATED_BILL2
     */
    public void setRELATED_BILL2(java.lang.String RELATED_BILL2) {
        this.RELATED_BILL2 = RELATED_BILL2;
    }


    /**
     * Gets the RELATED_BILL3 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return RELATED_BILL3
     */
    public java.lang.String getRELATED_BILL3() {
        return RELATED_BILL3;
    }


    /**
     * Sets the RELATED_BILL3 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param RELATED_BILL3
     */
    public void setRELATED_BILL3(java.lang.String RELATED_BILL3) {
        this.RELATED_BILL3 = RELATED_BILL3;
    }


    /**
     * Gets the PROJECT_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return PROJECT_CODE
     */
    public java.lang.String getPROJECT_CODE() {
        return PROJECT_CODE;
    }


    /**
     * Sets the PROJECT_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param PROJECT_CODE
     */
    public void setPROJECT_CODE(java.lang.String PROJECT_CODE) {
        this.PROJECT_CODE = PROJECT_CODE;
    }


    /**
     * Gets the ORDER_DATE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ORDER_DATE
     */
    public java.util.Calendar getORDER_DATE() {
        return ORDER_DATE;
    }


    /**
     * Sets the ORDER_DATE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ORDER_DATE
     */
    public void setORDER_DATE(java.util.Calendar ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }


    /**
     * Gets the EXPECTED_ARRIVE_DATE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return EXPECTED_ARRIVE_DATE
     */
    public java.util.Calendar getEXPECTED_ARRIVE_DATE() {
        return EXPECTED_ARRIVE_DATE;
    }


    /**
     * Sets the EXPECTED_ARRIVE_DATE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param EXPECTED_ARRIVE_DATE
     */
    public void setEXPECTED_ARRIVE_DATE(java.util.Calendar EXPECTED_ARRIVE_DATE) {
        this.EXPECTED_ARRIVE_DATE = EXPECTED_ARRIVE_DATE;
    }


    /**
     * Gets the DOCK value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return DOCK
     */
    public java.lang.String getDOCK() {
        return DOCK;
    }


    /**
     * Sets the DOCK value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param DOCK
     */
    public void setDOCK(java.lang.String DOCK) {
        this.DOCK = DOCK;
    }


    /**
     * Gets the APPLY_PERSON value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return APPLY_PERSON
     */
    public java.lang.String getAPPLY_PERSON() {
        return APPLY_PERSON;
    }


    /**
     * Sets the APPLY_PERSON value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param APPLY_PERSON
     */
    public void setAPPLY_PERSON(java.lang.String APPLY_PERSON) {
        this.APPLY_PERSON = APPLY_PERSON;
    }


    /**
     * Gets the BUYER_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return BUYER_NAME
     */
    public java.lang.String getBUYER_NAME() {
        return BUYER_NAME;
    }


    /**
     * Sets the BUYER_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param BUYER_NAME
     */
    public void setBUYER_NAME(java.lang.String BUYER_NAME) {
        this.BUYER_NAME = BUYER_NAME;
    }


    /**
     * Gets the BUYER_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return BUYER_EMAIL
     */
    public java.lang.String getBUYER_EMAIL() {
        return BUYER_EMAIL;
    }


    /**
     * Sets the BUYER_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param BUYER_EMAIL
     */
    public void setBUYER_EMAIL(java.lang.String BUYER_EMAIL) {
        this.BUYER_EMAIL = BUYER_EMAIL;
    }


    /**
     * Gets the BUYER_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return BUYER_FAX
     */
    public java.lang.String getBUYER_FAX() {
        return BUYER_FAX;
    }


    /**
     * Sets the BUYER_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param BUYER_FAX
     */
    public void setBUYER_FAX(java.lang.String BUYER_FAX) {
        this.BUYER_FAX = BUYER_FAX;
    }


    /**
     * Gets the BUYER_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return BUYER_PHONE
     */
    public java.lang.String getBUYER_PHONE() {
        return BUYER_PHONE;
    }


    /**
     * Sets the BUYER_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param BUYER_PHONE
     */
    public void setBUYER_PHONE(java.lang.String BUYER_PHONE) {
        this.BUYER_PHONE = BUYER_PHONE;
    }


    /**
     * Gets the MATERIALS_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return MATERIALS_NAME
     */
    public java.lang.String getMATERIALS_NAME() {
        return MATERIALS_NAME;
    }


    /**
     * Sets the MATERIALS_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param MATERIALS_NAME
     */
    public void setMATERIALS_NAME(java.lang.String MATERIALS_NAME) {
        this.MATERIALS_NAME = MATERIALS_NAME;
    }


    /**
     * Gets the MATERIALS_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return MATERIALS_EMAIL
     */
    public java.lang.String getMATERIALS_EMAIL() {
        return MATERIALS_EMAIL;
    }


    /**
     * Sets the MATERIALS_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param MATERIALS_EMAIL
     */
    public void setMATERIALS_EMAIL(java.lang.String MATERIALS_EMAIL) {
        this.MATERIALS_EMAIL = MATERIALS_EMAIL;
    }


    /**
     * Gets the MATERIALS_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return MATERIALS_FAX
     */
    public java.lang.String getMATERIALS_FAX() {
        return MATERIALS_FAX;
    }


    /**
     * Sets the MATERIALS_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param MATERIALS_FAX
     */
    public void setMATERIALS_FAX(java.lang.String MATERIALS_FAX) {
        this.MATERIALS_FAX = MATERIALS_FAX;
    }


    /**
     * Gets the MATERIALS_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return MATERIALS_PHONE
     */
    public java.lang.String getMATERIALS_PHONE() {
        return MATERIALS_PHONE;
    }


    /**
     * Sets the MATERIALS_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param MATERIALS_PHONE
     */
    public void setMATERIALS_PHONE(java.lang.String MATERIALS_PHONE) {
        this.MATERIALS_PHONE = MATERIALS_PHONE;
    }


    /**
     * Gets the VENDOR_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return VENDOR_CODE
     */
    public java.lang.String getVENDOR_CODE() {
        return VENDOR_CODE;
    }


    /**
     * Sets the VENDOR_CODE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param VENDOR_CODE
     */
    public void setVENDOR_CODE(java.lang.String VENDOR_CODE) {
        this.VENDOR_CODE = VENDOR_CODE;
    }


    /**
     * Gets the VENDOR_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return VENDOR_NAME
     */
    public java.lang.String getVENDOR_NAME() {
        return VENDOR_NAME;
    }


    /**
     * Sets the VENDOR_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param VENDOR_NAME
     */
    public void setVENDOR_NAME(java.lang.String VENDOR_NAME) {
        this.VENDOR_NAME = VENDOR_NAME;
    }


    /**
     * Gets the VENDOR_CONTACT_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return VENDOR_CONTACT_NAME
     */
    public java.lang.String getVENDOR_CONTACT_NAME() {
        return VENDOR_CONTACT_NAME;
    }


    /**
     * Sets the VENDOR_CONTACT_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param VENDOR_CONTACT_NAME
     */
    public void setVENDOR_CONTACT_NAME(java.lang.String VENDOR_CONTACT_NAME) {
        this.VENDOR_CONTACT_NAME = VENDOR_CONTACT_NAME;
    }


    /**
     * Gets the VENDOR_CONTACT_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return VENDOR_CONTACT_EMAIL
     */
    public java.lang.String getVENDOR_CONTACT_EMAIL() {
        return VENDOR_CONTACT_EMAIL;
    }


    /**
     * Sets the VENDOR_CONTACT_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param VENDOR_CONTACT_EMAIL
     */
    public void setVENDOR_CONTACT_EMAIL(java.lang.String VENDOR_CONTACT_EMAIL) {
        this.VENDOR_CONTACT_EMAIL = VENDOR_CONTACT_EMAIL;
    }


    /**
     * Gets the VENDOR_CONTACT_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return VENDOR_CONTACT_FAX
     */
    public java.lang.String getVENDOR_CONTACT_FAX() {
        return VENDOR_CONTACT_FAX;
    }


    /**
     * Sets the VENDOR_CONTACT_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param VENDOR_CONTACT_FAX
     */
    public void setVENDOR_CONTACT_FAX(java.lang.String VENDOR_CONTACT_FAX) {
        this.VENDOR_CONTACT_FAX = VENDOR_CONTACT_FAX;
    }


    /**
     * Gets the VENDOR_CONTACT_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return VENDOR_CONTACT_PHONE
     */
    public java.lang.String getVENDOR_CONTACT_PHONE() {
        return VENDOR_CONTACT_PHONE;
    }


    /**
     * Sets the VENDOR_CONTACT_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param VENDOR_CONTACT_PHONE
     */
    public void setVENDOR_CONTACT_PHONE(java.lang.String VENDOR_CONTACT_PHONE) {
        this.VENDOR_CONTACT_PHONE = VENDOR_CONTACT_PHONE;
    }


    /**
     * Gets the CARRIER_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return CARRIER_NAME
     */
    public java.lang.String getCARRIER_NAME() {
        return CARRIER_NAME;
    }


    /**
     * Sets the CARRIER_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param CARRIER_NAME
     */
    public void setCARRIER_NAME(java.lang.String CARRIER_NAME) {
        this.CARRIER_NAME = CARRIER_NAME;
    }


    /**
     * Gets the CARRIER_CONTACT_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return CARRIER_CONTACT_NAME
     */
    public java.lang.String getCARRIER_CONTACT_NAME() {
        return CARRIER_CONTACT_NAME;
    }


    /**
     * Sets the CARRIER_CONTACT_NAME value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param CARRIER_CONTACT_NAME
     */
    public void setCARRIER_CONTACT_NAME(java.lang.String CARRIER_CONTACT_NAME) {
        this.CARRIER_CONTACT_NAME = CARRIER_CONTACT_NAME;
    }


    /**
     * Gets the CARRIER_CONTACT_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return CARRIER_CONTACT_EMAIL
     */
    public java.lang.String getCARRIER_CONTACT_EMAIL() {
        return CARRIER_CONTACT_EMAIL;
    }


    /**
     * Sets the CARRIER_CONTACT_EMAIL value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param CARRIER_CONTACT_EMAIL
     */
    public void setCARRIER_CONTACT_EMAIL(java.lang.String CARRIER_CONTACT_EMAIL) {
        this.CARRIER_CONTACT_EMAIL = CARRIER_CONTACT_EMAIL;
    }


    /**
     * Gets the CARRIER_CONTACT_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return CARRIER_CONTACT_FAX
     */
    public java.lang.String getCARRIER_CONTACT_FAX() {
        return CARRIER_CONTACT_FAX;
    }


    /**
     * Sets the CARRIER_CONTACT_FAX value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param CARRIER_CONTACT_FAX
     */
    public void setCARRIER_CONTACT_FAX(java.lang.String CARRIER_CONTACT_FAX) {
        this.CARRIER_CONTACT_FAX = CARRIER_CONTACT_FAX;
    }


    /**
     * Gets the CARRIER_CONTACT_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return CARRIER_CONTACT_PHONE
     */
    public java.lang.String getCARRIER_CONTACT_PHONE() {
        return CARRIER_CONTACT_PHONE;
    }


    /**
     * Sets the CARRIER_CONTACT_PHONE value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param CARRIER_CONTACT_PHONE
     */
    public void setCARRIER_CONTACT_PHONE(java.lang.String CARRIER_CONTACT_PHONE) {
        this.CARRIER_CONTACT_PHONE = CARRIER_CONTACT_PHONE;
    }


    /**
     * Gets the DISPOSITION value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return DISPOSITION
     */
    public java.lang.String getDISPOSITION() {
        return DISPOSITION;
    }


    /**
     * Sets the DISPOSITION value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param DISPOSITION
     */
    public void setDISPOSITION(java.lang.String DISPOSITION) {
        this.DISPOSITION = DISPOSITION;
    }


    /**
     * Gets the ATTRIBUTE1 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE1
     */
    public java.lang.String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }


    /**
     * Sets the ATTRIBUTE1 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE1
     */
    public void setATTRIBUTE1(java.lang.String ATTRIBUTE1) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
    }


    /**
     * Gets the ATTRIBUTE2 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE2
     */
    public java.lang.String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }


    /**
     * Sets the ATTRIBUTE2 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE2
     */
    public void setATTRIBUTE2(java.lang.String ATTRIBUTE2) {
        this.ATTRIBUTE2 = ATTRIBUTE2;
    }


    /**
     * Gets the ATTRIBUTE3 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE3
     */
    public java.lang.String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }


    /**
     * Sets the ATTRIBUTE3 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE3
     */
    public void setATTRIBUTE3(java.lang.String ATTRIBUTE3) {
        this.ATTRIBUTE3 = ATTRIBUTE3;
    }


    /**
     * Gets the ATTRIBUTE4 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE4
     */
    public java.lang.String getATTRIBUTE4() {
        return ATTRIBUTE4;
    }


    /**
     * Sets the ATTRIBUTE4 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE4
     */
    public void setATTRIBUTE4(java.lang.String ATTRIBUTE4) {
        this.ATTRIBUTE4 = ATTRIBUTE4;
    }


    /**
     * Gets the ATTRIBUTE5 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE5
     */
    public java.lang.String getATTRIBUTE5() {
        return ATTRIBUTE5;
    }


    /**
     * Sets the ATTRIBUTE5 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE5
     */
    public void setATTRIBUTE5(java.lang.String ATTRIBUTE5) {
        this.ATTRIBUTE5 = ATTRIBUTE5;
    }


    /**
     * Gets the ATTRIBUTE6 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE6
     */
    public java.lang.String getATTRIBUTE6() {
        return ATTRIBUTE6;
    }


    /**
     * Sets the ATTRIBUTE6 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE6
     */
    public void setATTRIBUTE6(java.lang.String ATTRIBUTE6) {
        this.ATTRIBUTE6 = ATTRIBUTE6;
    }


    /**
     * Gets the ATTRIBUTE7 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE7
     */
    public java.lang.String getATTRIBUTE7() {
        return ATTRIBUTE7;
    }


    /**
     * Sets the ATTRIBUTE7 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE7
     */
    public void setATTRIBUTE7(java.lang.String ATTRIBUTE7) {
        this.ATTRIBUTE7 = ATTRIBUTE7;
    }


    /**
     * Gets the ATTRIBUTE8 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE8
     */
    public java.lang.String getATTRIBUTE8() {
        return ATTRIBUTE8;
    }


    /**
     * Sets the ATTRIBUTE8 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE8
     */
    public void setATTRIBUTE8(java.lang.String ATTRIBUTE8) {
        this.ATTRIBUTE8 = ATTRIBUTE8;
    }


    /**
     * Gets the ATTRIBUTE9 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE9
     */
    public java.lang.String getATTRIBUTE9() {
        return ATTRIBUTE9;
    }


    /**
     * Sets the ATTRIBUTE9 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE9
     */
    public void setATTRIBUTE9(java.lang.String ATTRIBUTE9) {
        this.ATTRIBUTE9 = ATTRIBUTE9;
    }


    /**
     * Gets the ATTRIBUTE10 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE10
     */
    public java.lang.String getATTRIBUTE10() {
        return ATTRIBUTE10;
    }


    /**
     * Sets the ATTRIBUTE10 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE10
     */
    public void setATTRIBUTE10(java.lang.String ATTRIBUTE10) {
        this.ATTRIBUTE10 = ATTRIBUTE10;
    }


    /**
     * Gets the ATTRIBUTE11 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE11
     */
    public java.lang.String getATTRIBUTE11() {
        return ATTRIBUTE11;
    }


    /**
     * Sets the ATTRIBUTE11 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE11
     */
    public void setATTRIBUTE11(java.lang.String ATTRIBUTE11) {
        this.ATTRIBUTE11 = ATTRIBUTE11;
    }


    /**
     * Gets the ATTRIBUTE12 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE12
     */
    public java.lang.String getATTRIBUTE12() {
        return ATTRIBUTE12;
    }


    /**
     * Sets the ATTRIBUTE12 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE12
     */
    public void setATTRIBUTE12(java.lang.String ATTRIBUTE12) {
        this.ATTRIBUTE12 = ATTRIBUTE12;
    }


    /**
     * Gets the ATTRIBUTE13 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE13
     */
    public java.lang.String getATTRIBUTE13() {
        return ATTRIBUTE13;
    }


    /**
     * Sets the ATTRIBUTE13 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE13
     */
    public void setATTRIBUTE13(java.lang.String ATTRIBUTE13) {
        this.ATTRIBUTE13 = ATTRIBUTE13;
    }


    /**
     * Gets the ATTRIBUTE14 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE14
     */
    public java.lang.String getATTRIBUTE14() {
        return ATTRIBUTE14;
    }


    /**
     * Sets the ATTRIBUTE14 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE14
     */
    public void setATTRIBUTE14(java.lang.String ATTRIBUTE14) {
        this.ATTRIBUTE14 = ATTRIBUTE14;
    }


    /**
     * Gets the ATTRIBUTE15 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return ATTRIBUTE15
     */
    public java.lang.String getATTRIBUTE15() {
        return ATTRIBUTE15;
    }


    /**
     * Sets the ATTRIBUTE15 value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param ATTRIBUTE15
     */
    public void setATTRIBUTE15(java.lang.String ATTRIBUTE15) {
        this.ATTRIBUTE15 = ATTRIBUTE15;
    }


    /**
     * Gets the BOMS_Collection value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return BOMS_Collection
     */
    public PutIn.BOMS_Item[] getBOMS_Collection() {
        return BOMS_Collection;
    }


    /**
     * Sets the BOMS_Collection value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param BOMS_Collection
     */
    public void setBOMS_Collection(PutIn.BOMS_Item[] BOMS_Collection) {
        this.BOMS_Collection = BOMS_Collection;
    }


    /**
     * Gets the WMS_DETAILS_Collection value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @return WMS_DETAILS_Collection
     */
    public PutIn.WMS_DETAIL_Item[][][] getWMS_DETAILS_Collection() {
        return WMS_DETAILS_Collection;
    }


    /**
     * Sets the WMS_DETAILS_Collection value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.
     * 
     * @param WMS_DETAILS_Collection
     */
    public void setWMS_DETAILS_Collection(PutIn.WMS_DETAIL_Item[][][] WMS_DETAILS_Collection) {
        this.WMS_DETAILS_Collection = WMS_DETAILS_Collection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem)) return false;
        SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem other = (SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.COMPANY==null && other.getCOMPANY()==null) || 
             (this.COMPANY!=null &&
              this.COMPANY.equals(other.getCOMPANY()))) &&
            ((this.ORG_CODE==null && other.getORG_CODE()==null) || 
             (this.ORG_CODE!=null &&
              this.ORG_CODE.equals(other.getORG_CODE()))) &&
            ((this.WAREHOUSE_CODE==null && other.getWAREHOUSE_CODE()==null) || 
             (this.WAREHOUSE_CODE!=null &&
              this.WAREHOUSE_CODE.equals(other.getWAREHOUSE_CODE()))) &&
            ((this.BILL_TYPE==null && other.getBILL_TYPE()==null) || 
             (this.BILL_TYPE!=null &&
              this.BILL_TYPE.equals(other.getBILL_TYPE()))) &&
            ((this.CODE==null && other.getCODE()==null) || 
             (this.CODE!=null &&
              this.CODE.equals(other.getCODE()))) &&
            ((this.FLAG==null && other.getFLAG()==null) || 
             (this.FLAG!=null &&
              this.FLAG.equals(other.getFLAG()))) &&
            ((this.RELATED_BILL1==null && other.getRELATED_BILL1()==null) || 
             (this.RELATED_BILL1!=null &&
              this.RELATED_BILL1.equals(other.getRELATED_BILL1()))) &&
            ((this.RELATED_BILL2==null && other.getRELATED_BILL2()==null) || 
             (this.RELATED_BILL2!=null &&
              this.RELATED_BILL2.equals(other.getRELATED_BILL2()))) &&
            ((this.RELATED_BILL3==null && other.getRELATED_BILL3()==null) || 
             (this.RELATED_BILL3!=null &&
              this.RELATED_BILL3.equals(other.getRELATED_BILL3()))) &&
            ((this.PROJECT_CODE==null && other.getPROJECT_CODE()==null) || 
             (this.PROJECT_CODE!=null &&
              this.PROJECT_CODE.equals(other.getPROJECT_CODE()))) &&
            ((this.ORDER_DATE==null && other.getORDER_DATE()==null) || 
             (this.ORDER_DATE!=null &&
              this.ORDER_DATE.equals(other.getORDER_DATE()))) &&
            ((this.EXPECTED_ARRIVE_DATE==null && other.getEXPECTED_ARRIVE_DATE()==null) || 
             (this.EXPECTED_ARRIVE_DATE!=null &&
              this.EXPECTED_ARRIVE_DATE.equals(other.getEXPECTED_ARRIVE_DATE()))) &&
            ((this.DOCK==null && other.getDOCK()==null) || 
             (this.DOCK!=null &&
              this.DOCK.equals(other.getDOCK()))) &&
            ((this.APPLY_PERSON==null && other.getAPPLY_PERSON()==null) || 
             (this.APPLY_PERSON!=null &&
              this.APPLY_PERSON.equals(other.getAPPLY_PERSON()))) &&
            ((this.BUYER_NAME==null && other.getBUYER_NAME()==null) || 
             (this.BUYER_NAME!=null &&
              this.BUYER_NAME.equals(other.getBUYER_NAME()))) &&
            ((this.BUYER_EMAIL==null && other.getBUYER_EMAIL()==null) || 
             (this.BUYER_EMAIL!=null &&
              this.BUYER_EMAIL.equals(other.getBUYER_EMAIL()))) &&
            ((this.BUYER_FAX==null && other.getBUYER_FAX()==null) || 
             (this.BUYER_FAX!=null &&
              this.BUYER_FAX.equals(other.getBUYER_FAX()))) &&
            ((this.BUYER_PHONE==null && other.getBUYER_PHONE()==null) || 
             (this.BUYER_PHONE!=null &&
              this.BUYER_PHONE.equals(other.getBUYER_PHONE()))) &&
            ((this.MATERIALS_NAME==null && other.getMATERIALS_NAME()==null) || 
             (this.MATERIALS_NAME!=null &&
              this.MATERIALS_NAME.equals(other.getMATERIALS_NAME()))) &&
            ((this.MATERIALS_EMAIL==null && other.getMATERIALS_EMAIL()==null) || 
             (this.MATERIALS_EMAIL!=null &&
              this.MATERIALS_EMAIL.equals(other.getMATERIALS_EMAIL()))) &&
            ((this.MATERIALS_FAX==null && other.getMATERIALS_FAX()==null) || 
             (this.MATERIALS_FAX!=null &&
              this.MATERIALS_FAX.equals(other.getMATERIALS_FAX()))) &&
            ((this.MATERIALS_PHONE==null && other.getMATERIALS_PHONE()==null) || 
             (this.MATERIALS_PHONE!=null &&
              this.MATERIALS_PHONE.equals(other.getMATERIALS_PHONE()))) &&
            ((this.VENDOR_CODE==null && other.getVENDOR_CODE()==null) || 
             (this.VENDOR_CODE!=null &&
              this.VENDOR_CODE.equals(other.getVENDOR_CODE()))) &&
            ((this.VENDOR_NAME==null && other.getVENDOR_NAME()==null) || 
             (this.VENDOR_NAME!=null &&
              this.VENDOR_NAME.equals(other.getVENDOR_NAME()))) &&
            ((this.VENDOR_CONTACT_NAME==null && other.getVENDOR_CONTACT_NAME()==null) || 
             (this.VENDOR_CONTACT_NAME!=null &&
              this.VENDOR_CONTACT_NAME.equals(other.getVENDOR_CONTACT_NAME()))) &&
            ((this.VENDOR_CONTACT_EMAIL==null && other.getVENDOR_CONTACT_EMAIL()==null) || 
             (this.VENDOR_CONTACT_EMAIL!=null &&
              this.VENDOR_CONTACT_EMAIL.equals(other.getVENDOR_CONTACT_EMAIL()))) &&
            ((this.VENDOR_CONTACT_FAX==null && other.getVENDOR_CONTACT_FAX()==null) || 
             (this.VENDOR_CONTACT_FAX!=null &&
              this.VENDOR_CONTACT_FAX.equals(other.getVENDOR_CONTACT_FAX()))) &&
            ((this.VENDOR_CONTACT_PHONE==null && other.getVENDOR_CONTACT_PHONE()==null) || 
             (this.VENDOR_CONTACT_PHONE!=null &&
              this.VENDOR_CONTACT_PHONE.equals(other.getVENDOR_CONTACT_PHONE()))) &&
            ((this.CARRIER_NAME==null && other.getCARRIER_NAME()==null) || 
             (this.CARRIER_NAME!=null &&
              this.CARRIER_NAME.equals(other.getCARRIER_NAME()))) &&
            ((this.CARRIER_CONTACT_NAME==null && other.getCARRIER_CONTACT_NAME()==null) || 
             (this.CARRIER_CONTACT_NAME!=null &&
              this.CARRIER_CONTACT_NAME.equals(other.getCARRIER_CONTACT_NAME()))) &&
            ((this.CARRIER_CONTACT_EMAIL==null && other.getCARRIER_CONTACT_EMAIL()==null) || 
             (this.CARRIER_CONTACT_EMAIL!=null &&
              this.CARRIER_CONTACT_EMAIL.equals(other.getCARRIER_CONTACT_EMAIL()))) &&
            ((this.CARRIER_CONTACT_FAX==null && other.getCARRIER_CONTACT_FAX()==null) || 
             (this.CARRIER_CONTACT_FAX!=null &&
              this.CARRIER_CONTACT_FAX.equals(other.getCARRIER_CONTACT_FAX()))) &&
            ((this.CARRIER_CONTACT_PHONE==null && other.getCARRIER_CONTACT_PHONE()==null) || 
             (this.CARRIER_CONTACT_PHONE!=null &&
              this.CARRIER_CONTACT_PHONE.equals(other.getCARRIER_CONTACT_PHONE()))) &&
            ((this.DISPOSITION==null && other.getDISPOSITION()==null) || 
             (this.DISPOSITION!=null &&
              this.DISPOSITION.equals(other.getDISPOSITION()))) &&
            ((this.ATTRIBUTE1==null && other.getATTRIBUTE1()==null) || 
             (this.ATTRIBUTE1!=null &&
              this.ATTRIBUTE1.equals(other.getATTRIBUTE1()))) &&
            ((this.ATTRIBUTE2==null && other.getATTRIBUTE2()==null) || 
             (this.ATTRIBUTE2!=null &&
              this.ATTRIBUTE2.equals(other.getATTRIBUTE2()))) &&
            ((this.ATTRIBUTE3==null && other.getATTRIBUTE3()==null) || 
             (this.ATTRIBUTE3!=null &&
              this.ATTRIBUTE3.equals(other.getATTRIBUTE3()))) &&
            ((this.ATTRIBUTE4==null && other.getATTRIBUTE4()==null) || 
             (this.ATTRIBUTE4!=null &&
              this.ATTRIBUTE4.equals(other.getATTRIBUTE4()))) &&
            ((this.ATTRIBUTE5==null && other.getATTRIBUTE5()==null) || 
             (this.ATTRIBUTE5!=null &&
              this.ATTRIBUTE5.equals(other.getATTRIBUTE5()))) &&
            ((this.ATTRIBUTE6==null && other.getATTRIBUTE6()==null) || 
             (this.ATTRIBUTE6!=null &&
              this.ATTRIBUTE6.equals(other.getATTRIBUTE6()))) &&
            ((this.ATTRIBUTE7==null && other.getATTRIBUTE7()==null) || 
             (this.ATTRIBUTE7!=null &&
              this.ATTRIBUTE7.equals(other.getATTRIBUTE7()))) &&
            ((this.ATTRIBUTE8==null && other.getATTRIBUTE8()==null) || 
             (this.ATTRIBUTE8!=null &&
              this.ATTRIBUTE8.equals(other.getATTRIBUTE8()))) &&
            ((this.ATTRIBUTE9==null && other.getATTRIBUTE9()==null) || 
             (this.ATTRIBUTE9!=null &&
              this.ATTRIBUTE9.equals(other.getATTRIBUTE9()))) &&
            ((this.ATTRIBUTE10==null && other.getATTRIBUTE10()==null) || 
             (this.ATTRIBUTE10!=null &&
              this.ATTRIBUTE10.equals(other.getATTRIBUTE10()))) &&
            ((this.ATTRIBUTE11==null && other.getATTRIBUTE11()==null) || 
             (this.ATTRIBUTE11!=null &&
              this.ATTRIBUTE11.equals(other.getATTRIBUTE11()))) &&
            ((this.ATTRIBUTE12==null && other.getATTRIBUTE12()==null) || 
             (this.ATTRIBUTE12!=null &&
              this.ATTRIBUTE12.equals(other.getATTRIBUTE12()))) &&
            ((this.ATTRIBUTE13==null && other.getATTRIBUTE13()==null) || 
             (this.ATTRIBUTE13!=null &&
              this.ATTRIBUTE13.equals(other.getATTRIBUTE13()))) &&
            ((this.ATTRIBUTE14==null && other.getATTRIBUTE14()==null) || 
             (this.ATTRIBUTE14!=null &&
              this.ATTRIBUTE14.equals(other.getATTRIBUTE14()))) &&
            ((this.ATTRIBUTE15==null && other.getATTRIBUTE15()==null) || 
             (this.ATTRIBUTE15!=null &&
              this.ATTRIBUTE15.equals(other.getATTRIBUTE15()))) &&
            ((this.BOMS_Collection==null && other.getBOMS_Collection()==null) || 
             (this.BOMS_Collection!=null &&
              java.util.Arrays.equals(this.BOMS_Collection, other.getBOMS_Collection()))) &&
            ((this.WMS_DETAILS_Collection==null && other.getWMS_DETAILS_Collection()==null) || 
             (this.WMS_DETAILS_Collection!=null &&
              java.util.Arrays.equals(this.WMS_DETAILS_Collection, other.getWMS_DETAILS_Collection())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCOMPANY() != null) {
            _hashCode += getCOMPANY().hashCode();
        }
        if (getORG_CODE() != null) {
            _hashCode += getORG_CODE().hashCode();
        }
        if (getWAREHOUSE_CODE() != null) {
            _hashCode += getWAREHOUSE_CODE().hashCode();
        }
        if (getBILL_TYPE() != null) {
            _hashCode += getBILL_TYPE().hashCode();
        }
        if (getCODE() != null) {
            _hashCode += getCODE().hashCode();
        }
        if (getFLAG() != null) {
            _hashCode += getFLAG().hashCode();
        }
        if (getRELATED_BILL1() != null) {
            _hashCode += getRELATED_BILL1().hashCode();
        }
        if (getRELATED_BILL2() != null) {
            _hashCode += getRELATED_BILL2().hashCode();
        }
        if (getRELATED_BILL3() != null) {
            _hashCode += getRELATED_BILL3().hashCode();
        }
        if (getPROJECT_CODE() != null) {
            _hashCode += getPROJECT_CODE().hashCode();
        }
        if (getORDER_DATE() != null) {
            _hashCode += getORDER_DATE().hashCode();
        }
        if (getEXPECTED_ARRIVE_DATE() != null) {
            _hashCode += getEXPECTED_ARRIVE_DATE().hashCode();
        }
        if (getDOCK() != null) {
            _hashCode += getDOCK().hashCode();
        }
        if (getAPPLY_PERSON() != null) {
            _hashCode += getAPPLY_PERSON().hashCode();
        }
        if (getBUYER_NAME() != null) {
            _hashCode += getBUYER_NAME().hashCode();
        }
        if (getBUYER_EMAIL() != null) {
            _hashCode += getBUYER_EMAIL().hashCode();
        }
        if (getBUYER_FAX() != null) {
            _hashCode += getBUYER_FAX().hashCode();
        }
        if (getBUYER_PHONE() != null) {
            _hashCode += getBUYER_PHONE().hashCode();
        }
        if (getMATERIALS_NAME() != null) {
            _hashCode += getMATERIALS_NAME().hashCode();
        }
        if (getMATERIALS_EMAIL() != null) {
            _hashCode += getMATERIALS_EMAIL().hashCode();
        }
        if (getMATERIALS_FAX() != null) {
            _hashCode += getMATERIALS_FAX().hashCode();
        }
        if (getMATERIALS_PHONE() != null) {
            _hashCode += getMATERIALS_PHONE().hashCode();
        }
        if (getVENDOR_CODE() != null) {
            _hashCode += getVENDOR_CODE().hashCode();
        }
        if (getVENDOR_NAME() != null) {
            _hashCode += getVENDOR_NAME().hashCode();
        }
        if (getVENDOR_CONTACT_NAME() != null) {
            _hashCode += getVENDOR_CONTACT_NAME().hashCode();
        }
        if (getVENDOR_CONTACT_EMAIL() != null) {
            _hashCode += getVENDOR_CONTACT_EMAIL().hashCode();
        }
        if (getVENDOR_CONTACT_FAX() != null) {
            _hashCode += getVENDOR_CONTACT_FAX().hashCode();
        }
        if (getVENDOR_CONTACT_PHONE() != null) {
            _hashCode += getVENDOR_CONTACT_PHONE().hashCode();
        }
        if (getCARRIER_NAME() != null) {
            _hashCode += getCARRIER_NAME().hashCode();
        }
        if (getCARRIER_CONTACT_NAME() != null) {
            _hashCode += getCARRIER_CONTACT_NAME().hashCode();
        }
        if (getCARRIER_CONTACT_EMAIL() != null) {
            _hashCode += getCARRIER_CONTACT_EMAIL().hashCode();
        }
        if (getCARRIER_CONTACT_FAX() != null) {
            _hashCode += getCARRIER_CONTACT_FAX().hashCode();
        }
        if (getCARRIER_CONTACT_PHONE() != null) {
            _hashCode += getCARRIER_CONTACT_PHONE().hashCode();
        }
        if (getDISPOSITION() != null) {
            _hashCode += getDISPOSITION().hashCode();
        }
        if (getATTRIBUTE1() != null) {
            _hashCode += getATTRIBUTE1().hashCode();
        }
        if (getATTRIBUTE2() != null) {
            _hashCode += getATTRIBUTE2().hashCode();
        }
        if (getATTRIBUTE3() != null) {
            _hashCode += getATTRIBUTE3().hashCode();
        }
        if (getATTRIBUTE4() != null) {
            _hashCode += getATTRIBUTE4().hashCode();
        }
        if (getATTRIBUTE5() != null) {
            _hashCode += getATTRIBUTE5().hashCode();
        }
        if (getATTRIBUTE6() != null) {
            _hashCode += getATTRIBUTE6().hashCode();
        }
        if (getATTRIBUTE7() != null) {
            _hashCode += getATTRIBUTE7().hashCode();
        }
        if (getATTRIBUTE8() != null) {
            _hashCode += getATTRIBUTE8().hashCode();
        }
        if (getATTRIBUTE9() != null) {
            _hashCode += getATTRIBUTE9().hashCode();
        }
        if (getATTRIBUTE10() != null) {
            _hashCode += getATTRIBUTE10().hashCode();
        }
        if (getATTRIBUTE11() != null) {
            _hashCode += getATTRIBUTE11().hashCode();
        }
        if (getATTRIBUTE12() != null) {
            _hashCode += getATTRIBUTE12().hashCode();
        }
        if (getATTRIBUTE13() != null) {
            _hashCode += getATTRIBUTE13().hashCode();
        }
        if (getATTRIBUTE14() != null) {
            _hashCode += getATTRIBUTE14().hashCode();
        }
        if (getATTRIBUTE15() != null) {
            _hashCode += getATTRIBUTE15().hashCode();
        }
        if (getBOMS_Collection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBOMS_Collection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBOMS_Collection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWMS_DETAILS_Collection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWMS_DETAILS_Collection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWMS_DETAILS_Collection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMPANY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "COMPANY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORG_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ORG_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WAREHOUSE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "WAREHOUSE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BILL_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BILL_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RELATED_BILL1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "RELATED_BILL1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RELATED_BILL2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "RELATED_BILL2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RELATED_BILL3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "RELATED_BILL3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROJECT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "PROJECT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORDER_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ORDER_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXPECTED_ARRIVE_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "EXPECTED_ARRIVE_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCK");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "DOCK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APPLY_PERSON");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "APPLY_PERSON"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUYER_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BUYER_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUYER_EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BUYER_EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUYER_FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BUYER_FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUYER_PHONE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BUYER_PHONE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIALS_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "MATERIALS_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIALS_EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "MATERIALS_EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIALS_FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "MATERIALS_FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIALS_PHONE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "MATERIALS_PHONE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VENDOR_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "VENDOR_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VENDOR_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "VENDOR_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VENDOR_CONTACT_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "VENDOR_CONTACT_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VENDOR_CONTACT_EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "VENDOR_CONTACT_EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VENDOR_CONTACT_FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "VENDOR_CONTACT_FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VENDOR_CONTACT_PHONE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "VENDOR_CONTACT_PHONE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARRIER_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "CARRIER_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARRIER_CONTACT_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "CARRIER_CONTACT_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARRIER_CONTACT_EMAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "CARRIER_CONTACT_EMAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARRIER_CONTACT_FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "CARRIER_CONTACT_FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARRIER_CONTACT_PHONE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "CARRIER_CONTACT_PHONE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPOSITION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "DISPOSITION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE9");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE10");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE11");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE12");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE13");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE14");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE15");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ATTRIBUTE15"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BOMS_Collection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BOMS_Collection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BOMS_Item"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BOMS_Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WMS_DETAILS_Collection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "WMS_DETAILS_Collection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "WMS_DETAILS_Item"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "WMS_DETAILS_Item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
