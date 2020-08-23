package net.zhangyue.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFIleChannel04 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/1copy.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhangyue/github/springcloud2020/netty-samples/src/main/resources/2copy.txt");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //使用transferFrom完成拷贝
        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
        inputStreamChannel.close();
        outputStreamChannel.close();

    }
}
