package com.dy.nettypro.netty.webSocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @author dingyu
 * @description websocket协议处理器
 * @date 2020/1/13
 */
public class MyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 建立连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //channel id 表示唯一的值   asLongText() 唯一  asShortText() 不唯一

        System.out.println("handlerAdded 被调用，channel id："+ctx.channel().id().asLongText());
        System.out.println("handlerAdded 被调用，channel id："+ctx.channel().id().asShortText());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved 被调用，channel id："+ctx.channel().id().asLongText());
    }

    /**
     * 读取事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("接收到信息:"+msg.text());
        //回复信息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间"+ LocalDateTime.now())+" "+msg.text());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常事件："+cause.getMessage());
    }
}
