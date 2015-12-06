/**
 * ErrorItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutOut;

public class ErrorItem  implements java.io.Serializable {
    private java.lang.String ENTITY_NAME;

    private java.lang.String PRI_KEY;

    private java.lang.String ERROR_MESSAGE;

    public ErrorItem() {
    }

    public ErrorItem(
           java.lang.String ENTITY_NAME,
           java.lang.String PRI_KEY,
           java.lang.String ERROR_MESSAGE) {
           this.ENTITY_NAME = ENTITY_NAME;
           this.PRI_KEY = PRI_KEY;
           this.ERROR_MESSAGE = ERROR_MESSAGE;
    }


    /**
     * Gets the ENTITY_NAME value for this ErrorItem.
     * 
     * @return ENTITY_NAME
     */
    public java.lang.String getENTITY_NAME() {
        return ENTITY_NAME;
    }


    /**
     * Sets the ENTITY_NAME value for this ErrorItem.
     * 
     * @param ENTITY_NAME
     */
    public void setENTITY_NAME(java.lang.String ENTITY_NAME) {
        this.ENTITY_NAME = ENTITY_NAME;
    }


    /**
     * Gets the PRI_KEY value for this ErrorItem.
     * 
     * @return PRI_KEY
     */
    public java.lang.String getPRI_KEY() {
        return PRI_KEY;
    }


    /**
     * Sets the PRI_KEY value for this ErrorItem.
     * 
     * @param PRI_KEY
     */
    public void setPRI_KEY(java.lang.String PRI_KEY) {
        this.PRI_KEY = PRI_KEY;
    }


    /**
     * Gets the ERROR_MESSAGE value for this ErrorItem.
     * 
     * @return ERROR_MESSAGE
     */
    public java.lang.String getERROR_MESSAGE() {
        return ERROR_MESSAGE;
    }


    /**
     * Sets the ERROR_MESSAGE value for this ErrorItem.
     * 
     * @param ERROR_MESSAGE
     */
    public void setERROR_MESSAGE(java.lang.String ERROR_MESSAGE) {
        this.ERROR_MESSAGE = ERROR_MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorItem)) return false;
        ErrorItem other = (ErrorItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ENTITY_NAME==null && other.getENTITY_NAME()==null) || 
             (this.ENTITY_NAME!=null &&
              this.ENTITY_NAME.equals(other.getENTITY_NAME()))) &&
            ((this.PRI_KEY==null && other.getPRI_KEY()==null) || 
             (this.PRI_KEY!=null &&
              this.PRI_KEY.equals(other.getPRI_KEY()))) &&
            ((this.ERROR_MESSAGE==null && other.getERROR_MESSAGE()==null) || 
             (this.ERROR_MESSAGE!=null &&
              this.ERROR_MESSAGE.equals(other.getERROR_MESSAGE())));
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
        if (getENTITY_NAME() != null) {
            _hashCode += getENTITY_NAME().hashCode();
        }
        if (getPRI_KEY() != null) {
            _hashCode += getPRI_KEY().hashCode();
        }
        if (getERROR_MESSAGE() != null) {
            _hashCode += getERROR_MESSAGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ErrorItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTITY_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ENTITY_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRI_KEY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "PRI_KEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ERROR_MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "ERROR_MESSAGE"));
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
