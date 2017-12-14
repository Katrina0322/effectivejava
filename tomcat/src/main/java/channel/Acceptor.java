package channel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * filename: Acceptor
 * Description:
 * Author: ubuntu
 * Date: 12/13/17 2:32 PM
 */
public class Acceptor {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Acceptor() {
    }

    public void bind() throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = getAddress() != null ? new InetSocketAddress(getAddress(), getPort()) : new InetSocketAddress(getPort());
        serverSocketChannel.bind(address, getAcceptCount());
        this.serverSocketChannel.configureBlocking(true);
    }


    public InetAddress getAddress() {
        return null;
    }

    public int getPort() {
        return 8080;
    }

    public int getAcceptCount() {
        return 100;
    }
}
