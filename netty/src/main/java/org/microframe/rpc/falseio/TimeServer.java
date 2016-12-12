package org.microframe.rpc.falseio;

import org.microframe.rpc.bio.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * used to
 * Created by tianjin on 10/13/16.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket = null;
            TimeServerHandleExecutePool singleExecutor = new TimeServerHandleExecutePool(50, 10000);
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time sever close");
                server.close();
                server = null;
            }
        }
    }
}
