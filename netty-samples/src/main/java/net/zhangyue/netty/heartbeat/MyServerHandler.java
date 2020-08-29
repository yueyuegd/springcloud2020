package net.zhangyue.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx   上下文
     * @param evt   事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            //向下转型
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String idleType = null;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    idleType = "读空闲";
                    break;
                case WRITER_IDLE:
                    idleType = "写空闲";
                    break;
                case ALL_IDLE:
                    idleType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "--超时事件：" + idleType);
        }
    }
}
