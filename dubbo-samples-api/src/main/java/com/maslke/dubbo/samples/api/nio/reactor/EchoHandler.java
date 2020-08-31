package com.maslke.dubbo.samples.api.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

// 数据Echo存在问题
public class EchoHandler implements Runnable {
    private Selector selector;
    private SocketChannel socketChannel;
    private SelectionKey sk;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public EchoHandler(Selector selector, SocketChannel channel) {
        try {
            this.selector = selector;
            this.socketChannel = channel;
            this.socketChannel.configureBlocking(false);
            sk = this.socketChannel.register(this.selector, SelectionKey.OP_READ);
            this.selector.wakeup();
        } catch (IOException ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    if (key.isReadable()) {
                        int length = 0;
                        while ( (length = this.socketChannel.read(byteBuffer)) > 0) {
                            System.out.println(new String(byteBuffer.array(), 0, length));
                        }
                        byteBuffer.flip();
                        sk.interestOps(SelectionKey.OP_WRITE);

                    }
                    if (key.isWritable()) {
                        this.socketChannel.write(byteBuffer);
                        byteBuffer.clear();
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    keys.remove();
                }
            }

        } catch (IOException ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }
    }
}
