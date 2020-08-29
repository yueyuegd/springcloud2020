package net.zhangyue.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 演示Netty中ByteBuf的使用
 */
public class ByteBufTest01 {

    public static void main(String[] args) {
        //创建一个buffer
        //1.创建对象， 对象包含一个数据arr，是一个byte[10]
        //2.在netty的buffer中，不需要使用flip进行反转
        //因为底层维护了readIndex和writeIndex
        //3.通过readIndex和writeIndex和capacity，将buffer分成三个区域
        //0------readIndex：已经读取的区域
        //readIndex------------writeIndex：可读的区域
        //writeIndex------------capacity：可写的区域
        ByteBuf byteBuf = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println("byteBuf capacity:" + byteBuf.capacity());
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.readByte());
        }

    }
}
