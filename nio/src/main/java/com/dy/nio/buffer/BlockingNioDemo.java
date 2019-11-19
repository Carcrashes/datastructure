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
 * @description 阻塞式IO
 * @date 2019/11/13
 */
public class BlockingNioDemo {

    @Test
    public void client() throws IOException {
        SocketChannel sChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",8080));

        FileChannel fileChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.READ);

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        //向SocketChannel中写入缓冲区
        while (fileChannel.read(byteBuffer) !=-1){
            byteBuffer.flip();
            sChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        sChannel.shutdownOutput();//通知服务端数据传输完毕

        //接收服务端消息
        int len=0;
        while ((len = sChannel.read(byteBuffer)) !=-1){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0,len));
        }
        fileChannel.close();
        sChannel.close();
    }

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

        //反馈给客户端
        byteBuffer.put("数据接收成功".getBytes());
        byteBuffer.flip();//切换成读模式
        accept.write(byteBuffer);

        //关闭连接
        open.close();
        accept.close();
        serverChannel.close();
    }

}
