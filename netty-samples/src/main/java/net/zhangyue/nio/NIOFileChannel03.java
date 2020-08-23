package net.zhangyue.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileInputChannel = fileInputStream.getChannel();
        File file1 = new File("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileChannel fileOutChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        while(true) {
            byteBuffer.clear();
            int length = fileInputChannel.read(byteBuffer);
            if (length == -1) {
                break;
            }
            byteBuffer.flip();
            fileOutChannel.write(byteBuffer);
        }
        fileInputChannel.close();
        fileOutputStream.close();
    }
}
