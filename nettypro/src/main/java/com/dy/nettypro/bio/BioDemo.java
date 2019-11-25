package com.dy.nettypro.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dingyu
 * @description 阻塞式IO
 * 1.一个请求就是就分配一个线程处理。
 * 2.线程可能会形成阻塞
 * @date 2019/11/21
 */
public class BioDemo {


    public static void main(String[] args) throws IOException {
        //线程池机制（可以同时处理多个客户端）
        //思路
        //1.创建线程池 2.如果有客户端连接，则使用handle方法处理与之通讯
        ExecutorService pool= Executors.newCachedThreadPool();//创建一个缓存线程池
        ServerSocket server=new ServerSocket(6666);//创建监听的服务端
        System.out.println("服务端启动");
        while (true){
            //监听客户端连接
            System.out.println("线程信息:"+Thread.currentThread().getId()+" \t"+Thread.currentThread().getName());
            System.out.println("等待客户端连接");//当服务启动之后，没有客户端连接，则主线程阻塞这里等待线程连接
            final Socket socket=server.accept();
            System.out.println("客户端连接~~~");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void  handler(Socket socket){
        System.out.println("线程信息:"+Thread.currentThread().getId()+" \t"+Thread.currentThread().getName());
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();//获取输入流
            while (true) {
                System.out.println("等待读取数据");//当客户端连接之后，等待客户端发生数据，没有数据处理，则线程阻塞这这里
                int read = inputStream.read(bytes);//将数据写入到bytes数组中
                System.out.println("线程信息:"+Thread.currentThread().getId()+" \t"+Thread.currentThread().getName());
                if (read !=-1){
                    //输出客户端信息
                    System.out.println(new String(bytes,0,read));
                }else {
                    //读取完毕
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("关闭客户端连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
