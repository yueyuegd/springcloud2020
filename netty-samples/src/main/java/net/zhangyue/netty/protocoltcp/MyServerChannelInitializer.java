package net.zhangyue.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //添加解码器，用于对客户端发送来的消息进行解码，入站调用
        pipeline.addLast(new MyMessageDecoder());
        //添加编码器，用于给客户端发消息的时候编码数据，出站调用
        pipeline.addLast(new MyMessageEncoder());
        //自定义的业务处理逻辑，入站和出站都会调用
        pipeline.addLast(new MyServerHandler());
    }
}
