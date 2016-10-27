package com.oneapm.netty.opendecode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * used to
 * Created by tianjin on 10/27/16.
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if("TianJin".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscribe req:[" + req.toString() + "]");
            ctx.writeAndFlush(msg);
        }
    }

    private SubscribeReq resp(int subReqId){
        SubscribeReq resp = new SubscribeReq();
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
