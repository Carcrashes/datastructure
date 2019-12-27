package com.dy.nettypro.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dingyu
 * @description 读取文件
 * @date 2019/12/19
 */
public class NIOReadFile {

    public static void main(String[] args) throws IOException {

        File file=new File("d:\\nio.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        //获取通道
        FileChannel channel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate((int)file.length());
        //将数据写入到缓冲区
        channel.read(byteBuffer);
        //打印到控制台
        System.out.println(new String(byteBuffer.array()));

    }
}
