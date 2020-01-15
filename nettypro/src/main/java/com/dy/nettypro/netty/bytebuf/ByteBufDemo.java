package com.dy.nettypro.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author dingyu
 * @description netty 传输数据类型
 *  底层封装的是一个byte[] 数组 在netty中不需要像nio进行读取切换
 *  底层维护了三个变量 一个readerIndex ，writerIndex 和 capacity
 *      readerindex：表示可读的下个索引位置  0-readerindex 已读区域  readerindex -writerindex 可读区域
 *      writerindex：表示可写的下个索引位置  writerindex - capacity ：可写区域
 *      capacity: 表示ByteBuf 容量
 * @date 2020/1/13
 */
public class ByteBufDemo {

    public static void main(String[] args) {
        ByteBuf buf=Unpooled.buffer(10); //分配为十个空间大小的缓冲区

        for (int i=0;i<10;i++){
            buf.writeByte(i);
        }

        System.out.println("buf :"+"cap--"+buf.capacity()+" read--"+buf.readerIndex()+" writer--"+buf.writerIndex());

        for (int i=0;i<buf.capacity();i++){
            System.out.println(buf.getByte(i)); //获取buf下的i索引的值，这个不会引起readerIndex 变量的变化
        }
        System.out.println("readerIndex:"+buf.readerIndex());

        for (int i=0;i<buf.capacity();i++){
            System.out.println(buf.readByte()); //按照readerIndex获取buf值，每读取一次readerIndex+1；
        }
        System.out.println("readerIndex:"+buf.readerIndex());

    }
}
