package com.dy.nettypro.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dingyu
 * @description 编解码案例服务端
 * @date 2020/1/15
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossgroup = new NioEventLoopGroup(1);
        EventLoopGroup workgroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();

        try {
            //设置服务端信息
            bootstrap.group(bossgroup,workgroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ServerInitializer());

            //绑定端口
            ChannelFuture channelFuture = bootstrap.bind(7000).sync();

            //监听关闭端口
            channelFuture.channel().closeFuture().sync();


        }finally {
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }




    }
}
