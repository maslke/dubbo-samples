package com.maslke.dubbo.samples.api.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UdpServer {

    public static void main(String[] args) throws IOException {
        new UdpServer().receive();
    }

    private void receive() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress("127.0.0.1", 8989));

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        while (true) {
            int size = selector.select();
            if (size == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    SocketAddress client = channel.receive(byteBuffer);
                    byteBuffer.flip();
                    String input = new String(byteBuffer.array(), 0, byteBuffer.limit());
                    if (!"000".equals(input)) {
                        System.out.println(input);
                    }
                    byteBuffer.clear();
                }
                iterator.remove();
            }
        }

    }
}
