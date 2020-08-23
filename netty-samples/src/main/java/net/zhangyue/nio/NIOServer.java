package net.zhangyue.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws Exception {
        //创建一个ServerSocketChannel -》 ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector
        Selector selector = Selector.open();
        //绑定端口号6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //在serverSocketChannel注册到Selector，关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true) {
            //调用select()方法用于返回Selector关注的事件，这里等待一秒，如果没有事件发生就返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了一秒。");
                continue;
            }
            //如果返回大于0，就获取到相应的事件，就是SelectionKey的集合
            //1.如果返回大于0表示已经获取到关注的事件
            //2.selector.selectedKeys()方法返回关注的事件
            //3.通过selectionKeys反向获得通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历selectionKeys集合，使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件做相应的处理
                //如果这个客户端是连接事件的话走这个if逻辑
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将SocketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将SocketChannel注册到Selector，此时关注的是OP_READ事假，同时给SocketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接成功，生成一个socketChannel：" + socketChannel.hashCode());
                }
                if (key.isReadable()) {
                    //如果此时的读事件的话走这个逻辑
                    //通过key反向获取通道
                    SocketChannel channel = (SocketChannel)key.channel();
                    //获取该channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    //通过通道将数据读入到buffer中
                    channel.read(byteBuffer);
                    //可以输出内容
                    System.out.println("from 客户端内容：" + new String(byteBuffer.array()));
                }
                //之后需要手动将这个key删除，防止重复操作
                keyIterator.remove();
            }
        }

    }
}
