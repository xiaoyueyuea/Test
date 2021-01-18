package com.lay.net.socket.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/18 10:53
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/18 lei.yue 1.0 create file
 */

/**
 *传输层的TCP是基于网络层的IP协议的，而应用层的HTTP协议又是基于传输层的TCP协议的，而Socket本身不算是协议，就像上面所说，它只是提供了一个针对TCP或者UDP编程的接口。socket是对端口通信开发的工具,它要更底层一些。
 * socket屏蔽了各个协议的通信细节，使得程序员无需关注协议本身，直接使用socket提供的接口来进行互联的不同主机间的进程的通信
 */
//客户端2，用于测试一个服务端响应多个客户端
public class SocketClient2 {
    public static void main(String[] args) throws IOException {

        int port = 7000;
        String host = "localhost";

        // 创建一个套接字并将其连接到命名主机上的指定端口号
        Socket socket = new Socket(host, port);

        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

        DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));

        Scanner sc = new Scanner(System.in);

        boolean flag = false;

        while (!flag) {

            System.out.println("请输入正方形的边长:");
            double length = sc.nextDouble();

            dos.writeDouble(length);
            dos.flush();

            double area = dis.readDouble();

            System.out.println("服务器返回的计算面积为:" + area);

            while (true) {

                System.out.println("继续计算？(Y/N)");

                String str = sc.next();

                if (str.equalsIgnoreCase("N")) {
                    dos.writeInt(0);
                    dos.flush();
                    flag = true;
                    break;
                } else if (str.equalsIgnoreCase("Y")) {
                    dos.writeInt(1);
                    dos.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}
