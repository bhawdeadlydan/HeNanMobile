/**
 * SB_IES_IES_ImportComEpuSimMatPaidSrvRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package RIn;

public class SB_IES_IES_ImportComEpuSimMatPaidSrvRequest  implements java.io.Serializable {
    private RIn.MsgHeader msgHeader;

    private RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem[] SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection;

    public SB_IES_IES_ImportComEpuSimMatPaidSrvRequest() {
    }

    public SB_IES_IES_ImportComEpuSimMatPaidSrvRequest(
           RIn.MsgHeader msgHeader,
           RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem[] SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection) {
           this.msgHeader = msgHeader;
           this.SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection = SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection;
    }


    /**
     * Gets the msgHeader value for this SB_IES_IES_ImportComEpuSimMatPaidSrvRequest.
     * 
     * @return msgHeader
     */
    public RIn.MsgHeader getMsgHeader() {
        return msgHeader;
    }


    /**
     * Sets the msgHeader value for this SB_IES_IES_ImportComEpuSimMatPaidSrvRequest.
     * 
     * @param msgHeader
     */
    public void setMsgHeader(RIn.MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }


    /**
     * Gets the SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection value for this SB_IES_IES_ImportComEpuSimMatPaidSrvRequest.
     * 
     * @return SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection
     */
    public RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem[] getSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection() {
        return SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection;
    }


    /**
     * Sets the SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection value for this SB_IES_IES_ImportComEpuSimMatPaidSrvRequest.
     * 
     * @param SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection
     */
    public void setSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection(RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem[] SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection) {
        this.SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection = SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_IES_IES_ImportComEpuSimMatPaidSrvRequest)) return false;
        SB_IES_IES_ImportComEpuSimMatPaidSrvRequest other = (SB_IES_IES_ImportComEpuSimMatPaidSrvRequest) obj;
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
            ((this.SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection==null && other.getSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection()==null) || 
             (this.SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection!=null &&
              java.util.Arrays.equals(this.SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection, other.getSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection())));
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
        if (getSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection(), i);
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
        new org.apache.axis.description.TypeDesc(SB_IES_IES_ImportComEpuSimMatPaidSrvRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "SB_IES_IES_ImportComEpuSimMatPaidSrvRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "MsgHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/MsgHeader", "MsgHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "SB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem"));
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
