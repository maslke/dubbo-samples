package com.maslke.dubbo.samples.api.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiplexingThread {
    private Selector bossSelecter;
    private Selector workerSelector1;
    private Selector workerSelector2;
    private ServerSocketChannel server;
    private int port = 9090;

    public void initSever() {
        try {
            bossSelecter = Selector.open();
            workerSelector1 = Selector.open();
            workerSelector2 = Selector.open();
            server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            server.register(bossSelecter, SelectionKey.OP_ACCEPT, byteBuffer);
            startThread();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void startThread() throws InterruptedException {
        NioThread bossThread = new NioThread(bossSelecter, 2);
        NioThread workerThread1 = new NioThread(workerSelector1);
        NioThread workerThread2 = new NioThread(workerSelector2);
        new Thread(bossThread).start();
        new Thread(workerThread1).start();
        new Thread(workerThread2).start();
        new CountDownLatch(1).await();
    }

    public static void main(String[] args) {
        new MultiplexingThread().initSever();
    }

}

class NioThread implements Runnable {

    Selector selector;
    static LinkedBlockingDeque<SocketChannel>[] queue;
    int index = -1;
    static AtomicInteger next = new AtomicInteger(0);
    int selectors;

    NioThread(Selector selector, int selectors) {
        this.selector = selector;
        this.selectors = selectors;
        queue = new LinkedBlockingDeque[2];
    }

    NioThread(Selector selector) {
        this.selector = selector;
        index = next.incrementAndGet() % queue.length;
        queue[index] = new LinkedBlockingDeque<>();
    }

    private void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            Selector selector = key.selector();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            System.out.println(".......................................");
            System.out.println("client is:" + client.getRemoteAddress());
            System.out.println(".......................................");
            client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void readHandler(SelectionKey key) {
        try {
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
            while (true) {
                int length = client.read(byteBuffer);
                if (length == 0) {
                    break;
                }
                else if (length == -1) {
                    client.close();
                    break;
                }
                else {
                    // System.out.println(new String(byteBuffer.array(), 0, length));
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        client.write(byteBuffer);
                    }
                    byteBuffer.clear();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (selector.select(10) > 0) {
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        }
                        else if (key.isReadable()) {
                            readHandler(key);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
