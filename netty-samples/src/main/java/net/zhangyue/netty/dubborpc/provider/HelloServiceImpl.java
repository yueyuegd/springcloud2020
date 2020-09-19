package net.zhangyue.netty.dubborpc.provider;

import net.zhangyue.netty.dubborpc.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {

    /**
     * 当有消费者调用该方法的时候返回一个值
     * @param message
     * @return
     */
    @Override
    public String hello(String message) {
        System.out.println("收到客户端消息：" + message);
        //根据Message不同返回不同的结果
        if (message != null) {
            return "你好客户端，我已经收到你的消息[" + message + "]";
        } else {
            return "你好客户端，我已经收到你的消息";
        }
    }
}
