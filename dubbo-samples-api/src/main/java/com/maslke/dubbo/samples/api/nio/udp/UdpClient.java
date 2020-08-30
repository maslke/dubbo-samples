package com.maslke.dubbo.samples.api.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class UdpClient {

    public static void main(String[] args) throws IOException {
        new UdpClient().send();
    }

    private void send() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            if ("000".equals(next)) {
                break;
            }
            byteBuffer.put(next.getBytes());
            byteBuffer.flip();
            // DatagramChannel 使用send方法
            channel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 8989));
            byteBuffer.clear();
        }
        channel.close();
    }
}
