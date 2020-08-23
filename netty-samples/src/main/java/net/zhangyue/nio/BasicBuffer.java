package net.zhangyue.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {
        //创建一个Buffer，大小为5个整型
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向Buffer放入数据
        for (int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put(i * 2);
        }
        //如何从Buffer中读取数据
        //将Buffer转换，读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
