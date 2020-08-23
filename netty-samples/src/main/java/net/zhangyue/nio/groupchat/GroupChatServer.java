package net.zhangyue.nio.groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6667;
    //构造器，初始化工作
    public GroupChatServer() {
        try {
            //1.得到选择器
            selector = Selector.open();
            //2.得到ServerSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //3.绑定端口号
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            //4.将ServerSocketChannel注册到Selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //监听方法
    public void listen() {
        try {
            //循环处理
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    //有事件处理
                    Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey key = selectionKeyIterator.next();
                        if (key.isAcceptable()) {
                            //有连接事件
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println(socketChannel.getRemoteAddress() + " 上线");
                        }
                        if (key.isReadable()) {
                            //有读的事件,单独处理读的事件方法
                            readData(key);

                        }
                        selectionKeyIterator.remove();
                    }

                } else {
                    System.out.println("等待。。。。");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
    //读取数据的方法
    public void readData(SelectionKey selectionKey) {
        SocketChannel socketChannel = null;
        try {
            //取得关联的通道
            socketChannel = (SocketChannel) selectionKey.channel();
            //创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if (count > 0) {
                String msg = new String(byteBuffer.array());
                //输出消息
                System.out.println("from 客户端" + msg);
                //向其他客户端转发消息（排除自己），单独方法处理
                sendInfoToOtherClients(msg, socketChannel);
            }

        } catch (Exception e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + "离线了");
                //取消注册
                selectionKey.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void sendInfoToOtherClients(String msg, SocketChannel socketChannel) {
        System.out.println("服务器转发消息中。。。");
        //遍历selector上注册的所有通道
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            //排除自己
            if (channel instanceof SocketChannel && channel != socketChannel) {
                SocketChannel dest = (SocketChannel) channel;
                //将msg存储到目标通道的buffer
                try {
                    dest.write(ByteBuffer.wrap(msg.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
