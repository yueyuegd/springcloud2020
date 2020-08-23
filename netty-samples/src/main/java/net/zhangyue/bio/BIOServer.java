package net.zhangyue.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(() -> handler(socket));
        }

    }

    public static void handler(Socket socket) {
        try {
            System.out.println("线程信息，线程ID：" + Thread.currentThread().getId() + " 名称：" + Thread.currentThread().getName());
           byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while(true) {
                int length = inputStream.read(bytes);
                if (length != -1) {
                    System.out.println(new String(bytes, 0 , length));
                } else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
