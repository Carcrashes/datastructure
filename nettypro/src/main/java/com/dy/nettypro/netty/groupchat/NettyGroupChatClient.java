package com.dy.nettypro.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author dingyu
 * @description 群聊客户端
 * @date 2020/1/13
 */
public class NettyGroupChatClient {

    private String host;

    private int port;

    public NettyGroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {

        //定义一个事件组
        EventLoopGroup group=new NioEventLoopGroup();

        //创建客户端
        Bootstrap bootstrap=new Bootstrap();

        try {
            //配置客户端信息
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //得到peiple
                    ChannelPipeline pipeline = ch.pipeline();
                    //加入String的编解码处理器
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new StringEncoder());

                    //加入自定义处理器
                    pipeline.addLast(new NettyGroupChatClientHandler());

                }
            });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            //得到通道
            Channel channel = channelFuture.channel();
            //客户端输入消息，创建一个扫描器
            Scanner scanner=new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg=scanner.nextLine();
                //将数据发送给服务端
                channel.writeAndFlush(msg+"\n");
            }
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new NettyGroupChatClient("127.0.0.1",7000).run();
    }
}
