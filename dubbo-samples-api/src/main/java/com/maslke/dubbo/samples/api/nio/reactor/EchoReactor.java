package com.maslke.dubbo.samples.api.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class EchoReactor implements Runnable {

    public static void main(String[] args) throws IOException {
        new Thread(new EchoReactor()).start();
    }

    private static final String BIND_IP = "127.0.0.1";
    private static final int BIND_PORT = 8989;

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public EchoReactor() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(BIND_IP, BIND_PORT));
            selector = Selector.open();
            SelectionKey sk =  serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new AcceptHandler());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    dispatch(key);
                    keys.remove();
                }
            } catch (IOException ex) {
                System.out.println("ex.getMessage() = " + ex.getMessage());
            }
        }
    }

    private void dispatch(SelectionKey key) {
        AcceptHandler handler = (AcceptHandler) key.attachment();
        if (handler != null) {
            handler.run();
        } else {
            System.out.println("handler is null");
        }
    }

    class AcceptHandler implements Runnable {
        @Override
        public void run() {
            try {
                // 创建新的SocketChannel
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null) {
                    System.out.println("EchoHandler processing");
                    (new EchoHandler(selector, channel)).run();
                }

            } catch (IOException ex) {
                System.out.println("ex.getMessage() = " + ex.getMessage());
            }
        }
    }
}


