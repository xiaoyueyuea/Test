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

    public static void main(String[] args) throws IOException {
        //writeContentToFileByBytesStream();
        //getContentFromFileByBytesStream();
        //getContentFromFileByCharacterStream();
        //writeContentToFileByCharacterStream();
        //bisAndBos();
        brAndBw();
    }
}
