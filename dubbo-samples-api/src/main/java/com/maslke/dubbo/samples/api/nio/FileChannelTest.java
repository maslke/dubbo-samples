package com.maslke.dubbo.samples.api.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream =  new FileInputStream("C:\\Users\\DELL\\Desktop\\ApplicationConfig.json");
        FileChannel fileChannel = fileInputStream.getChannel();
        File file = new File("C:\\Users\\DELL\\Desktop\\2.json");
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                System.out.println("create file failed");
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel1 = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
        while ( (fileChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            fileChannel1.write(byteBuffer);
            byteBuffer.clear();
        }

        fileChannel.close();
        fileChannel1.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
