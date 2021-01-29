package com.lay.net.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
                connection.setRequestMethod("POST");
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
     */
    public void HttpClientTest(){

    }

}
