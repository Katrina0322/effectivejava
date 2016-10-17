package com.oneapm.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * used to
 * Created by spark on 10/14/16.
 */
public class MultiplexerTimeServer implements Runnable {
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器,绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //设为异步非阻塞
            serverSocketChannel.configureBlocking(false);
            //backlog设为1024
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void stop() {
        this.stop = true;
    }

    /**
     * 根据key的操作位获取网络事件的类型   TCP三次握手过程
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {

                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);

            }

            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();
                //通过ByteBuffer读取客户端的请求信息   开辟1K的缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server received order : " + body );
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readBytes < 0){
                    key.cancel();
                    sc.close();
                }else{

                }
            }
        }
    }

    /**
     * 通过ByteBuffer将应答消息异步发送给客户端
     * @param socketChannel
     * @param response
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel,String response) throws IOException {
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }

    @Override
    public void run() {
        //遍历selector,间隔为1s
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                //有就绪状态的Channel时,selector返回就绪状态的Channel的SelectionKey集合,通过对就绪状态的Channel集合进行迭代,进行异步读写操作
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
