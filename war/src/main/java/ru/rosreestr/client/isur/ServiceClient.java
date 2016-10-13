
package ru.rosreestr.client.isur;

import ru.rosreestr.handler.LoggerHandler;
import ru.rosreestr.handler.SignatureHandler;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 */
@WebServiceClient(name = "Service", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/")
//@HandlerChain(file="handler-chain.xml")
public class ServiceClient extends Service {

    private final static URL SERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICE_EXCEPTION;
    private final static QName SERVICE_QNAME = new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "Service");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://212.45.30.233:81/IsurTest/Coordinate/V5/Service.svc?singleWsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICE_WSDL_LOCATION = url;
        SERVICE_EXCEPTION = e;
    }

    private SignatureHandler signatureHandler;

    private LoggerHandler loggerHandler;

    public ServiceClient() {
        super(__getWsdlLocation(), SERVICE_QNAME);
    }

    public ServiceClient(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICE_QNAME, features);
    }

    public ServiceClient(URL wsdlLocation) {
        super(wsdlLocation, SERVICE_QNAME);
    }

    public ServiceClient(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE_QNAME, features);
    }

    public ServiceClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceClient(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * @return returns IService
     */
    @WebEndpoint(name = "CustomBinding_IService")
    public IService getCustomBindingIService() {
        IService customBindingIService = super.getPort(new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "CustomBinding_IService"), IService.class);

        List<Handler> handlers = ((BindingProvider) customBindingIService).getBinding().getHandlerChain();
        if (handlers == null)
            handlers = new ArrayList<Handler>();
        handlers.add(signatureHandler);
        handlers.add(loggerHandler);
        ((BindingProvider) customBindingIService).getBinding().setHandlerChain(handlers);
        return customBindingIService;
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns IService
     */
    @WebEndpoint(name = "CustomBinding_IService")
    public IService getCustomBindingIService(WebServiceFeature... features) {
        IService customBindingIService = super.getPort(new QName("http://asguf.mos.ru/rkis_gu/coordinate/v5/", "CustomBinding_IService"), IService.class, features);

        List<Handler> handlers = ((BindingProvider) customBindingIService).getBinding().getHandlerChain();
        if (handlers == null)
            handlers = new ArrayList<Handler>();
        handlers.add(signatureHandler);
        handlers.add(loggerHandler);
        ((BindingProvider) customBindingIService).getBinding().setHandlerChain(handlers);
        return customBindingIService;
    }

    private static URL __getWsdlLocation() {
        if (SERVICE_EXCEPTION != null) {
            throw SERVICE_EXCEPTION;
        }
        return SERVICE_WSDL_LOCATION;
    }

    public void configureLogger(Integer serviceId, boolean logXmlEnable) {
        loggerHandler.setServiceId(serviceId);
        loggerHandler.setIsLogXmlEnable(logXmlEnable);
        signatureHandler.setServiceId(serviceId);
    }

    public void setSignatureHandler(SignatureHandler signatureHandler) {
        this.signatureHandler = signatureHandler;
    }

    public void setLoggerHandler(LoggerHandler loggerHandler) {
        this.loggerHandler = loggerHandler;
    }
}
