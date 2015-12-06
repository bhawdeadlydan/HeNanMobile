/**
 * SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutIn;

public class SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse  implements java.io.Serializable {
    private java.lang.String SERVICE_FLAG;

    private java.lang.String INSTANCE_ID;

    private java.lang.String SERVICE_MESSAGE;

    private PutIn.ErrorItem[] errorCollection;

    private PutIn.ResponseItem[] responseCollection;

    public SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse() {
    }

    public SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse(
           java.lang.String SERVICE_FLAG,
           java.lang.String INSTANCE_ID,
           java.lang.String SERVICE_MESSAGE,
           PutIn.ErrorItem[] errorCollection,
           PutIn.ResponseItem[] responseCollection) {
           this.SERVICE_FLAG = SERVICE_FLAG;
           this.INSTANCE_ID = INSTANCE_ID;
           this.SERVICE_MESSAGE = SERVICE_MESSAGE;
           this.errorCollection = errorCollection;
           this.responseCollection = responseCollection;
    }


    /**
     * Gets the SERVICE_FLAG value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @return SERVICE_FLAG
     */
    public java.lang.String getSERVICE_FLAG() {
        return SERVICE_FLAG;
    }


    /**
     * Sets the SERVICE_FLAG value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @param SERVICE_FLAG
     */
    public void setSERVICE_FLAG(java.lang.String SERVICE_FLAG) {
        this.SERVICE_FLAG = SERVICE_FLAG;
    }


    /**
     * Gets the INSTANCE_ID value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @return INSTANCE_ID
     */
    public java.lang.String getINSTANCE_ID() {
        return INSTANCE_ID;
    }


    /**
     * Sets the INSTANCE_ID value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @param INSTANCE_ID
     */
    public void setINSTANCE_ID(java.lang.String INSTANCE_ID) {
        this.INSTANCE_ID = INSTANCE_ID;
    }


    /**
     * Gets the SERVICE_MESSAGE value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @return SERVICE_MESSAGE
     */
    public java.lang.String getSERVICE_MESSAGE() {
        return SERVICE_MESSAGE;
    }


    /**
     * Sets the SERVICE_MESSAGE value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @param SERVICE_MESSAGE
     */
    public void setSERVICE_MESSAGE(java.lang.String SERVICE_MESSAGE) {
        this.SERVICE_MESSAGE = SERVICE_MESSAGE;
    }


    /**
     * Gets the errorCollection value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @return errorCollection
     */
    public PutIn.ErrorItem[] getErrorCollection() {
        return errorCollection;
    }


    /**
     * Sets the errorCollection value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @param errorCollection
     */
    public void setErrorCollection(PutIn.ErrorItem[] errorCollection) {
        this.errorCollection = errorCollection;
    }


    /**
     * Gets the responseCollection value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @return responseCollection
     */
    public PutIn.ResponseItem[] getResponseCollection() {
        return responseCollection;
    }


    /**
     * Sets the responseCollection value for this SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.
     * 
     * @param responseCollection
     */
    public void setResponseCollection(PutIn.ResponseItem[] responseCollection) {
        this.responseCollection = responseCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse)) return false;
        SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse other = (SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SERVICE_FLAG==null && other.getSERVICE_FLAG()==null) || 
             (this.SERVICE_FLAG!=null &&
              this.SERVICE_FLAG.equals(other.getSERVICE_FLAG()))) &&
            ((this.INSTANCE_ID==null && other.getINSTANCE_ID()==null) || 
             (this.INSTANCE_ID!=null &&
              this.INSTANCE_ID.equals(other.getINSTANCE_ID()))) &&
            ((this.SERVICE_MESSAGE==null && other.getSERVICE_MESSAGE()==null) || 
             (this.SERVICE_MESSAGE!=null &&
              this.SERVICE_MESSAGE.equals(other.getSERVICE_MESSAGE()))) &&
            ((this.errorCollection==null && other.getErrorCollection()==null) || 
             (this.errorCollection!=null &&
              java.util.Arrays.equals(this.errorCollection, other.getErrorCollection()))) &&
            ((this.responseCollection==null && other.getResponseCollection()==null) || 
             (this.responseCollection!=null &&
              java.util.Arrays.equals(this.responseCollection, other.getResponseCollection())));
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
        if (getSERVICE_FLAG() != null) {
            _hashCode += getSERVICE_FLAG().hashCode();
        }
        if (getINSTANCE_ID() != null) {
            _hashCode += getINSTANCE_ID().hashCode();
        }
        if (getSERVICE_MESSAGE() != null) {
            _hashCode += getSERVICE_MESSAGE().hashCode();
        }
        if (getErrorCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResponseCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResponseCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResponseCollection(), i);
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
        new org.apache.axis.description.TypeDesc(SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SB_WMS_WMS_ImportComEpuSimMatAppMStorSrvResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVICE_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SERVICE_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTANCE_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "INSTANCE_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVICE_MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "SERVICE_MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "ErrorCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "ErrorItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "ErrorItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "ResponseCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "ResponseItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatAppMStorSrv", "ResponseItem"));
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
