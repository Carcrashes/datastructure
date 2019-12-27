package com.dy.nettypro.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dingyu
 * @description 服务端
 * @date 2019/12/27
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        //创建服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress(6666);
        //绑定端口
        serverSocketChannel.bind(inetSocketAddress);
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //创建选择器
        Selector selector = Selector.open();
        //将服务端注册到选择器上  register 参数1：选择器  参数2：事件操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while(true){
            //select()方法监听有事件的通道 这是一个阻塞式方法，如果没有参数传入，然后会一直阻塞，等待有连接才返回
            //如果传入参数，超时立马返回。
            if (selector.select(1000)==0){
                System.out.println("服务器等待1秒。无客户端连接");
                continue;
            }
            //如果返回大于0，表示获取到了关注的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历，使用迭代器进行
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            //遍历
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //判断连接事件
                if (key.isAcceptable()) {
                    //OP_ACCEPT 事件，新客户端连接 创建一个新的通道
                    SocketChannel channel = serverSocketChannel.accept();
                    System.out.println(channel.hashCode() + "客户端连接");
                    //设置通道为非阻塞
                    channel.configureBlocking(false);
                    //注册到选择器上，同时分配关联一个Buffer
                    channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    //OP_READ 事件，读取通道数据，通过key 方向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    //读取数据
                    channel.read(byteBuffer);
                    System.out.println("from clien message:" + new String(byteBuffer.array()));
                }

                //处理完成之后，手动移除当前的selectionKey，防止重复操作
                iterator.remove();
            }
        }
    }
}
