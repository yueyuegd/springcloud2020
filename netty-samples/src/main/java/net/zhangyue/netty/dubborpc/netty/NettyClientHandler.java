package net.zhangyue.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter
implements Callable {

    //上下文
    private ChannelHandlerContext context;
    //返回的结果
    private String result;
    //客户端调用方法时，传入的参数
    private String param;

    /** 3
     * 被代理对象调用，发送数据给服务器 -》 wait -》处于等待被唤醒的状态（ChannelRead） -》返回结果
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        //进行wait
        wait();
        //等待ChannelRead方法获取到服务器的结果后唤醒
        //将服务器返回的结果返回
        return result;
    }

    /** 1
     * 与服务器创建连接后就会调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //在这里设置值是为了在后面的方法使用
        context = ctx;
    }

    /** 4
     * 收到服务器的数据的时候被调用该方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        //唤醒等待的线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //2
    void setParam(String param) {
        this.param = param;
    }
}
