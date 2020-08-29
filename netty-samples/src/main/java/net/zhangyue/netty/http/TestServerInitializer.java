package net.zhangyue.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器
        //得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        //加入一个Netty提供的HttpServerCodec,是Netty提供粗粒HTTP 编解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        //再加入一个自定义的handler
        pipeline.addLast("MyTestServerHandler", new TestServerHandler());
    }
}
