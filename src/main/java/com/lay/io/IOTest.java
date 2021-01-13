package com.lay.io;



/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/28 15:00
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/28 lei.yue 1.0 create file
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 字节流操作类(bytes streams)和字符流操作类(character streams)组成了Java IO体系,Java中所有流的相关操作的类，都继承自以下四个抽象类
 * 1.字节流操作类:InputStream,OutputStream
 * 2.字符流操作类:Reader,Writer
 *
 * 处理纯文本数据，优先考虑使用字符流，除此之外都使用字节流
 * Input(Reader)和Output(writer)是针对当前程序而言,输入流是把数据从别处读入本程序内存，输出流则是把数据从本程序内存写到别处。
 */
public class IOTest {

    final static String FILE_PATH = "C:/Users/dauron/Desktop/IO测试.txt";

    /**
     * FileInputStream
     * 使用字节流从外部文件读数据，若数据包含中文，会产生乱码，推荐使用字符流。
     * @throws IOException
     */
    public static  void getContentFromFileByBytesStream() throws IOException {
        File file = new File(FILE_PATH);
        if(file.exists()){
            InputStream in = new FileInputStream(file);
            int a = 0;
            StringBuilder sb = new StringBuilder();
            while ((a = in.read()) != -1){
                sb.append((char) a);
            }
            in.close();
            System.out.println(sb.toString());
        }else {
            System.out.println("目标文件不存在");
        }
    }

    /**
     * FileOutputStream
     * 使用字节流写数据到外部文件
     * @throws IOException
     */
    public static void writeContentToFileByBytesStream() throws IOException{
        File file = new File(FILE_PATH);
        String str = "IO测试";
        OutputStream out = new FileOutputStream(file,true);//true代表将在原文件基础上进行追加，不填写此参数默认为false
        out.write(str.getBytes());
        out.flush();
        out.close();
    }

    /**
     * FileReader
     *使用字符流从外部文件读数据
     * @throws IOException
     */
    public static void getContentFromFileByCharacterStream() throws IOException {
        File file = new File(FILE_PATH);
        if(file.exists()){
            Reader reader = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            int a = 0;
            while ((a = reader.read()) != -1){
                sb.append((char)a);
                System.out.println(a);
            }
            reader.close();
            System.out.println(sb.toString());
        }else {
            System.out.println("目标文件不存在");
        }
    }

    /**
     * FileWriter
     * 使用字符流写数据到外部文件
     * @throws IOException
     */
    public static void writeContentToFileByCharacterStream() throws IOException {
        File file = new File(FILE_PATH);
        String str = "使用字符流写数据到指定文件";
        Writer writer = new FileWriter(file,true);//true代表将在原文件基础上进行追加，不填写此参数默认为false
        writer.write(str);
        writer.flush();
        writer.close();
    }

