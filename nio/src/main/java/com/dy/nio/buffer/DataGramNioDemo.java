package com.dy.nio.buffer;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author dingyu
 * @description DataGramChannel 通道
 *  数据传输格式是UDP
 * @date 2019/11/15
 */
public class DataGramNioDemo {

    @Test
    public void client() throws IOException {
        //创建通道
        DatagramChannel da = DatagramChannel.open();
        //设置非阻塞模式
        da.configureBlocking(false);
        //分配缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put("你好！".getBytes());
        //写数据进入缓冲区
        byteBuffer.flip();
        da.send(byteBuffer,new InetSocketAddress("127.0.0.1",9098));  //发送数据
        byteBuffer.clear();
        //关闭通道
        da.close();
    }


    @Test
    public void server() throws IOException {
        //创建通道
        DatagramChannel dChannel=DatagramChannel.open();
        //设置为非阻塞模式
        dChannel.configureBlocking(false);
        //绑定
        dChannel.bind(new InetSocketAddress(9098));
        //获取选择器
        Selector selector=Selector.open();
        //注册选择器
        dChannel.register(selector, SelectionKey.OP_READ);
        //轮询选择器，是否存在准备就绪的事件
        while (selector.select()>0){
            //迭代选择器
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isReadable()){
                    //读取模式
                    ByteBuffer buffer=ByteBuffer.allocate(1024);
                    //获取通道
                    DatagramChannel datagramChannel=(DatagramChannel)next.channel();
                    //接收数据 写入缓冲区
                    datagramChannel.receive(buffer);
                    buffer.clear();
                    System.out.println(new String(buffer.array(),0,buffer.limit()));
                }
            }
            iterator.remove();
        }
    }
}
