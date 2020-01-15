package com.dy.nettypro.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author dingyu
 * @description 服务端处理器
 * @date 2020/1/15
 */
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("from client:"+ctx.channel().remoteAddress()+" message "+msg);
        //给客户端发送一个long
        ctx.writeAndFlush(98765L);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("err message is" +cause.getMessage());
    }
}
