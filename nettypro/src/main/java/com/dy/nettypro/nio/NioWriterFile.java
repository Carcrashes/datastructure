package com.dy.nettypro.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dingyu
 * @description nio复制文件
 * @date 2019/12/19
 */
public class NioWriterFile {

    public static void main(String[] args) throws IOException {
         String str="你好 nio复制文件";
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\nio.txt");
        //获取通道
        FileChannel channel = fileOutputStream.getChannel();

        //创建缓冲空间
        ByteBuffer byteBuffer=ByteBuffer.allocate(50);
        byteBuffer.put(str.getBytes());

        //切换为读模式
        byteBuffer.flip();

        //写文件
        channel.write(byteBuffer);

        //关闭通道关闭流
        channel.close();
        fileOutputStream.close();
        
    }
}
