package com.maslke.dubbo.samples.api.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;


/**
 * create by 尼恩 @ 疯狂创客圈
 **/
public class EchoClient {

    public void start() throws IOException {

        InetSocketAddress address =
                new InetSocketAddress("127.0.0.1", 8989);

        SocketChannel socketChannel = SocketChannel.open(address);
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()) {

        }
        System.out.println("connection connected");

        //启动接受线程
        Processer processer = new Processer(socketChannel);
        new Thread(processer).start();

    }

    static class Processer implements Runnable {
        final Selector selector;
        final SocketChannel channel;

        Processer(SocketChannel channel) throws IOException {
            //Reactor初始化
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector,
                    SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        if (sk.isWritable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            Scanner scanner = new Scanner(System.in);
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                String next = scanner.next();
                                buffer.put((new Date().toString() + " >>" + next).getBytes());
                                buffer.flip();
                                socketChannel.write(buffer);
                                buffer.clear();
                            }
                        }
                        if (sk.isReadable()) {
                            // 若选择键的IO事件是“可读”事件,读取数据
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            //读取数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int length = 0;
                            while ((length = socketChannel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                System.out.println("server echo:" + new String(byteBuffer.array(), 0, length));
                                byteBuffer.clear();
                            }

                        }
                        it.remove();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().start();
    }
}


