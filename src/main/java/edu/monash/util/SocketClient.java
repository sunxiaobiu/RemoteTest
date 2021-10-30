package edu.monash.util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SocketClient {

    public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 8888;//服务器端口号

    public static void generateAPKPatch() {
        System.out.println("客户端启动...");
        System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");
        Socket socket = null;
        try {
            //创建一个流套接字并将其连接到指定ManualTimeSuggestionTest.java主机上的指定端口号
            socket = new Socket(IP_ADDR, PORT);

            //读取服务器端数据
            DataInputStream input = new DataInputStream(socket.getInputStream());
            //向服务器端发送数据
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //System.out.print("请输入: \t");
            //String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
            String str = "sudo ./gradlew tinkerPatchDebug";
            out.writeUTF(str);

            String ret = input.readUTF();
            System.out.println("服务器端返回过来的是: " + ret);

            out.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("客户端异常:" + e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                    System.out.println("客户端 finally 异常:" + e.getMessage());
                }
            }
        }
    }

    public static void chmod777(String path) {
        System.out.println("【chmod777】...");
        Socket socket = null;
        try {
            //创建一个流套接字并将其连接到指定主机上的指定端口号
            socket = new Socket(IP_ADDR, PORT);

            //读取服务器端数据
            DataInputStream input = new DataInputStream(socket.getInputStream());
            //向服务器端发送数据
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //System.out.print("请输入: \t");
            //String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
            String str = "sudo chmod -R 777 "+path;
            out.writeUTF(str);

            String ret = input.readUTF();
            System.out.println("【chmod777】服务器端返回过来的是: " + ret);

            out.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("【chmod777】客户端异常:" + e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                    System.out.println("【chmod777】客户端 finally 异常:" + e.getMessage());
                }
            }
        }
    }
}
