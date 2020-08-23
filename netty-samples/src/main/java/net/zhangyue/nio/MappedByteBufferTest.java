package net.zhangyue.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明：
 * MappedByteBuffer：可以让文件直接在内存（堆外内存）中修改，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/1.txt", "rw");
        FileChannel randomAccessFileChannel = randomAccessFile.getChannel();
        /**
         * map方法：
         * 参数一：FileChannel.MapMode.READ_WRITE使用读写模式
         * 参数二：0表示可以直接修改的起始位置
         * 参数三：5表示映射到内存的大小(不是索引位置)，即将文件的多少个字节映射到内存中，可以直接修改的范围就是0-5
         */
        MappedByteBuffer mappedByteBuffer = randomAccessFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte)'H');
        mappedByteBuffer.put(3,(byte)'L');
        randomAccessFile.close();
        randomAccessFileChannel.close();
    }
}
