package liuyang.testcxf.modules.client.hello.aliyun.dns.stub;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.4.3
 * 2022-03-17T16:21:34.749+08:00
 * Generated source version: 3.4.3
 *
 */
@WebServiceClient(name = "HelloServiceService",
                  wsdlLocation = "https://xconfigurator.cn/soap/hello?wsdl",
                  targetNamespace = "http://services.server.modules.testcxf.liuyang/")
public class HelloServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://services.server.modules.testcxf.liuyang/", "HelloServiceService");
    public final static QName HelloServicePort = new QName("http://services.server.modules.testcxf.liuyang/", "HelloServicePort");
    static {
        URL url = null;
        try {
            url = new URL("https://xconfigurator.cn/soap/hello?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(HelloServiceService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "https://xconfigurator.cn/soap/hello?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public HelloServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public HelloServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public HelloServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public HelloServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public HelloServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "HelloServicePort")
    public HelloInterface getHelloServicePort() {
        return super.getPort(HelloServicePort, HelloInterface.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloInterface
     */
    @WebEndpoint(name = "HelloServicePort")
    public HelloInterface getHelloServicePort(WebServiceFeature... features) {
        return super.getPort(HelloServicePort, HelloInterface.class, features);
    }

}