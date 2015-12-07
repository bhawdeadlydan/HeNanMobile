/**
 * SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutIn;

public class SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest  implements java.io.Serializable {
    private PutIn.MsgHeader msgHeader;

    private PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[] SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection;

    public SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest() {
    }

    public SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest(
            PutIn.MsgHeader msgHeader,
            PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[] SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection) {
           this.msgHeader = msgHeader;
           this.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection = SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection;
    }


    /**
     * Gets the msgHeader value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest.
     * 
     * @return msgHeader
     */
    public PutIn.MsgHeader getMsgHeader() {
        return msgHeader;
    }


    /**
     * Sets the msgHeader value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest.
     * 
     * @param msgHeader
     */
    public void setMsgHeader(PutIn.MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }


    /**
     * Gets the SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest.
     * 
     * @return SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection
     */
    public PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[] getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection() {
        return SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection;
    }


    /**
     * Sets the SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection value for this SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest.
     * 
     * @param SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection
     */
    public void setSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection(PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[] SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection) {
        this.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection = SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest)) return false;
        SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest other = (SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.msgHeader==null && other.getMsgHeader()==null) || 
             (this.msgHeader!=null &&
              this.msgHeader.equals(other.getMsgHeader()))) &&
            ((this.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection==null && other.getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection()==null) || 
             (this.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection!=null &&
              java.util.Arrays.equals(this.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection, other.getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection())));
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
        if (getMsgHeader() != null) {
            _hashCode += getMsgHeader().hashCode();
        }
        if (getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection(), i);
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
        new org.apache.axis.description.TypeDesc(SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "MsgHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/MsgHeader", "MsgHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrvInputCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem"));
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
