package com.oneapm.nio;

import java.io.IOException;

/**
 * used to
 * Created by tianjin on 10/14/16.
 */
public class FirstNio {
    public static void main(String[] args) throws IOException {
//        //打开ServerSocketChannel,用于监听客户端的链接
//        ServerSocketChannel acceptor = ServerSocketChannel.open();
//
//        //绑定监听端口,设置连接为非阻塞模式
//        int port = 8080;
//        acceptor.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"),port));
//        acceptor.configureBlocking(false);
//
//        //创建Reactor线程,创建多路复用器并启动线程
//        Selector selector = Selector.open();
//        new Thread(new ReactorTask()).start();
//
//        //将ServerSocketChannel注册到Reactor线程的多路复用器Selcetor上
//        SelectionKey key = acceptor.register(selector,SelectionKey.OP_ACCEPT,ioHandler);
//
//        //轮询
//        int num = selector.select();
//        Set<SelectionKey> selectedKeys = selector.selectedKeys();
//        Iterator<SelectionKey> keys = selectedKeys.iterator();
//
//        while(keys.hasNext()){
//            SelectionKey key = keys.next();
//
//        }
//
//
//        //新的客户端接入
//        SocketChannel sc = serverChannel.accept();
//
//
//        //设置位非阻塞模式
//        sc.configureBlocking(false);
//        sc.socket().setReuseAddress(true);
//
//        //将新接入的客户端连接注册到Reactor
//        SelectionKey key = sc.register(selector,SelectionKey.OP_READ);
//
//        //异步读取客户端消息到缓冲区
//        int number = sc.read(receivedBuffer);
//
//        //最后读取bytebuffer    略
//        while(buffer.hasRemain){
//
//        }


//        //打开SocketChannel,绑定客户端本地地址
//        SocketChannel clientChannel = SocketChannel.open();
//
//        //设置SocketChannel为非阻塞模式
//        clientChannel.configureBlocking(false);
//        clientChannel.socket().setReuseAddress(true);
//        clientChannel.socket().setReceiveBufferSize(BUFFER_SIZE);
//        clientChannel.socket().setSendBufferSize(BUFFER_SIZE);
//
//        //异步连接服务端
//        boolean connected = clientChannel.connect(new InetSocketAddress("ip",port));
//
//        //判断  注册
//        if(connected){
//            clientChannel.register(selector,SelectionKey.OP_READ,ioHandler);
//        }else{
//            clientChannel.register(selector,SelectionKey.OP_CONNECT,ioHandler);
//        }
//
//        //创建Reactor线程,创建多路复用器并启动线程
//        Selector selector = Selector.open();
//        new Thread(new ReactorTask()).start();
//
//        //轮询
//        int num = selector.select();
//        Set<SelectionKey> selectedKeys = selector.selectedKeys();
//        Iterator<SelectionKey> keys = selectedKeys.iterator();
//
//        while(keys.hasNext()){
//            SelectionKey key = keys.next();
//
//        }
//
//        //接受connect事件进行处理
//        if(key.isConnectable()){
//            //handleConnect
//        }
//        //连接成功,注册读事件
//        if(clientChannel.finishConnect()) registerRead();
//
//        //异步读和消息读取
//        int number = sc.read(receivedBuffer);
//        while(buffer.hasRemain){
//
//        }
    }
}
