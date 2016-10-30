package com.oneapm.netty.opendecode.serializable;


import com.oneapm.netty.opendecode.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

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
        ctx.flush();
    }

//    private SubscribeReq subReq(int i){
//        SubscribeReq req = new SubscribeReq();
//        req.setAddress("宜昌市三峡大坝");
//        req.setProductName("netty深入学习");
//        req.setPhoneNumber("185********");
//        req.setSubReqId(i);
//        req.setUserName("oneapm");
//        return req;
//    }

    /**
     * protobuf
     * @param i
     * @return
     */
    private SubscribeReqProto.SubscribeReq subReq(int i){
        SubscribeReqProto.SubscribeReq.Builder req = SubscribeReqProto.SubscribeReq.newBuilder();
        List<String> address = new ArrayList<>();
        address.add("NanJing");
        address.add("BeiJing");
        req.addAllAddress(address);
        req.setProductName("netty study");
        req.setSubReqId(i);
        req.setUserName("oneapm");
        return req.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response: [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
