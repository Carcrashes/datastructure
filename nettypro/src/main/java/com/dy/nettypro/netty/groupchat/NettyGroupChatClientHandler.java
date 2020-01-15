package com.dy.nettypro.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author dingyu
 * @description 群聊客户端处理器
 * @date 2020/1/13
 */
public class NettyGroupChatClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 读取通道信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
