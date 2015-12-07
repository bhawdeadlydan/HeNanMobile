/**
 * SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ROut;

public class SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_ServiceLocator extends org.apache.axis.client.Service implements ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_Service {

    public SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_ServiceLocator() {
    }


    public SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort
    private java.lang.String SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort_address = "http://localhost:8080";

    public java.lang.String getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortAddress() {
        return SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName = "SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort";

    public java.lang.String getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName() {
        return SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName;
    }

    public void setSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName(java.lang.String name) {
        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName = name;
    }

    public ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_PortType getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort(endpoint);
    }

    public ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_PortType getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvBindingStub _stub = new ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvBindingStub(portAddress, this);
            _stub.setPortName(getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortEndpointAddress(java.lang.String address) {
        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvBindingStub _stub = new ROut.SB_IES_IES_ImportComEpuSimMatMStorRSendSrvBindingStub(new java.net.URL(SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort_address), this);
                _stub.setPortName(getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortWSDDServiceName());
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
        if ("SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort".equals(inputPortName)) {
            return getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "SB_IES_IES_ImportComEpuSimMatMStorRSendSrv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatMStorRSendSrv", "SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort".equals(portName)) {
            setSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPortEndpointAddress(address);
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
