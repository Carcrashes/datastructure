package com.dy.nettypro.nio;

import java.nio.IntBuffer;

/**
 * @author dingyu
 * @description 缓冲区
 * @date 2019/11/22
 */
public class BasicBuffer {

    public static void main(String[] args) {

        //定义缓冲区
        IntBuffer buffer=IntBuffer.allocate(5);
        //写入数据
        for (int i=0;i<buffer.capacity();i++){
            buffer.put(i*2);
        }
        //切换为读模式
        buffer.flip();

       //输出缓冲区数据
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
