package net.zhangyue.netty.http;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * 说明：
 * 1.SimpleChannelInboundHandler是ChannelInboundHandlerAdapter的子类
 * 2.HttpObject是客户端与服务器端相互通讯的数据封装实体
 */
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                HttpObject httpObject) throws Exception {
        //判断msg是不是HTTPRequest
        if (httpObject instanceof HttpRequest) {
            System.out.println("httpObject 类型：" + httpObject.getClass());
            System.out.println("客户端地址：" + channelHandlerContext.channel().remoteAddress());
            //回复消息给客户端[http协议]
            ByteBuf context = Unpooled.copiedBuffer("hello，我是服务器", CharsetUtil.UTF_8);
            //构造一个HTTP的响应，即HTTPResponse
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, context);
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, context.readableBytes());
            //将构建好的response返回
            channelHandlerContext.writeAndFlush(defaultFullHttpResponse);


        }
    }
}
