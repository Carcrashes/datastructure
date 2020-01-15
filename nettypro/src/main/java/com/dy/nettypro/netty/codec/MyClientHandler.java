package com.dy.nettypro.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author dingyu
 * @description 客户端处理器
 * @date 2020/1/15
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {


    /**
     * 处理连接事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("发送数据");
        ctx.writeAndFlush(1234567L);
        //ctx.writeAndFlush("aaaa"); 此时MyLongToByteEncoder不会进行处理，只会处理Long类型数据
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("from server message:"+msg);

    }
}
