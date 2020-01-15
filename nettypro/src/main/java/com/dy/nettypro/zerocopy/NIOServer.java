package com.dy.nettypro.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author dingyu
 * @description 零拷贝服务端
 *
 * @date 2020/1/6
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",6668);
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //读取通道数据
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readcount=0;
            if (-1 !=readcount){
                readcount=socketChannel.read(byteBuffer);
            }
            byteBuffer.rewind();
        }

    }
}
