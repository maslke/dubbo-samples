package com.maslke.dubbo.samples.api.nio.reactor;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
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

    private void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = server.accept();
        Selector select = key.selector();
        // 每个通道绑定一个buffer，buffer不共享，给channel独占
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //  最后一个参数，可以附加任何需要的数据
        socketChannel.register(select, SelectionKey.OP_READ, byteBuffer);
    }


    private void readHandler(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        // byteBuffer分配的空间可能不够
        while (true) {
            int length = client.read(byteBuffer);
            if (length > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    client.write(byteBuffer);
                }
                byteBuffer.clear();

            } else if (length == 0) {
                break;
            } else {
                client.close();
            }
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
