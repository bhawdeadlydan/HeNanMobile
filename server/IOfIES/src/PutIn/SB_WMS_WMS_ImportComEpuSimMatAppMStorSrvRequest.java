/**
 * SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutIn;

public class SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest  implements java.io.Serializable {
    private PutIn.MsgHeader msgHeader;

    private PutIn.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem[] SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection;

    public SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest() {
    }

    public SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest(
           PutIn.MsgHeader msgHeader,
           PutIn.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem[] SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection) {
           this.msgHeader = msgHeader;
           this.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection = SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection;
    }


    /**
     * Gets the msgHeader value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest.
     * 
     * @return msgHeader
     */
    public PutIn.MsgHeader getMsgHeader() {
        return msgHeader;
    }


    /**
     * Sets the msgHeader value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest.
     * 
     * @param msgHeader
     */
    public void setMsgHeader(PutIn.MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }


    /**
     * Gets the SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest.
     * 
     * @return SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection
     */
    public PutIn.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem[] getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection() {
        return SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection;
    }


    /**
     * Sets the SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest.
     * 
     * @param SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection
     */
    public void setSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection(PutIn.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem[] SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection) {
        this.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection = SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest)) return false;
        SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest other = (SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest) obj;
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
            ((this.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection==null && other.getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection()==null) || 
             (this.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection!=null &&
              java.util.Arrays.equals(this.SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection, other.getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection())));
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
        if (getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection(), i);
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
        new org.apache.axis.description.TypeDesc(SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "MsgHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/MsgHeader", "MsgHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvInputItem"));
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
