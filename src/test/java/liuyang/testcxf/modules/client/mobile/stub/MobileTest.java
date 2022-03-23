package liuyang.testcxf.modules.client.mobile.stub;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuyang
 * @scine 2021/5/27
 */
//@SpringBootTest
@Slf4j
public class MobileTest {

    // 20220315 实测：貌似带不带wsdl都可以。
    //private static final String URL_WSDL = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
    private static final String URL_WSDL = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";// 不要带?wsdl

    @Test
    void testMobile() {
        log.info("URL_WSDL = " + URL_WSDL);

        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

        // 1. wsdl
        jaxWsProxyFactoryBean.setAddress(URL_WSDL);
        // 2. 设置服务接口
        // 问：从哪来？
        // 答：wsdl2java -p liuyang.testcxf.modules.mobile.stub -d . http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl
        jaxWsProxyFactoryBean.setServiceClass(MobileCodeWSSoap.class);

        // 3. 通过工厂类获取对象
        MobileCodeWSSoap mobileCodeWSSoap = jaxWsProxyFactoryBean.create(MobileCodeWSSoap.class);

        // 4. 调用
        String info1 = mobileCodeWSSoap.getMobileCodeInfo("18931160972", null);
        log.info("info1 = " + info1);

        String info2 = mobileCodeWSSoap.getMobileCodeInfo("13653387445", null);
        log.info("info2 = " + info2);

        String info3 = mobileCodeWSSoap.getMobileCodeInfo("13703297271", null);
        log.info("info3 = " + info3);

        String info4 = mobileCodeWSSoap.getMobileCodeInfo("13784355862", null);
        log.info("info4 = " + info4);
    }
}
