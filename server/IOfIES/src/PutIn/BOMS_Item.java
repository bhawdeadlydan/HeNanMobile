/**
 * BOMS_Item.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutIn;

public class BOMS_Item  implements java.io.Serializable {
    private PutIn.SALE_BOMS_Item[] SALE_BOMS_Collection;

    private PutIn.PACKING_BOMS_Item[] PACKING_BOMS_Collection;

    public BOMS_Item() {
    }

    public BOMS_Item(
           PutIn.SALE_BOMS_Item[] SALE_BOMS_Collection,
           PutIn.PACKING_BOMS_Item[] PACKING_BOMS_Collection) {
           this.SALE_BOMS_Collection = SALE_BOMS_Collection;
           this.PACKING_BOMS_Collection = PACKING_BOMS_Collection;
    }


    /**
     * Gets the SALE_BOMS_Collection value for this BOMS_Item.
     * 
     * @return SALE_BOMS_Collection
     */
    public PutIn.SALE_BOMS_Item[] getSALE_BOMS_Collection() {
        return SALE_BOMS_Collection;
    }


    /**
     * Sets the SALE_BOMS_Collection value for this BOMS_Item.
     * 
     * @param SALE_BOMS_Collection
     */
    public void setSALE_BOMS_Collection(PutIn.SALE_BOMS_Item[] SALE_BOMS_Collection) {
        this.SALE_BOMS_Collection = SALE_BOMS_Collection;
    }


    /**
     * Gets the PACKING_BOMS_Collection value for this BOMS_Item.
     * 
     * @return PACKING_BOMS_Collection
     */
    public PutIn.PACKING_BOMS_Item[] getPACKING_BOMS_Collection() {
        return PACKING_BOMS_Collection;
    }


    /**
     * Sets the PACKING_BOMS_Collection value for this BOMS_Item.
     * 
     * @param PACKING_BOMS_Collection
     */
    public void setPACKING_BOMS_Collection(PutIn.PACKING_BOMS_Item[] PACKING_BOMS_Collection) {
        this.PACKING_BOMS_Collection = PACKING_BOMS_Collection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOMS_Item)) return false;
        BOMS_Item other = (BOMS_Item) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SALE_BOMS_Collection==null && other.getSALE_BOMS_Collection()==null) || 
             (this.SALE_BOMS_Collection!=null &&
              java.util.Arrays.equals(this.SALE_BOMS_Collection, other.getSALE_BOMS_Collection()))) &&
            ((this.PACKING_BOMS_Collection==null && other.getPACKING_BOMS_Collection()==null) || 
             (this.PACKING_BOMS_Collection!=null &&
              java.util.Arrays.equals(this.PACKING_BOMS_Collection, other.getPACKING_BOMS_Collection())));
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
        if (getSALE_BOMS_Collection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSALE_BOMS_Collection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSALE_BOMS_Collection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPACKING_BOMS_Collection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPACKING_BOMS_Collection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPACKING_BOMS_Collection(), i);
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
        new org.apache.axis.description.TypeDesc(BOMS_Item.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "BOMS_Item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALE_BOMS_Collection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SALE_BOMS_Collection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SALE_BOMS_Item"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SALE_BOMS_Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PACKING_BOMS_Collection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "PACKING_BOMS_Collection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "PACKING_BOMS_Item"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "PACKING_BOMS_Item"));
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
