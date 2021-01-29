package com.lay.net.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import java.net.URL;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/29 15:13
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/29 lei.yue 1.0 create file
 */
//HttpsURLConnection发送https请求的常用用法。
//HTTPS是一种通过计算机网络进行安全通信的传输协议，经由HTTP进行通信，利用SSL/TLS建立全信道，加密数据包。HTTPS使用的主要目的是提供对网站服务器的身份认证，同时保护交换数据的隐私与完整性。TLS是传输层加密协议，前身是SSL协议(安全套接字协议)
public class HttpsUrlConnection {

    /**
     * 通过HttpsUrlConnection发送https请求
     */
    public void HttpsUrlConnectionTest(){
        //HttpsURLConnection:HttpURLConnection的子类
        HttpsURLConnection conn = null;
        final String BOUNDARY = "YXY";
        try{
            final HostnameVerifier hv = new HostnameVerifier() {

                @Override
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };

            conn = (HttpsURLConnection)new URL("请求地址").openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);//用于上传文件,boundary为分隔符
            conn.setSSLSocketFactory(getSSLSocketFactory());//设置SSL
            conn.setHostnameVerifier(hv);//默认静态主机名校验器
            conn.connect();

            //...之后的操作与发送http请求基本相同
        }catch (Exception e){
            //异常
        }finally {
            //关闭连接
        }
    }

    private SSLSocketFactory getSSLSocketFactory() throws Exception {
        final javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        final javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        final javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        return sc.getSocketFactory();
    }

    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException {
            return;
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException {
            return;
        }
    }
}
