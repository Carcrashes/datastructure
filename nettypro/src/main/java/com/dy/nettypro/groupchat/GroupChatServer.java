package com.dy.nettypro.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dingyu
 * @description 群聊系统服务端
 * @date 2019/12/27
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private InetSocketAddress inetSocketAddress;

    public GroupChatServer() {
        try {
            //创建通道信息
            serverSocketChannel= ServerSocketChannel.open();
            //创建选择器
            selector=Selector.open();
            //绑定端口
            inetSocketAddress=new InetSocketAddress(6667);
            serverSocketChannel.bind(inetSocketAddress);
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            //注册到选择器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 监听消息
     */
    public void listen(){
        try {
            while (true){
                //获取发生事件的通道
                int count = selector.select();
                if (count>0){
                    //表示通道有事件发生
                    //遍历得到SelectionKey的集合
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    if (keyIterator.hasNext()){
                        //取出SelectionKey
                        SelectionKey key = keyIterator.next();

                        //监听accept
                        if (key.isAcceptable()){
                            //客户端连接
                            SocketChannel client = serverSocketChannel.accept();
                            //设置非阻塞
                            client.configureBlocking(false);
                            //注册到选择器
                            client.register(selector,SelectionKey.OP_READ);
                            //提示上线
                            System.out.println(client.getRemoteAddress()+" 上线");
                        }
                        //读取数据
                        if (key.isReadable()){
                            //读取消息
                            readData(key);
                        }
                        keyIterator.remove();
                    }
                }else {
                    System.out.println("等待连接。。。。");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    /**
     * 读取通道消息
     * @param key
     */
    public void readData(SelectionKey key){
        SocketChannel channel = null;
        try {
            //获取跟key相关的通道
            channel=(SocketChannel) key.channel();
            //创建buffer
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            //从通道中读取数据
            int count = channel.read(byteBuffer);
            //根据通道读取数据值处理
            if (count>0){
                //缓冲区数据转换为字符串
                String str=new String(byteBuffer.array());
                //输出消息
                System.out.println("from client："+str);
                //转发消息 除自己之外的客户端
                transferMessgae(str,channel);
            }
        }catch (Exception e){
            try {
                System.out.println(channel.getRemoteAddress()+"离线了");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }


    /**
     * 处理转发消息
     * @param msg
     * @param channel
     * @throws IOException
     */
    public void transferMessgae(String msg,SocketChannel channel) throws IOException {
        //服务器转发消息
        System.out.println("服务器开始转发消息");
        //获取所有通道
        for (SelectionKey key:selector.keys()){
            //根据key获取对应通道
           SocketChannel targetChanne=(SocketChannel) key.channel();
           //排除自己
            if (targetChanne instanceof SocketChannel && targetChanne !=channel){
                //将msg存储到buffer中
                ByteBuffer mesbuffer = ByteBuffer.wrap(msg.getBytes());
                targetChanne.write(mesbuffer);
            }

        }
    }
}
