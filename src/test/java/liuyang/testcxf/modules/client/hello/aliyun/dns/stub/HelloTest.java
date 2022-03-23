package liuyang.testcxf.modules.client.hello.aliyun.dns.stub;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author liuyang(wx)
 * @since 2022/3/17
 */
@Slf4j
public class HelloTest {

    private static final QName SERVICE_NAME = new QName("http://services.server.modules.testcxf.liuyang/", "HelloServiceService");
    private static URL wsdlURL;

    @Test
    void testFoo() {
        // 以下四行 https://www.freesion.com/article/1261127815/ 貌似没用！
        //System.setProperty("javax.net.ssl.trustStore","C:\\Users\\liuyang(wx)\\.keystore"); //key路径
        //System.setProperty("javax.net.ssl.trustStorePassword","123456");//密码
        /*
        System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
         */

        // liuyang 20220323 搞死我了！ 原来是这个！
        // https://blog.csdn.net/qq_40315210/article/details/102503946 管用！！！！
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
                //return false;
            }
        });

        try {
            //wsdlURL = new URL("https://xconfigurator.cn/soap/hello?wsdl");// ok 最标准的证书，不需要配合HttpsURLConnection.setDefaultHostnameVerifier();可以应对中间人劫持攻击。不需要在keystore中导入证书。
            wsdlURL = new URL("https://39.104.173.57/soap/hello?wsdl");// failure 需要配合HttpsURLConnection.setDefaultHostnameVerifier();
            //wsdlURL = new URL("http://39.104.173.57:8081/soap/hello?wsdl");// ok
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HelloServiceService ss = new HelloServiceService(wsdlURL, SERVICE_NAME);

        HelloInterface port = ss.getHelloServicePort();

        log.info(port.foo());
    }
}
