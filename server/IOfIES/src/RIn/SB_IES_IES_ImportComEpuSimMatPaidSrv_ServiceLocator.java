/**
 * SB_IES_IES_ImportComEpuSimMatPaidSrv_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package RIn;

public class SB_IES_IES_ImportComEpuSimMatPaidSrv_ServiceLocator extends org.apache.axis.client.Service implements RIn.SB_IES_IES_ImportComEpuSimMatPaidSrv_Service {

    public SB_IES_IES_ImportComEpuSimMatPaidSrv_ServiceLocator() {
    }


    public SB_IES_IES_ImportComEpuSimMatPaidSrv_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SB_IES_IES_ImportComEpuSimMatPaidSrv_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SB_IES_IES_ImportComEpuSimMatPaidSrvPort
    private java.lang.String SB_IES_IES_ImportComEpuSimMatPaidSrvPort_address = "http://localhost:8080";

    public java.lang.String getSB_IES_IES_ImportComEpuSimMatPaidSrvPortAddress() {
        return SB_IES_IES_ImportComEpuSimMatPaidSrvPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName = "SB_IES_IES_ImportComEpuSimMatPaidSrvPort";

    public java.lang.String getSB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName() {
        return SB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName;
    }

    public void setSB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName(java.lang.String name) {
        SB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName = name;
    }

    public RIn.SB_IES_IES_ImportComEpuSimMatPaidSrv_PortType getSB_IES_IES_ImportComEpuSimMatPaidSrvPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SB_IES_IES_ImportComEpuSimMatPaidSrvPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSB_IES_IES_ImportComEpuSimMatPaidSrvPort(endpoint);
    }

    public RIn.SB_IES_IES_ImportComEpuSimMatPaidSrv_PortType getSB_IES_IES_ImportComEpuSimMatPaidSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvBindingStub _stub = new RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvBindingStub(portAddress, this);
            _stub.setPortName(getSB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSB_IES_IES_ImportComEpuSimMatPaidSrvPortEndpointAddress(java.lang.String address) {
        SB_IES_IES_ImportComEpuSimMatPaidSrvPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (RIn.SB_IES_IES_ImportComEpuSimMatPaidSrv_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvBindingStub _stub = new RIn.SB_IES_IES_ImportComEpuSimMatPaidSrvBindingStub(new java.net.URL(SB_IES_IES_ImportComEpuSimMatPaidSrvPort_address), this);
                _stub.setPortName(getSB_IES_IES_ImportComEpuSimMatPaidSrvPortWSDDServiceName());
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
        if ("SB_IES_IES_ImportComEpuSimMatPaidSrvPort".equals(inputPortName)) {
            return getSB_IES_IES_ImportComEpuSimMatPaidSrvPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "SB_IES_IES_ImportComEpuSimMatPaidSrv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mss.cmcc.com/SB_IES_IES_ImportComEpuSimMatPaidSrv", "SB_IES_IES_ImportComEpuSimMatPaidSrvPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SB_IES_IES_ImportComEpuSimMatPaidSrvPort".equals(portName)) {
            setSB_IES_IES_ImportComEpuSimMatPaidSrvPortEndpointAddress(address);
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
