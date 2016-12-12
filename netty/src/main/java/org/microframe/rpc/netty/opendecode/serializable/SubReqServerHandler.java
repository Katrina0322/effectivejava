package org.microframe.rpc.netty.opendecode.serializable;

import org.microframe.rpc.netty.opendecode.protobuf.SubscribeReqProto;
import org.microframe.rpc.netty.opendecode.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * used to
 * Created by tianjin on 10/27/16.
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SubscribeReq req = (SubscribeReq) msg;
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if ("oneapm".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req:[" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
    }

    /**
     * java serializable
     * @param subReqId
     * @return
     */
//    private SubscribeResp resp(int subReqId) {
//        SubscribeResp resp = new SubscribeResp();
//        resp.setSubReqId(subReqId);
//        resp.setRespCode(0);
//        resp.setDesc("Welcome to China");
//        return resp;
//    }

    /**
     * protobuf
     *
     * @param subReqId
     * @return
     */
    private SubscribeRespProto.SubscribeResp resp(int subReqId) {
        SubscribeRespProto.SubscribeResp.Builder resp = SubscribeRespProto.SubscribeResp.newBuilder();
        resp.setSubReqId(subReqId);
        resp.setRespCode(0);
        resp.setDesc("Welcome to China");
        return resp.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
