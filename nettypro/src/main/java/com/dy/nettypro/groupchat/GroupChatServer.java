package com.dy.nettypro.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author dingyu
 * @description 群聊系统服务端
 * @date 2019/12/27
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final Integer  PORT=6667;

    public GroupChatServer() {
        try {
            //创建选择器
            selector=Selector.open();
            //创建通道信息
            listenChannel= ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞
            listenChannel.configureBlocking(false);
            //注册到选择器
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 监听消息
     */
    public void listen(){
        System.out.println("监听线程: " + Thread.currentThread().getName());
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
                            SocketChannel client = listenChannel.accept();
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
                   // System.out.println("等待连接。。。。");
                   // continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //发生异常
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
           Channel targetChannel=key.channel();
           //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel !=channel){
                //转型
                SocketChannel dest = (SocketChannel)targetChannel;
                //将msg存储到buffer中
                ByteBuffer mesbuffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(mesbuffer);
            }

        }
    }

    public static void main(String[] args) {
            GroupChatServer server=new GroupChatServer();
            server.listen();

    }
}
