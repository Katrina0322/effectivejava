package com.oneapm.netty.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * used to
 * Created by tianjin on 10/24/16.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ((ByteBuf)msg).release();
//        ByteBuf in = (ByteBuf)msg;
//        try {
//
//                System.out.println(in.toString(CharsetUtil.UTF_8));
//                System.out.flush();
//
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
