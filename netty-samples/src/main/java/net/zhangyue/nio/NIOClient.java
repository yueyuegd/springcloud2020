package net.zhangyue.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws Exception{
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞？？？
        socketChannel.configureBlocking(false);
        //提供服务器IP和端口号用于客户端与之建立连接和交互数据
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            //没有连接成功
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他事情。。。");
            }
        }
        //如果连接成功的话，发送数据给服务器端
        String str = "hello,zy";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据，将buffer写入到通道
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
