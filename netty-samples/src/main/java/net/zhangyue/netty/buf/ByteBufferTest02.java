package net.zhangyue.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBufferTest02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,zy", CharsetUtil.UTF_8);
        //使用相关的方法
        if (byteBuf.hasArray()) {
            //true
            byte[] array = byteBuf.array();
            System.out.println(new String(array, CharsetUtil.UTF_8));
            System.out.println("byteBuf:" + byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readByte());
            int length = byteBuf.readableBytes();
            System.out.println("byteBuf length :" + length);
        }
    }
}
