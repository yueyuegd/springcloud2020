package net.zhangyue.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        //将通道的数据读入到buffer中
        fileChannel.read(byteBuffer);
        //将buffer中的数据转化为字符串
        System.out.println(new String(byteBuffer.array()));

    }
}
