package com.dy.nettypro.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author dingyu
 * @description 群聊系统服务器端
 * @date 2020/1/13
 */
public class NettyGroupChatServer {


    private Integer port;// 服务监控的端口

    public NettyGroupChatServer(Integer port) {
        this.port=port;
    }

    // 运行服务端
    public void run() throws Exception {

        EventLoopGroup bossgroup=new NioEventLoopGroup(1);//处理连接线程组
        EventLoopGroup workgroup=new NioEventLoopGroup(); //cpu 核心数 *2  进行业务处理线程组

        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            //配置服务端信息
            serverBootstrap.group(bossgroup,workgroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128) //设置连接任务队列128
                    .childOption(ChannelOption.SO_KEEPALIVE,true)// 设置工作处理线程组保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取管道(管道维护了一个Channel双向链表，包含多个处理器)
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            //加入编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            //加入自定义处理器
                            pipeline.addLast(new NettyGroupChatServerHandler());
                        }
                    });

                    //绑定端口
                    ChannelFuture channelFuture = serverBootstrap.bind(port).sync();//异步处理服务器
                    System.out.println("服务器启动成功。。。。。");

                    //关闭服务
                    channelFuture.channel().closeFuture().sync();
        }finally {
                    bossgroup.shutdownGracefully();
                    workgroup.shutdownGracefully();
        }

    }



    public static void main(String[] args) throws Exception {
        new NettyGroupChatServer(7000).run();

    }
}
