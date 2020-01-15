package com.dy.nettypro.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author dingyu
 * @description 解码器 方式2
 *
 * 继承ReplayingDecoder 实现decode方法 不需要判断字节
 * @date 2020/1/15
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Long> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        out.add(in.readLong());

    }
}
