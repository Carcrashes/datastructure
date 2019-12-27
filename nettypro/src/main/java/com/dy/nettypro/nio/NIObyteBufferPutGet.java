package com.dy.nettypro.nio;

import java.nio.ByteBuffer;

/**
 * @author dingyu
 * @description 存取值注意事项
 * @date 2019/12/19
 */
public class NIObyteBufferPutGet {

    public static void main(String[] args) {
        /**
         *  注意事项：
         *  放入什么数据类型，get就应该相应顺序的按照对应数据类型取出 否则可能抛出异常
         */
        //创建一个ByteBuffer
        ByteBuffer byteBuffer=ByteBuffer.allocate(64);

        //存值
        byteBuffer.putInt(100);
        byteBuffer.putLong(9);
        byteBuffer.putChar('尚');
        byteBuffer.putShort((short)4);

        //切换读模式
        byteBuffer.flip();

        //取出
        System.out.println( byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());


    }
}
