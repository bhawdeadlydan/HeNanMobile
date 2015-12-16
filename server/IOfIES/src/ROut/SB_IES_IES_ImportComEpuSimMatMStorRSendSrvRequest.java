/**
 * SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ROut;

public class SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest  implements java.io.Serializable {
    private ROut.MsgHeader msgHeader;

    private ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem[] SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection;

    public SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest() {
    }

    public SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest(
           ROut.MsgHeader msgHeader,
           ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem[] SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection) {
           this.msgHeader = msgHeader;
           this.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection = SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection;
    }


    /**
     * Gets the msgHeader value for this SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest.
     * 
     * @return msgHeader
     */
    public ROut.MsgHeader getMsgHeader() {
        return msgHeader;
    }


    /**
     * Sets the msgHeader value for this SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest.
     * 
     * @param msgHeader
     */
    public void setMsgHeader(ROut.MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }


    /**
     * Gets the SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection value for this SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest.
     * 
     * @return SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection
     */
    public ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem[] getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection() {
        return SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection;
    }


    /**
     * Sets the SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection value for this SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest.
     * 
     * @param SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection
     */
    public void setSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection(ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem[] SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection) {
        this.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection = SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest)) return false;
        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest other = (SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest) obj;
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
            ((this.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection==null && other.getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection()==null) || 
             (this.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection!=null &&
              java.util.Arrays.equals(this.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection, other.getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection())));
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
        if (getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection(), i);
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
        new org.apache.axis.description.TypeDesc(SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "MsgHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/MsgHeader", "MsgHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem"));
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
