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
 *传输层的TCP是基于网络层的IP协议的，而应用层的HTTP协议又是基于传输层的TCP协议的，而Socket本身不算是协议，它只是提供了一个针对TCP或者UDP编程的接口。socket是对端口通信开发的工具,它要更底层一些，位于传输层。
 * socket屏蔽了各个协议的通信细节，使得程序员无需关注协议本身，直接使用socket提供的接口来进行互联的不同主机间的进程的通信
 * TCP用主机的IP地址加上主机上的端口号作为TCP连接的端点，这种端点就叫做套接字(socket)。套接字使用TCP提供了两台计算机之间的通信机制。 客户端程序创建一个套接字，并尝试连接服务器的套接字。
 * 连接建立后，通过使用 I/O 流在进行通信，每一个socket都有一个输出流和一个输入流，客户端的输出流连接到服务器端的输入流，而客户端的输入流连接到服务器端的输出流。
 *
 *1.流格式套接字（Stream Sockets）：基于 TCP 协议，是“面向连接的套接字”。有连接套接字非常可靠，万无一失，但是传输效率低，耗费资源多。
 *2.数据报格式套接字（Datagram Sockets）：基于 UDP 协议，是“无连接的套接字”。无连接套接字传输效率高，但是不可靠，有丢失数据包、捣乱数据的风险。
 */
//客户端
public class SocketClient1 {
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
