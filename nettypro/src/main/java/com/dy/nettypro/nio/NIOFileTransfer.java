package com.dy.nettypro.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author dingyu
 * @description 文件复制方法2
 * @date 2019/12/19
 */
public class NIOFileTransfer {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream=new FileInputStream("d:\\nio.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\nioTransferFrom.txt");

        //获取通道
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outchannel= fileOutputStream.getChannel();

        //复制文件
        //方法一：transferFrom
        //outchannel.transferFrom(inChannel,0,inChannel.size());

        //方法二：transferTo
        inChannel.transferTo(0,inChannel.size(),outchannel);

        //关闭通道和流
        outchannel.close();
        fileOutputStream.close();
        inChannel.close();
        fileInputStream.close();




    }
}
