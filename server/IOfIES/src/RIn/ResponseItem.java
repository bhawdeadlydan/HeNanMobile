/**
 * ResponseItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package RIn;

public class ResponseItem  implements java.io.Serializable {
    private java.lang.String REQUEST_ID;

    private java.lang.String PRI_KEY;

    public ResponseItem() {
    }

    public ResponseItem(
           java.lang.String REQUEST_ID,
           java.lang.String PRI_KEY) {
           this.REQUEST_ID = REQUEST_ID;
           this.PRI_KEY = PRI_KEY;
    }


    /**
     * Gets the REQUEST_ID value for this ResponseItem.
     * 
     * @return REQUEST_ID
     */
    public java.lang.String getREQUEST_ID() {
        return REQUEST_ID;
    }


    /**
     * Sets the REQUEST_ID value for this ResponseItem.
     * 
     * @param REQUEST_ID
     */
    public void setREQUEST_ID(java.lang.String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }


    /**
     * Gets the PRI_KEY value for this ResponseItem.
     * 
     * @return PRI_KEY
     */
    public java.lang.String getPRI_KEY() {
        return PRI_KEY;
    }


    /**
     * Sets the PRI_KEY value for this ResponseItem.
     * 
     * @param PRI_KEY
     */
    public void setPRI_KEY(java.lang.String PRI_KEY) {
        this.PRI_KEY = PRI_KEY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResponseItem)) return false;
        ResponseItem other = (ResponseItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.REQUEST_ID==null && other.getREQUEST_ID()==null) || 
             (this.REQUEST_ID!=null &&
              this.REQUEST_ID.equals(other.getREQUEST_ID()))) &&
            ((this.PRI_KEY==null && other.getPRI_KEY()==null) || 
             (this.PRI_KEY!=null &&
              this.PRI_KEY.equals(other.getPRI_KEY())));
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
        if (getREQUEST_ID() != null) {
            _hashCode += getREQUEST_ID().hashCode();
        }
        if (getPRI_KEY() != null) {
            _hashCode += getPRI_KEY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "ResponseItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUEST_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "REQUEST_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRI_KEY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "PRI_KEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
