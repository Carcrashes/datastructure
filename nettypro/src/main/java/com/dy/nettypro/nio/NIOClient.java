package com.dy.nettypro.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author dingyu
 * @description 客户端
 * @date 2019/12/27
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {

        //创建通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供绑定服务端和端口
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",6666);

        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("正在连接服务端，客户端不会阻塞，可以进行其他工作");
            }
        }
        //连接成功
        String str="你好！连接成功";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());//将字节数组封装到缓冲区Buffer中
        socketChannel.write(buffer);
        System.in.read();


    }



}
