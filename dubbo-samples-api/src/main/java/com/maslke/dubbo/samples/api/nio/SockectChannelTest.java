package com.maslke.dubbo.samples.api.nio;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class SockectChannelTest {
    public static void main(String[] args) throws Exception {
        String fileName = "C:\\Users\\DELL\\Desktop\\ApplicationConfig.json";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileChannel fileChannel = fileInputStream.getChannel();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 8888));
        while (!socketChannel.finishConnect()) {
            System.out.println("connectting");
        }
        System.out.println("connected");
        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
        ByteBuffer nameByteBuffer = ByteBuffer.allocate(100);
        nameByteBuffer.put(fileName.getBytes());
        nameByteBuffer.flip();
        socketChannel.write(nameByteBuffer);
        while (fileChannel.read(byteBuf) != -1) {
            byteBuf.flip();
            socketChannel.write(byteBuf);
            byteBuf.clear();
        }

        fileChannel.close();
        socketChannel.close();
        fileInputStream.close();
    }
}
