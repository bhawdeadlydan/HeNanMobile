/**
 * SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PutOut;

public class SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator extends org.apache.axis.client.Service implements PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrv_Service {

    public SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator() {
    }


    public SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SB_WMS_WMS_ImportComEpuSimMatStorSrvPort
    private java.lang.String SB_WMS_WMS_ImportComEpuSimMatStorSrvPort_address = "http://localhost:8080";

    public java.lang.String getSB_WMS_WMS_ImportComEpuSimMatStorSrvPortAddress() {
        return SB_WMS_WMS_ImportComEpuSimMatStorSrvPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName = "SB_WMS_WMS_ImportComEpuSimMatStorSrvPort";

    public java.lang.String getSB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName() {
        return SB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName;
    }

    public void setSB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName(java.lang.String name) {
        SB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName = name;
    }

    public PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrv_PortType getSB_WMS_WMS_ImportComEpuSimMatStorSrvPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SB_WMS_WMS_ImportComEpuSimMatStorSrvPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSB_WMS_WMS_ImportComEpuSimMatStorSrvPort(endpoint);
    }

    public PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrv_PortType getSB_WMS_WMS_ImportComEpuSimMatStorSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub _stub = new PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub(portAddress, this);
            _stub.setPortName(getSB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSB_WMS_WMS_ImportComEpuSimMatStorSrvPortEndpointAddress(java.lang.String address) {
        SB_WMS_WMS_ImportComEpuSimMatStorSrvPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrv_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub _stub = new PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub(new java.net.URL(SB_WMS_WMS_ImportComEpuSimMatStorSrvPort_address), this);
                _stub.setPortName(getSB_WMS_WMS_ImportComEpuSimMatStorSrvPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SB_WMS_WMS_ImportComEpuSimMatStorSrvPort".equals(inputPortName)) {
            return getSB_WMS_WMS_ImportComEpuSimMatStorSrvPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_WMS_WMS_ImportComEpuSimMatStorSrv", "SB_WMS_WMS_ImportComEpuSimMatStorSrvPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SB_WMS_WMS_ImportComEpuSimMatStorSrvPort".equals(portName)) {
            setSB_WMS_WMS_ImportComEpuSimMatStorSrvPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
