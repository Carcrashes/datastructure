package com.dy.nettypro.netty.httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author dingyu
 * @description 处理器
 * @date 2020/1/10
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    /**
     * 读取客户端数据
     * @param ctx  通道上下文
     * @param msg 请求的规范数据
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //判断msg是一个httprequest数据
        if (msg instanceof HttpRequest){

            System.out.println("peiplechannel hascode"+ctx.channel().hashCode()+" httpServerHashCode"+this.hashCode());
            System.out.println("服务端地址："+ctx.channel().remoteAddress());
            System.out.println("msg类型:"+msg.getClass());

            //将msg 转换为标准
            HttpRequest httpRequest= (HttpRequest) msg;
            //获取uri 资源
            URI uri=new URI(httpRequest.uri());
            if (uri.getPath().equals("/favicon.ico")){
                System.out.println("不处理该资源");
                return;
            }

            //回复给浏览器信息(http协议)

            //定义回复内容
            ByteBuf content= Unpooled.copiedBuffer("服务器回复消息", CharsetUtil.UTF_8);

            //构建回复符合http协议 httpresponse
            //参数  1：http协议版本   2:http协议状态  3:返回消息内容
            FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);

            //设置头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //将构建好的消息返回给浏览器
            ctx.writeAndFlush(response);
        }

    }
}
