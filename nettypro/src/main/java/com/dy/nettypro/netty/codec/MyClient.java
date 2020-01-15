package com.dy.nettypro.netty.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author dingyu
 * @description 编解码客户端
 * @date 2020/1/15
 */
public class MyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();

        Bootstrap bootstrap=new Bootstrap();

        try {
            //配置服务器
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ClientInitalizer());

            //连接主机
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 7000).sync();

            //开启监听关闭端口
            channelFuture.channel().closeFuture().sync();


        }finally {
            group.shutdownGracefully();
        }
    }
}
