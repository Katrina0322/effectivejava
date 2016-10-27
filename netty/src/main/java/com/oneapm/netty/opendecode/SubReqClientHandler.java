package com.oneapm.netty.opendecode;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by spark on 10/28/16.
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    public SubReqClientHandler() {
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for( int i = 0 ; i < 10 ; i++){
            ctx.write(subReq(i));
        }
    }

    private SubscribeReq subReq(int i){
        return null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + msg + " times received client:[" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
