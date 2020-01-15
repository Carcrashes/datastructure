package com.dy.nettypro.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dingyu
 * @description 服务端处理器
 * @date 2020/1/13
 */
public class NettyGroupChatServerHandler extends SimpleChannelInboundHandler<String> {


    //GlobalEventExecutor.INSTANCE 是一个全局的事件执行器 是一个单例
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    /**
     * handlerAdd 表示连接建立  一旦建立连接 第一个执行
     * 将当前的channel加入到ChannelGroup中
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将新加入群聊的用户的信息推送给其他已经连接的用户

        //channelGroup.writeAndFlush() 会遍历channelGroup中的所有channel，不需要我们手动遍历
        channelGroup.writeAndFlush("[用户]"+channel.remoteAddress()+" 加入聊天"+sdf.format(new Date()));
        channelGroup.add(channel);
    }

    /**
     * 断开连接  将用户离开群聊信息推送给其他用户
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //获取通道信息
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[用户]"+channel.remoteAddress()+"离开了\n");
        System.out.println("channelGroup size ："+channelGroup.size()); // 一旦Channel 离开 channelGroup会自动减一
    }

    /**
     * 表示channel处于活跃状态，提示xx上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线了~~~");
    }

    /**
     * 表示channel处理不活跃状态，提示xx离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线了~~~");
    }


    /**
     * 读取通道信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取通道
        Channel channel = ctx.channel();
        //此时我们遍历
        channelGroup.forEach(ch->{
            if (channel !=ch){
                ch.writeAndFlush("[用户]"+channel.remoteAddress()+"发送了消息:"+msg+"\n");
            }else{
                ch.writeAndFlush("[自己]"+channel.remoteAddress()+"发送了消息:"+msg+"\n");
            }
        });

    }

    /**
     * 读取事件完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }





    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("异常信息:"+cause.getMessage());
        //关闭通道
        ctx.close();
    }


}
