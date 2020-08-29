package com.maslke.dubbo.samples.api.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class UdpServer {

    public static void main(String[] args) throws IOException {
        new UdpServer().receive();
    }

    private void receive() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.bind(new InetSocketAddress("127.0.0.1", 8989));

        Selector selector = Selector.open();
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
    }
}
