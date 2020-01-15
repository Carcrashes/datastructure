package com.dy.nettypro.netty.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author dingyu
 * @description 服务端业务逻辑处理
 * @date 2020/1/15
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //获取pieple管道
        ChannelPipeline pipeline = ch.pipeline();

        //添加处理器
        pipeline.addLast(new MyByteToLongDecoder2());
        //出站的handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //自定义的handler 处理业务逻辑
        pipeline.addLast(new MyServerHandler());


    }
}
