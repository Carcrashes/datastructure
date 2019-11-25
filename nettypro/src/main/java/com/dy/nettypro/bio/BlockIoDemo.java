package com.dy.nettypro.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dingyu
 * @description BIO 传统IO流介绍
 * 一个请求需要一个线程处理，当资源未准备好时候，线程会阻塞
 * 该案例处理使用线程池，处理多个客户端
 * @date 2019/11/22
 */
public class BlockIoDemo {

    public static void main(String[] args) throws IOException {

        //创建线程池
        ExecutorService pool= Executors.newCachedThreadPool();
        //创建服务端
        ServerSocket server=new ServerSocket(6666);
        System.out.println("服务端启动");
        //监听服务端接口
        while(true){

            final Socket socket = server.accept();
            System.out.println("客户端连接成功。。。。");
            //创建子线程处理客户端请求
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }


    public static void handler(Socket socket){
        try {
            //创建字节数组接收数据
            byte[] bytes = new byte[1024];
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            while(true){
                System.out.println("等待客户端发送数据");
                int read = inputStream.read(bytes);//将数据读入到字节数组中
                if (read !=-1){
                    System.out.println("线程信息:"+Thread.currentThread().getName()+"\t"+Thread.currentThread().getId());
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        

    }
}
