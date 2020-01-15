package com.dy.nettypro.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author dingyu
 * @description 关于Unpooled操作的介绍
 * @date 2020/1/13
 */
public class ByteBufDemo2 {


    public static void main(String[] args) {
        //创建ByteBuf对象
        ByteBuf buf= Unpooled.copiedBuffer("hello,world", Charset.forName("utf-8"));

        //使用相关的方法
        if (buf.hasArray()){

            byte[] array = buf.array(); //返回buf存储数据数组

            //解码 将array 转换对应编码的字符串
            System.out.println(new String(array, Charset.forName("utf-8")));

            System.out.println(buf.arrayOffset()); //0
            System.out.println(buf.readerIndex()); //0
            System.out.println(buf.writerIndex()); //12
            System.out.println(buf.capacity());  //36




        }

    }
}
