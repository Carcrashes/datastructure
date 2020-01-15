package com.dy.nettypro.netty.httpserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author dingyu
 * @description 处理器
 * @date 2020/1/10
 */
public class ServiceInitlizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //得到管道
        ChannelPipeline pipeline = ch.pipeline();
        //向管道中添加 处理器
        //加入netty提供的处理的http编码
        pipeline.addLast("MyHttpServerCodecc", new HttpServerCodec());
        // 自定义一个handler
        pipeline.addLast("MyHttpServerHandler",new HttpServerHandler());



    }
}
