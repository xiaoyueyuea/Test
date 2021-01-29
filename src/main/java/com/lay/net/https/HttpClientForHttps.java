package com.lay.net.https;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/29 15:43
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/29 lei.yue 1.0 create file
 */
//HttpClient发送https请求
public class HttpClientForHttps {

    private static CloseableHttpClient httpsClient = null;

    private final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();

    static class AnyTrustStrategy implements TrustStrategy {

        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            return true;
        }

    }

    {
        try {
            final RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
            final ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
            registryBuilder.register("http", plainSF);
            final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new AnyTrustStrategy()).build();
            final LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier());
            registryBuilder.register("https", sslSF);
            final Registry<ConnectionSocketFactory> registry = registryBuilder.build();
            final PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
            connManager.setMaxTotal(5000);
            httpsClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
        } catch (final Exception e) {
            //异常
        }
    }

    public void test(){
        try{
            final ObjectMapper mapper = new ObjectMapper();
            final HttpPost httppost = new HttpPost("请求地址");
            httppost.setConfig(requestConfig);
            httppost.addHeader("Content-Type", "application/json");

            httppost.setEntity(new StringEntity(mapper.writeValueAsString("请求参数"), "UTF-8"));
            final CloseableHttpResponse execute = httpsClient.execute(httppost);
            final String resultJson = EntityUtils.toString(execute.getEntity());

            //..后续操作与发送http请求基本一致
        }catch (Exception e){
            //异常
        }finally {
            //关闭资源
        }
    }
}
