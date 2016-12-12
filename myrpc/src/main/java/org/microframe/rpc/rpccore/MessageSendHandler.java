package org.microframe.rpc.rpccore;

import org.microframe.rpc.rpccore.MessageCallBack;
import org.microframe.rpc.rpcmodel.MessageResponse;
import org.microframe.rpc.rpcmodel.MessageRequest;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class MessageSendHandler extends ChannelInboundHandlerAdapter {
    private ConcurrentHashMap<String,MessageCallBack> mapCallBack = new ConcurrentHashMap<>();

    private volatile Channel channel;

    private SocketAddress socketAddress;

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.socketAddress = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageResponse response = (MessageResponse) msg;
        String messageId = response.getMessageId();
        MessageCallBack callBack = mapCallBack.get(messageId);
        if(callBack != null){
            mapCallBack.remove(messageId);
            callBack.over(response);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public MessageCallBack sendRequest(MessageRequest request){
        MessageCallBack callBack = new MessageCallBack(request);
        mapCallBack.put(request.getMessageId(), callBack);
        channel.writeAndFlush(request);
        return callBack;
    }

}
