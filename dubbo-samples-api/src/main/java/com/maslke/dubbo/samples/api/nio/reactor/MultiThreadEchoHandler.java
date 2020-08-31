package com.maslke.dubbo.samples.api.nio.reactor;

import io.netty.buffer.ByteBufUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoHandler implements Runnable {

    private final Selector selector;
    private final SocketChannel channel;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private SelectionKey sk;
    static final int RECEIVING = 0;
    static final int SENDING = 1;
    int state = RECEIVING;

    static ExecutorService pool = Executors.newFixedThreadPool(4);

    public MultiThreadEchoHandler(Selector selector, SocketChannel channel) {
        this.selector = selector;
        this.channel = channel;
        try {
            this.channel.configureBlocking(false);
            sk = this.channel.register(selector, 0);
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void run() {
        pool.execute(new AsyncTask());
    }

    class AsyncTask implements Runnable {
        @Override
        public void run() {
            asyncRun();
        }
    }

    private  void asyncRun() {
        synchronized (this) {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        if (key.isReadable()) {
                            int length = 0;
                            while ( (length = this.channel.read(byteBuffer)) > 0) {
                                System.out.println(new String(byteBuffer.array(), 0, length));
                            }
                            byteBuffer.flip();
                            sk.interestOps(SelectionKey.OP_WRITE);

                        }
                        if (key.isWritable()) {
                            this.channel.write(byteBuffer);
                            byteBuffer.clear();
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                       // keys.remove();
                    }
                }

            } catch (IOException ex) {
                System.out.println("ex.getMessage() = " + ex.getMessage());
            }
        }


    }

}
