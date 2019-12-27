package com.dy.nettypro.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dingyu
 * @description 创建直接缓冲区
 *  可以让文件直接在内存（堆外内存）修改，操作系统不需要从jvm内存复制到内核内存中。
 * @date 2019/12/20
 */
public class NIOMapByteBuffer {

    public static void main(String[] args) throws IOException {
        //创建直接缓冲区
        RandomAccessFile accessFile=new RandomAccessFile("d:\\nio.txt","rw");
        //获取对应通道
        FileChannel channel = accessFile.getChannel();

        /**
         * channel.map() 方法，直接在磁盘创建文件
         * 参数1: 方法模式 READ_WRITE 读写模式 READ_ONLY 只读 PRIVATE
         * 参数2： 可以直接修改的起始位置
         * 参数3:  映射到内存的大小，即将文件多少个字节映射到内存中，可以直接修改的范围既是0-5
         * 5是指字节大小，而不是索引位置为5
         * mappedByteBuffer 实际对象类型是DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer=channel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(1,(byte)'9');
        mappedByteBuffer.put(2,(byte)'Y');
        mappedByteBuffer.put(5,(byte)'A'); //此时会抛出越界异常

        //关闭
        channel.close();
        accessFile.close();

    }

}
