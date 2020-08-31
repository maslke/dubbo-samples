package com.maslke.dubbo.samples.api.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiEchoReactor {

    public static void main(String[] args) throws Exception {
        MultiEchoReactor server = new MultiEchoReactor();
        server.startService();
    }

    private ServerSocketChannel serverSocketChannel;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    Selector[] selectors = new Selector[2];

    //
    SubReactor[] subReactors = null;

    public MultiEchoReactor() throws Exception {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8989));
        serverSocketChannel.configureBlocking(false);
        SelectionKey sk = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptHandler());
        SubReactor subReactor = new SubReactor(selectors[0]);
        SubReactor subReactor1 = new SubReactor(selectors[1]);
        subReactors = new SubReactor[] { subReactor, subReactor1 };

    }

    private void startService() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    class SubReactor implements Runnable {
        final Selector selector;

        SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        dispatch(key);
                        keys.remove();
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        private void dispatch(SelectionKey key) {
            AcceptHandler handler = (AcceptHandler) key.attachment();
            if (handler != null) {
                handler.run();
            }
        }


    }

    class AcceptHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null) {
                     new MultiThreadEchoHandler(selectors[atomicInteger.get()], channel).run();
                   // new EchoHandler(selectors[atomicInteger.get()], channel).run();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            // ... 莫名其妙
            if (atomicInteger.incrementAndGet() == selectors.length) {
                atomicInteger.set(0);
            }
        }
    }

}
