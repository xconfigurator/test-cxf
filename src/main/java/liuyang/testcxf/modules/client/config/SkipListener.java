package liuyang.testcxf.modules.client.config;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientLifeCycleListener;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.stereotype.Component;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 参考：https://blog.csdn.net/qq_36135928/article/details/88600034
 *
 * @author liuyang(wx)
 * @since 2022/3/23
 */
@Component
public class SkipListener implements ClientLifeCycleListener {

    @Override
    public void clientCreated(Client client) {
        if (client.getConduit() instanceof HTTPConduit) {
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            TLSClientParameters params = conduit.getTlsClientParameters();
            if (null == params) {
                params = new TLSClientParameters();
                conduit.setTlsClientParameters(params);
            }
            params.setTrustManagers(new TrustManager[] { new TrustAllManager()});// TrustAllManager是自己写的
            params.setDisableCNCheck(true);
        }
    }

    @Override
    public void clientDestroyed(Client client) {
        // Do Nothing
    }

    private class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            // Do Nothing
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            // Do Nothing
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            //return new X509Certificate[0];
            return null;
        }
    }
}
