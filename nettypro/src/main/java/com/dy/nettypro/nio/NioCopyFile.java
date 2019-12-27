package com.dy.nettypro.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dingyu
 * @description 文件复制
 * @date 2019/12/19
 */
public class NioCopyFile {

    public static void main(String[] args) throws IOException {

        File file=new File("d:\\nio.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\niocopy.txt");
        
        //获取通道
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();



        //创建缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate((int)file.length());

        //安全方式
        while(true){
            //while重复读取，因为缓冲区分配的大小没有文件大。需要进行重复读取
            byteBuffer.clear();//这里需要将缓冲区进行重置，因为
            int read = inputChannel.read(byteBuffer);
            //切换到读模式
            byteBuffer.flip();
            //文件写入
            outputChannel.write(byteBuffer);
            //防止死循环
            if (read==-1){
                break;
            }
        }


        //关闭
        inputChannel.close();
        fileInputStream.close();
        outputChannel.close();
        fileOutputStream.close();



    }
}
