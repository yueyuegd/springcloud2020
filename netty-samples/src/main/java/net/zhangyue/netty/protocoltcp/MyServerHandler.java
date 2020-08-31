package net.zhangyue.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常信息：" + cause.getMessage());
        ctx.close();
    }


    /**
     * 处理客户端发送过来的数据，将解码成MessageProtocol的对象进行处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收到客户端发送的消息并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务器接收到的消息如下：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接收到消息包数量：" + (++this.count));

        //回复消息给客户端
        String responseContext = UUID.randomUUID().toString();
        byte[] bytes = responseContext.getBytes();
        int length = bytes.length;
        //构建一个MessageProtocol对象
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(bytes);
        ctx.writeAndFlush(messageProtocol);
    }
}
