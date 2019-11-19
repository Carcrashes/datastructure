package com.dy.nio.buffer;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author dingyu
 * @description  管道 用于线程之间通信传输 sink 和source 可以放置不同线程中。
 *   Pipe.SinkChannel sink = pipe.sink();
 *   Pipe.SourceChannel source = pipe.source();
 *
 * @date 2019/11/15
 */
public class PeiDemo {

    @Test
    public void test() throws IOException {
        //获取管道
        Pipe pipe = Pipe.open();

        //将缓冲区数据写入管道中
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        //单向通道
        Pipe.SinkChannel sink = pipe.sink();
        byteBuffer.put("向单向管道中写入数据".getBytes());
        byteBuffer.flip();
        sink.write(byteBuffer);

        //读取缓冲区数据
        Pipe.SourceChannel source = pipe.source();
        byteBuffer.flip();
        int read = source.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),0,read));

        source.close();
        sink.close();




    }
}
