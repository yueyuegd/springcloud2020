package net.zhangyue.netty.dubborpc.customer;

import net.zhangyue.netty.dubborpc.netty.NettyClient;
import net.zhangyue.netty.dubborpc.publicinterface.HelloService;

public class ClientBootstrap {

    //这里设置协议头
    private static final String PROVIDERNAME = "HelloService#hello#";

    public static void main(String[] args) {
        //创建一个消费者
        NettyClient customer = new NettyClient();
        //创建代理对象
        HelloService helloService = (HelloService) customer.getBean(HelloService.class, PROVIDERNAME);

        //通过代理对象调用服务器提供的方法
        String result = helloService.hello("hello dubbo");
        System.out.println("调用服务器hello方法的返回值：" + result);

    }
}
