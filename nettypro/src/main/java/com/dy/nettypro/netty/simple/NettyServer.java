package com.dy.nettypro.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dingyu
 * @description 服务端
 * @date 2020/1/7
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //创建BossGroup 和WorkGroup
        //1.创建BossGroup和WorkGroup两个线程组
        //2.bossGroup 是 处理连接，真正处理客户端业务是workGroup,bossgroup 和workgroup都是无线循环
        //3.bossgroup 和workgroup 包含得NioEventLoop个数，默认是 cpu核数 *2 或者指定个数  NettyRuntime.availableProcessors() 获取cpu核心数

        EventLoopGroup bossGroup = new NioEventLoopGroup(1); //一个bossgroup 处理连接
        EventLoopGroup workGroup = new NioEventLoopGroup(); //默认 cpu核心数 * 2  进行真正的业务处理

        try {
            //创建服务端 配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程进行配置
            bootstrap.group(bossGroup, workGroup) //设置两个线程组  bossgroup线程组处理连接  workgroup线程组处理连接后的业务
                    .channel(NioServerSocketChannel.class) //使用NioServerSocketChannel作为通道的实现
                    .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象(匿名对象方式)
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());// 加入对应handler 给WorkGroup的EventLoop管道设置处理器
                        }
                    });

            System.out.println("服务器 is ready ok");

            //绑定端口并同步，生成了一个ChannelFuture对象  涉及到netty的异步模型
            ChannelFuture channelFuture = bootstrap.bind(6669).sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("监听 端口6669 成功");
                    }else{
                        System.out.println("监听 端口6669 失败");
                    }
                }
            });

            //对关闭通道进行监听，监听到关闭通道事件，再进行通道关闭，而不是直接直接关闭通道
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully(); //关闭服务
        }
    }
}
