package com.maslke.dubbo.samples.api.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSockectChannelTest {
    public static void main(String[] args) throws Exception {
        String outFileName = "C:\\Users\\DELL\\Desktop\\output.json";
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

    }
}
