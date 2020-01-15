package com.dy.nettypro.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @author dingyu
 * @description 服务端处理器
 * 自定义一个handler 继承netty规定的HandlerAdapter （规范），这样才能称之为一个handler
 * @date 2020/1/7
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据  读取客户端传输过来的数据
     * @param ctx 上下文对象 包含 pipeline（管道） channl（通道） 地址
     * @param msg 就是客户端发送的数据，默认object类型
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端读取线程"+Thread.currentThread().getName());
        System.out.println("server ctx:"+ctx);
        System.out.println("channel 和pipeline 的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();//实质是一个双向链表，出栈入栈

        //将msg转换为一个ByteBuf (这是netty提供的数据对象，不是NIO中的ByteBuffer)
        ByteBuf buf=(ByteBuf)msg;
        System.out.println("客户端发送的消息："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址："+ctx.channel().remoteAddress());
    }

    /**
     * 读取数据完毕之后处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存,并刷新，需要对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~",CharsetUtil.UTF_8));
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
