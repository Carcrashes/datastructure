package com.dy.nettypro.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author dingyu
 * @description 群聊客户端
 * @date 2020/1/3
 */
public class GroupChatClient {

    private final String HOST="127.0.0.1";

    private final Integer PORT=6667;

    private Selector selector;

    private SocketChannel socketChannel;

    private String username;


    /**
     * 构造器 创建一个群聊客户端
     * @throws IOException
     */
    public GroupChatClient() throws IOException {
        //获取选择器
        selector = Selector.open();
        //创建通道
        socketChannel=SocketChannel.open(new InetSocketAddress(HOST, PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //注册到选择器上
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        username=socketChannel.getLocalAddress().toString().substring(1);

        System.out.println(username+"is ok");

    }


    /**
     * 发送消息
     * @param info
     */
    public void  sendInfo(String info){
        info=username +" 说："+info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readInfo(){

        try {
            int selectCount = selector.select();
            if (selectCount>0){//存在可用通道
                //获取有事件通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();//获取通道关联得key
                    if (key.isReadable()){
                        //可读事件
                        //得到通道
                       SocketChannel channel=(SocketChannel) key.channel();
                       //得到一个buffer
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        //读取通道数据到缓冲区
                        channel.read(byteBuffer);
                        //数据输出
                        String str=new String(byteBuffer.array());
                        System.out.println(new String(byteBuffer.array()));
                    }
                }
                iterator.remove(); //!!!!注意每次处理完成之后，就需要关联key移除
            }else{
                //System.out.println("没有可用通道");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        GroupChatClient client=new GroupChatClient();

            //启动一个线程每三秒读取消息
          new Thread(){
              @Override
              public void run() {
                  while (true){
                      client.readInfo();
                      try {
                          Thread.currentThread().sleep(3000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }

                  }
              }
          }.start();

            //发送消息
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                client.sendInfo(s);
            }

    }
}
