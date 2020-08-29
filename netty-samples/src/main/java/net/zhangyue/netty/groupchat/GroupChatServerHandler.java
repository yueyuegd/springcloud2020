package net.zhangyue.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    //定义一个channel组 ，管理所有的channel
    //GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 表示一旦连接建立，第一个被执行的方法
     * 将当前channel加入到channelGroup中
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户端加入聊天的信息推送到其他在线的客户端
        /**
         * 下面这个方法会将channels中所有的channel遍历，并发送消息，不需要自己遍历
         */
        channels.writeAndFlush("在" + simpleDateFormat.format(new Date()) + ":[客户端]" + channel.remoteAddress() + " 加入聊天");
        channels.add(channel);
    }

    /**
     * 断开连接，将消息推送给当前在线的客户端
     * 触发该方法的时候会移除该channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //channels.remove(channel);
        channels.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了");

    }

    /**
     * 当channel处于活动状态，提示上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    /**
     * 表示channel处于不活动的状态，提示离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了");
    }

    /**
     * 读取数据
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //读取channel
        Channel channel = channelHandlerContext.channel();
        channels.forEach(ch -> {
            if (channel != ch) { //如果是别的channel，需要转发消息
                ch.writeAndFlush("[客户端]" + channel.remoteAddress() + "发送了消息：" + s);
            } else {
                //如果是自己的话,辉县自己发送的消息
                ch.writeAndFlush("[自己]发送了消息：" + s);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道，异常发生的话
        ctx.close();
    }
}
