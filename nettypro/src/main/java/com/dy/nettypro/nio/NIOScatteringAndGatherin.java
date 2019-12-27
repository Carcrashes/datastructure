package com.dy.nettypro.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author dingyu
 * @description 分散和聚合
 * Scattering：分散 ，将数据写入到多个buffer中，可以采用buffer数组方式。依次写入
 * Gathering: 聚合，从buffer数组读取数组，可以采用buffer数组，依次读入
 * @date 2019/12/26
 */
public class NIOScatteringAndGatherin {

    public static void main(String[] args) throws IOException {

        //获取通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress(7000);

        //端口绑定
        serverChannel.bind(inetSocketAddress);

        //创建buffer 数组
        ByteBuffer[] buffers=new ByteBuffer[2];
        buffers[0]=ByteBuffer.allocate(5);
        buffers[1]=ByteBuffer.allocate(3);

        //创建SocketChannel通道
        SocketChannel socketChannel = serverChannel.accept();
        int messagelength=8;
        while(true){
            int byteRead=0;
            while (byteRead<messagelength){
                long l = socketChannel.read(buffers);
                byteRead+=l;
                System.out.println("byteRead："+byteRead);
                Arrays.asList(buffers).stream().map(buffer->"position:"+buffer.position()+" limit:"+buffer.limit()).forEach(System.out::println);
            }

            //将所有缓冲区进行模式切换
            Arrays.asList(buffers).stream().forEach(buffer->buffer.flip());

            //将读取的数据显示到客户端
            int byteWrite=0;
            while (byteWrite < messagelength){
                long l = socketChannel.write(buffers);
                byteWrite+=l;
            }

            //清空缓冲区
            Arrays.asList(buffers).stream().forEach(buffer->buffer.clear());

            System.out.println(byteRead+"  "+byteWrite+"  "+messagelength);


        }


    }


}
