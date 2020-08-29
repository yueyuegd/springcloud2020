package net.zhangyue.netty.codec2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import net.zhangyue.netty.codec.StudentPOJO;

/**
 * 说明：
 * 1.我们自定义一个handler，需要继承netty规定好的某个handlerAdapter（规范）
 * 2.这时我们自定义一个handler，才能称为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据的事件（这里我们可以读取客户端发送的消息）：此时类型是protobuf
     * @param ctx   上下文对象，含有管道pipeline，通道channel，地址
     * @param msg   就是客户端发送的数据，默认是Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //System.out.println("server ctx=" + ctx);
        //将 msg转化成ByteBuf，是Netty提供的不是NIO的。
        ////ByteBuf byteBuf = (ByteBuf) msg;
        //System.out.println("客户端发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        //System.out.println("客户端地址：" + ctx.channel().remoteAddress());
        //StudentPOJO.Student student = (StudentPOJO.Student) msg;
        //System.out.println("客户端发送的数据 id:" + student.getId() +" name:" + student.getName());
        MyDataInfo.MyMessage myMessage = (MyDataInfo.MyMessage) msg;
        MyDataInfo.MyMessage.DataType dataType = myMessage.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.StudentType) {
            MyDataInfo.Student student = myMessage.getStudent();
            System.out.println("学生类型，id：" + student.getId() + " name：" + student.getName());
        } else if (dataType == MyDataInfo.MyMessage.DataType.WorkerType) {
            MyDataInfo.Worker worker = myMessage.getWorker();
            System.out.println("工人类型，name：" + worker.getName() + " age：" + worker.getAge());
        } else {
            System.out.println("传输的类型不正确");
        }
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
