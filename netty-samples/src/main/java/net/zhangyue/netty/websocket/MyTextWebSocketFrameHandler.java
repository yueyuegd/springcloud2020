package net.zhangyue.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * 这里 TextWebSocketFrame 类型表示一个文本帧(frame)
 */
public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息：" + msg.text());
        //回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间：" + LocalDateTime.now() + "-" +
                msg.text()));
    }

    /**
     * 当web客户端连接后触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //ID是唯一值，asLongText这个返回的是唯一 ，asShortText 这个方法返回的可能不唯一
        System.out.println("handlerAdded被调用,Long id:[" + ctx.channel().id().asLongText());
        System.out.println("handlerAdded被调用,Short id:[" + ctx.channel().id().asShortText());
    }

    /**
     * 当客户端连接断开后触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved被调用,Long id :[" + ctx.channel().id().asLongText());
    }

    /**
     * 发生异常后触发该方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生：" + cause.getMessage());
        ctx.close();
    }
}
