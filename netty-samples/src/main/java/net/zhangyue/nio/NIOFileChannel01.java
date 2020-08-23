package net.zhangyue.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {
        String str = "hello,zy";
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/file01.txt");
        //通过fileOutputStream获取对应的FileChannel
        //这个fileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将str放入到buffer中
        byteBuffer.put(str.getBytes());
        //反转buffer，读取数据写入到channel中
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
        //fileChannel.close();;
    }
}
