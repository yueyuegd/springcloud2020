package net.zhangyue.netty.protocoltcp;

/**
 * 协议包对象
 */

public class MessageProtocol {

    //关键：数据的字节长度
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
