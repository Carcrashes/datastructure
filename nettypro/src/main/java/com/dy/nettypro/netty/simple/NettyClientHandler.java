package com.dy.nettypro.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author dingyu
 * @description 客户端处理器
 * 自定义一个handler 继承netty规定的HandlerAdapter （规范），这样才能称之为一个handler
 * @date 2020/1/7
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道有事件就触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client:"+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,服务端",CharsetUtil.UTF_8));
    }

    /**
     * 当通道有读取事件就触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=(ByteBuf)msg;
        //System.out.println("服务端回复消息："+buf.toString(CharsetUtil.UTF_8));
        //System.out.println("服务端地址："+ctx.channel().remoteAddress());

        //netty 任务对列中三种典型使用场景

        //1.用户程序自定义方式
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,服务端1",CharsetUtil.UTF_8));
                    System.out.println("channle hashcode:" +ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常"+e.getMessage());
                }
            }
        });

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,服务端2",CharsetUtil.UTF_8));
                    System.out.println("channle hashcode:" +ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("发生异常"+e.getMessage());
                }
            }
        });

        //2.用户自定义定时任务 该任务提交到scheduleTaskQueue中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,服务端3",CharsetUtil.UTF_8));
                    System.out.println("channle hashcode:" +ctx.channel().hashCode());
                }catch (Exception e){
                    System.out.println("异常信息:"+e.getMessage());
                }

            }
        },5, TimeUnit.SECONDS);


        //3.非当前Reactor 线程调用channle(处理方式。根据用户标记，将对应的channle保存到一个集合中，需要用的时候，再根据用户标记取出)


        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,is ok",CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx 线程上下文
     * @param cause 异常信息
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //关闭通道
        ctx.close();
    }
}
