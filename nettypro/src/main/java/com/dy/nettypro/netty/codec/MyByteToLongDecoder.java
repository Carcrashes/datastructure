package com.dy.nettypro.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author dingyu
 * @description 解码器
 * @date 2020/1/15
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * decode方法会根据接收的数据，会被多次调用，直到没有没有新的数据被添加到list中位置，或者ByteBuf中没用数据为止。如果list 不为空，会将数据传输给下一个处理器处理
     *
     * 解码器
     * @param ctx  上下文
     * @param in  入站的buf
     * @param out 解码后将数据传输给下一个进行处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyByteToLongDecoder decode 方法被调用");
        if (in.readableBytes()>8){
            out.add(in.readLong());
        }

    }
}