    /**
     *BufferedInputStream  BufferedOutputStream  缓冲字节输入/输出流，是一个高级流(处理流)，与其他低级流配合使用。
     */
    public static void bisAndBos() throws IOException {
        //向外部文件写入数据
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH,true);
        BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);
        String data = "{abcd}";
        out.write(data.getBytes(),0,data.getBytes().length);
        out.flush();
        out.close();

        //读数据
        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        BufferedInputStream in = new BufferedInputStream(fileInputStream);
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[10240];//自定义缓冲区
        int flag = 0;
        while ((flag = in.read(buffer)) != -1){
            sb.append(new String(buffer, 0, flag));
        }
        in.close();
        System.out.println(sb.toString());
    }

    /**
     * BufferedReader BufferedWriter  缓冲字符输入/输出流，是一个高级流(处理流)，与其他低级流配合使用。
     * @throws IOException
     */
    public static void brAndBw() throws IOException{
        //写入
        FileWriter fileWriter = new FileWriter(FILE_PATH,true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        String data = "缓冲字符流测试数据";
        writer.write(data,0,data.length());
        writer.flush();
        writer.close();

        //读
        FileReader fileReader = new FileReader(FILE_PATH);
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        int size = 10;
        while ((flag = reader.read()) != -1){
            sb.append((char)flag);
        }
        reader.close();
        System.out.println(sb.toString());
    }

    /**
     * 提供了针对于字符数组 byte [] 的标准的IO操作方式
     * ByteArrayOutputStream 实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException。
     * ByteArrayInputStream 包含一个内部缓冲区，该缓冲区包含从流中读取的字节。内部计数器跟踪 read 方法要提供的下一个字节。关闭 ByteArrayInputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException。
     * @throws IOException
     */
    public static void baosAndBais() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        String data = "ByteArrayOutputStream和ByteArrayInputStream测试数据";
        byte[] bytes = data.getBytes();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(bytes,0,bytes.length);
        out.writeTo(fileOutputStream);
//        System.out.println(out.toString());
//        System.out.println(out.toByteArray());
        fileOutputStream.flush();
        out.close();

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while ((flag = in.read()) != -1){
            sb.append((char)flag);
        }
        in.close();
        System.out.println(sb.toString());

    }

    /**
     * CharArrayReader 该类实现了一个可用作字符输入流的字符缓冲区，即该类可利用字符缓冲区当做字符输入流进行读取工作
     * CharArrayWriter 该类实现了一个可用作字符输出流的字符缓冲区，当数据写入流时，缓冲区自动增长，请注意在此类上调用close（）无效，并且可以在流关闭后调用此类的方法而不生成IOException。
     */
    public static void carAndCaw()throws IOException{
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        String data = "CharArrayReader和CharArrayWriter测试数据";
        CharArrayWriter writer = new CharArrayWriter();
        writer.write(data.toCharArray(),0,data.length());
        writer.writeTo(fileWriter);
        //System.out.println(writer.toString());
        fileWriter.flush();//可能需要刷新，否则会被阻塞，写入文件失败（因为writer.writeTo方法加了锁）
        writer.flush();
        writer.close();

        CharArrayReader reader = new CharArrayReader(data.toCharArray());
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while ((flag = reader.read()) != -1){
            sb.append((char)flag);
        }
        reader.close();
        System.out.println(sb.toString());
    }

    /**
     * DataOutputStream 允许应用程序将基本Java数据类型写到基础输出流中。也可用于网络请求中传输数据
     * DataInputStream 允许应用程序以机器无关的方式从底层输入流中读取基本的Java类型,需要提前知道数据类型，读的顺序必须和写的顺序相同
     */
    public static void dosAndDis()throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        //String data = "好好学习";
        int a = 666;
        char b = 'b';
        //写入
        dataOutputStream.writeInt(a);
        dataOutputStream.writeChar(b);
        //dataOutputStream.write(data.getBytes());
        dataOutputStream.flush();
        dataOutputStream.close();

        //按顺序读
        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        int A = dataInputStream.readInt();
        char B = dataInputStream.readChar();
        System.out.println("A:" + A + ",B:" + B);

    }

    public static void dataOutputStreamTestOfNet() throws IOException {
        URL url = new URL("www.baidu.com");
        String data = "{value:1}";
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8"); // 设置发送数据的格式
        connection.connect();

        final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(data.getBytes("utf-8"));
        out.flush();
        out.close();

        //读取返回结果
        final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        final StringBuffer sbf = new StringBuffer();
        final String heads = connection.getHeaderField("X-Signature");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            sbf.append(lines);
        }
        sbf.append("&&" + heads);// 分隔符，用以区分返回信息和签名
        reader.close();
        System.out.println(sbf.toString());
        connection.disconnect();//关闭连接
    }

    public static void main(String[] args) throws IOException {
        //writeContentToFileByBytesStream();
        //getContentFromFileByBytesStream();
        //getContentFromFileByCharacterStream();
        //writeContentToFileByCharacterStream();
        //bisAndBos();
        //brAndBw();
        //baosAndBais();
        //carAndCaw();
        dosAndDis();
    }
}
