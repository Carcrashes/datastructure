package com.dy.nettypro.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author dingyu
 * @description 零拷贝客户端
 * @date 2020/1/6
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",6668));
        String filepath="D:/erp.zip";

        FileChannel fileChannel=new FileInputStream(filepath).getChannel();

        //准备发送 记录时间
        long startTime = System.currentTimeMillis();

        //linux 使用transferTo可以一次性发送
        //windows 系统中调用最大只能发送8M,大文件传输时候，需要对文件进行分割
        fileChannel.transferTo(0,fileChannel.size(),socketChannel);

        long endTime = System.currentTimeMillis();

        System.out.println("发送成功，消耗时间为:"+(endTime-startTime)+"传输大小为:"+fileChannel.size());

        //关闭通道:
        fileChannel.close();

    }

}
