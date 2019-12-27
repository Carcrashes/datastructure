package com.dy.nettypro.nio;

import java.nio.ByteBuffer;

/**
 * @author dingyu
 * @description 设置只读缓冲区
 * @date 2019/12/20
 */
public class NIOReadOnlyBuffer {

    public static void main(String[] args) {

        //创建一个ByteBuffer
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        for (int i=0;i<64;i++){
            byteBuffer.put((byte) i);
        }

        //切换读取模式
        byteBuffer.flip();
        
        //得到一个只读的ByteBuffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());

        //读取
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte)1); //只读byteBuffer会抛出java.nio.ReadOnlyBufferException异常

    }
}
