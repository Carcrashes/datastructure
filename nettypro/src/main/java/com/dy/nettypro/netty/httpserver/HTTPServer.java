package com.dy.nettypro.netty.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dingyu
 * @description 模拟HTTP服务
 * @date 2020/1/10
 */
public class HTTPServer {

    public static void main(String[] args) {

        //定义bossgroup 和 workgroup 两个线程组
        EventLoopGroup bossgroup=new NioEventLoopGroup();
        EventLoopGroup workgroup=new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap=new ServerBootstrap();//定义服务端

            //配置参数
            bootstrap.group(bossgroup,workgroup) //配置两个线程组
                    .channel(NioServerSocketChannel.class) //设置通道处理类型
                    .childHandler(new ServiceInitlizer()); //设置workgroup 下的handler

            //绑定端口 //异步模型
            ChannelFuture chan = bootstrap.bind(8080).sync();

            //关闭端口 开启关闭端口的的监听
            chan.channel().closeFuture();

        }catch (Exception e){
            System.err.println("异常信息:"+e.getMessage());
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();

        }



    }
}
