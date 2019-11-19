package com.dy.nio.buffer;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * @author dingyu
 * @description 非阻塞式NIIO
 * @date 2019/11/14
 */
public class NoBlockingNioDemo {

    @Test
    public void client() throws IOException {
        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8081));

        //设置非阻塞模式
        sChannel.configureBlocking(false);

        //定义缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        //发送数据
        byteBuffer.put(new Date().toString().getBytes());
        //byteBuffer.flip();
        sChannel.write(byteBuffer);
        byteBuffer.clear();

        //关闭通道
        sChannel.close();

    }

    @Test
    public void server() throws IOException {

        //设置通道
        ServerSocketChannel ssChannel=ServerSocketChannel.open();

        //设置非阻塞模式
        ssChannel.configureBlocking(false);

        //端口绑定
        ssChannel.bind(new InetSocketAddress(8081));

        //获取选择器
        Selector selector = Selector.open();

        //将通道注册到选择器  SelectionKey.OP_ACCEPT 表示选择器监控什么操作
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询获取选择器中已经准备好的事件select()
        if (selector.select()>0){
            //迭代所有已经监听的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                //获取准备"就绪事件"
                SelectionKey key = iterator.next();

                //判断事件属于什么类型
                if (key.isAcceptable()){
                    //接收事件
                    SocketChannel sChannel = ssChannel.accept();
                    //切换为非阻塞模式
                    sChannel.configureBlocking(false);
                    //将通道注册到选择器中
                    sChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) key.channel();

                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = sChannel.read(buf)) > 0 ){
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }/*else if(key.isConnectable()){
                    //连接事件
                }else if(key.isValid()){
                    //是否有效事件
                }else if (key.isWritable()){
                    //写事件
                }*/

                //15. 取消选择键 SelectionKey
                iterator.remove();
            }

        }
    }

}
