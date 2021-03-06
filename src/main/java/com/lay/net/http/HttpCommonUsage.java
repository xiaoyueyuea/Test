package com.lay.net.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/28 11:20
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/28 lei.yue 1.0 create file
 */
//发送http请求的用法
public class HttpCommonUsage {

    /**
     * HttpUrlConnection：由jdk中java.net包提供,继承自URLConnection，可用于向指定网站发送GET请求、POST请求。
     */
    public void httpUrlConnectionTest(){
        //请求地址
        String requestUrl = "";
        {
            try{
                final HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
                //设置请求头
                connection.setRequestProperty("Accept","application/json");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty("Cookie","");//携带Cookie
                connection.setConnectTimeout(30 * 1000);
                connection.setReadTimeout(30 * 1000);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");//POST请求
                //设置能处理输入/输出流
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                //构建请求参数
                //方式一：使用URLEncoder.encode + & 拼接参数,适用于Content-Type为application/x-www-form-urlencoded，参数格式为key=value&key=value的请求,接收方需用URLEncoder.decode解码。对于Get请求，是将参数转换?key=value&key=value格式，拼接到url后
                String data1 = "phones=" + URLEncoder.encode("参数1", "UTF-8") + "&content=" + URLEncoder.encode("参数2","UTF-8");
                DataOutputStream outputStream1 = new DataOutputStream(connection.getOutputStream());
                outputStream1.writeBytes(data1);
                outputStream1.flush();
                outputStream1.close();

                //方式二：构建参数Bean,转为字符串后传输
                ObjectMapper mapper = new ObjectMapper();
                String requestPram = mapper.writeValueAsString("参数Bean");
                DataOutputStream outputStream2 = new DataOutputStream(connection.getOutputStream());
                outputStream2.write(requestPram.getBytes("utf-8"));
                outputStream2.flush();
                outputStream2.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
                connection.disconnect();
                //得到结果字符串
                String result = sb.toString();
            }catch (Exception e){
            }
        }
    }

    /**
     *HttpClient就是一个增强版的HttpURLConnection，HttpURLConnection可以做的事情HttpClient全部可以做；HttpURLConnection没有提供的有些功能，HttpClient也提供了，但它只是关注于如何发送请求、接收响应，以及管理HTTP连接。
     * get请求
     */
    public void HttpClientDoGetTest(){
        //CloseableHttpClient实现了Closeable接口
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try {
            //创建一个默认的CloseableHttpClient实例
            httpClient = HttpClients.createDefault();
            //创建HttpGet远程连接实例
            HttpGet httpGet = new HttpGet("请求地址");
            //设置请求头，鉴权
            httpGet.setHeader("Authorization","");
            //设置请求配置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(3000)
                    .setConnectTimeout(3500)
                    .setSocketTimeout(60000)
                    .build();
            //为HttpGet设置配置
            httpGet.setConfig(requestConfig);
            //执行get请求得到响应
            response = httpClient.execute(httpGet);
            //通过CloseableHttpResponse得到返回对象
            HttpEntity httpEntity = response.getEntity();
            //通过EntityUtils工具类将HttpEntity转为字符串
            String result = EntityUtils.toString(httpEntity);
        }catch (Exception e){
            //异常
        }finally {
            if(httpClient != null){
                try{
                    httpClient.close();
                }catch (Exception e){
                    //异常
                }
            }
            if(response != null){
                try{
                    response.close();
                }catch (Exception e){
                    //异常
                }
            }
        }
    }

    /**
     * HttpClient
     * post请求
     */
    public void HttpClientDoPostTest(Map<String,Object> pramMap){

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try{
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("请求地址");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(3000)
                    .setConnectTimeout(3500)
                    .setSocketTimeout(60000)
                    .build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            //封装post请求参数
            //方式一：使用NameValuePair，与UrlEncodedFormEntity配合使用。
            //1.NameValuePair:简单名称值对节点类型,大多用于发送Post请求时用该list来存放参数。
            //2.UrlEncodedFormEntity这个类是用来把输入数据编码成合适的内容，继承自使用StringEntity。默认ContentType为application/x-www-form-urlencoded，所以使用这个类做参数，会将参数以key1=value1&key2=value2的键值对形式发出。
            List<NameValuePair> nvps = new ArrayList<>();
            if(pramMap != null && pramMap.size() > 0){
                for (Map.Entry<String, Object> entry : pramMap.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));

            //方式二:使用StringEntity。这个类做参数的时候设置比较灵活。不指定ContentType时默认为text/plain.
            ObjectMapper mapper = new ObjectMapper();
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(pramMap),"UTF-8"));

            response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity);
        }catch (Exception e){
            //异常
        }finally {
            //关闭连接
        }
    }

}
