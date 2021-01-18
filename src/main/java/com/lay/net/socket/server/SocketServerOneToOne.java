package com.lay.net.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/18 10:47
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/18 lei.yue 1.0 create file
 */

//服务器端(与客户端一对一)
public class SocketServerOneToOne {
    public static void main(String[] args) throws IOException {
        // 端口号
        int port = 7000;
        // 在端口上创建一个服务器套接字(绑定端口)
        ServerSocket serverSocket = new ServerSocket(port);
        // 监听来自客户端的连接
        Socket socket = serverSocket.accept();

        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

        DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));

        do {
            double length = dis.readDouble();
            System.out.println("服务器端收到的边长数据为：" + length);
            double result = length * length;
            dos.writeDouble(result);
            dos.flush();
        } while (dis.readInt() != 0);
        socket.close();
        serverSocket.close();
    }
}
