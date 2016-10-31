package com.oneapm.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * used to
 * Created by tianjin on 10/14/16.
 */
public class AsyncTimeServerHandler implements Runnable {
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;
    private int port;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open(); // 创建一个异步服务端通道。
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));// bind 一个监听端口
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        latch = new CountDownLatch(1); // 在完成一组正在执行的操作之前，允许当前的线程一直阻塞。
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());// 处理接受消息的通知。
    }
}
