package com.maslke.dubbo.samples.api.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerSockectChannelTest {
    public static void main(String[] args) throws Exception {
        String outFileName = "C:\\Users\\maslke\\Desktop\\ApplicationConfig_copy.java";
        File file = new File(outFileName);
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                System.out.println("create file failed");
            }
        }
        FileOutputStream fileInputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int size = selector.select();
            if (size == 0) break;
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // OA_ACCPET
                    System.out.println("connection created");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    fileChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
                iterator.remove();
            }
        }
        fileChannel.close();
        serverSocketChannel.close();
    }
}
