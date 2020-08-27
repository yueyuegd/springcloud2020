package net.zhangyue.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 说明：
 * 1.我们自定义一个handler，需要继承netty规定好的某个handlerAdapter（规范）
 * 2.这时我们自定义一个handler，才能称为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据的事件（这里我们可以读取客户端发送的消息）
     * @param ctx   上下文对象，含有管道pipeline，通道channel，地址
     * @param msg   就是客户端发送的数据，默认是Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //比如我们这里有一个很耗时的任务，我们可以异步执行，提交该handler对应的NIOEventLoop的taskQueue中
        //解决方案1是用户自定义普通的任务
        ctx.channel().eventLoop().execute(() -> {
            System.out.println("当前运行的线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ctx.channel().eventLoop().execute(() -> {
            System.out.println("当前运行的线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //用户自定义定时任务，该任务提交到scheduleTaskQueue
        ctx.channel().eventLoop().schedule(() ->
                System.out.println("当前线程名称：" + Thread.currentThread().getName()),3, TimeUnit.SECONDS);
        System.out.println("go on...");
        //System.out.println("server ctx=" + ctx);
        //将 msg转化成ByteBuf，是Netty提供的不是NIO的。
        //ByteBuf byteBuf = (ByteBuf) msg;
        //System.out.println("客户端发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        //System.out.println("客户端地址：" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存并刷新
        //一般来讲，我们要对这个数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, client1", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
