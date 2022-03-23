package liuyang.testcxf.modules.server.config;

import liuyang.testcxf.modules.server.services.HelloInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * 本配置类暴露JAX-WS类型WebService
 * @author liuyang
 * @scine 2021/6/2
 */
@Configuration
@Slf4j
public class CXFJaxWsConfig {

    @Value("${liuyang.soap.path}")
    private String SOAP_SERVICE_PATH;

    @Autowired
    private Bus bus;

    @Bean
    public ServletRegistrationBean regisCXFServlet() {
        StringBuilder urlMapping = new StringBuilder();
        urlMapping.append("/");
        urlMapping.append(SOAP_SERVICE_PATH);
        urlMapping.append("/*");
        ServletRegistrationBean registration = new ServletRegistrationBean(new CXFServlet(), urlMapping.toString());
        return registration;
    }

    // http://localhost/soap/hello?wsdl
    @Bean
    public Endpoint publicHelloService(HelloInterface helloService) {
        //Endpoint endpoint = new EndpointImpl(bus, helloService);
        EndpointImpl endpoint = new EndpointImpl(bus, helloService);
        endpoint.publish("/hello");

        if (log.isDebugEnabled()) {// 目前指定的精度还控制不到目标服务上，有点遗憾。
            endpoint.getInInterceptors().add(new LoggingInInterceptor());
            endpoint.getOutInterceptors().add(new LoggingOutInterceptor());
        }

        return endpoint;
    }
}
