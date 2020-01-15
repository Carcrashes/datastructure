package com.dy.nettypro.netty.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author dingyu
 * @description WebSocket服务端处理
 * @date 2020/1/13
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossgroup=new NioEventLoopGroup(1);
        EventLoopGroup workgroup=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();

        try {
            bootstrap.group(bossgroup,workgroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) //连接添加日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取管道
                            ChannelPipeline pipeline = ch.pipeline();
                            //添加处理器
                            pipeline.addLast(new HttpServerCodec());// 使用http编解码器  websocket 协议握手阶段使用http协议，数据传输阶段使用tcp/ip协议
                            pipeline.addLast(new ChunkedWriteHandler()); //websocket使用块方式写

                            //http数据长连接数据传输过程中是分段，HttpObjectAggregator将多段聚合
                            //这就是为什么浏览器发送大量数据时，会发送多次请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //websocket 协议数据传输在tcp/ip 以帧形式传输
                            //WebSocketServerProtocolHandler 是将http协议升级到ws协议，保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //添加自定义处理器
                            pipeline.addLast(new MyServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(7000).sync();

            //开启监听关闭通道
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }

    }
}
