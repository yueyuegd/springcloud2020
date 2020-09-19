package net.zhangyue.netty.dubborpc.provider;

import net.zhangyue.netty.dubborpc.netty.NettyServer;

public class ServerBootstrap {

    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
