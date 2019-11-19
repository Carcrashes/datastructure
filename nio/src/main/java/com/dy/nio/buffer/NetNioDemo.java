package com.dy.nio.buffer;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author dingyu
 * @description 网络NIO
 *  使用NIO完成网络通信的三个核心
 *      1.通道(Channel)
 *
 *      2.缓冲区（Buffer）负责数据存取
 *
 *      3.选择器(Selector) ：是selectChannel 的多路复用器，用于监控SelectChannel的IO状况
 *
 * @date 2019/11/13
 */
public class NetNioDemo {

    //客户端（阻塞式）
    @Test
    public  void client() throws IOException {
        //创建网络通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        
        //创建本地通道
        FileChannel file = FileChannel.open(Paths.get("bw.txr"), StandardOpenOption.READ);

        //创建缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        //将文件读入缓冲区
        while (file.read(byteBuffer) !=-1){
            byteBuffer.flip(); //切换读模式
            socketChannel.write(byteBuffer); //将缓冲区写入网络通道中
            byteBuffer.clear();
        }

        //关闭通道
        file.close();
        socketChannel.close();
    }


    //服务端（阻塞式）
    @Test
    public void server() throws IOException {
        //创建服务端网络通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        //创建本地文件通道
        FileChannel open = FileChannel.open(Paths.get("3.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //绑定通道
        serverChannel.bind(new InetSocketAddress(8080));

        //获取客户端链接的通道
        SocketChannel accept = serverChannel.accept();

        //分配指定大小的缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        //从通道中读取数据，写入到本地文件通道
        while (accept.read(byteBuffer) !=-1){
            byteBuffer.flip();
            open.write(byteBuffer);
            byteBuffer.clear();
        }

        //关闭连接
        open.close();
        accept.close();
        serverChannel.close();

    }

}
