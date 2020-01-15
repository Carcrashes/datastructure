package com.dy.nettypro.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author dingyu
 * @description 客户端
 * @date 2020/1/7
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        //客户端只需要一个事件循环组
        EventLoopGroup group=new NioEventLoopGroup();

        //创建客户端对象  !!!注意客户端使用的BootStreap
        Bootstrap bootstrap = new Bootstrap();
        try {
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());//给管道中添加处理器
                        }
                    });

            System.out.println("客户端 is ready ok");
            //启动客户端去连接服务器
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",6669).sync();

            //给关闭客户端进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
