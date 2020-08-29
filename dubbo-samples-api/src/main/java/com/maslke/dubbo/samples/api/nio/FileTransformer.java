package com.maslke.dubbo.samples.api.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileTransformer {
    public static void main(String[] args) throws IOException {
        new FileTransformer().transformer("C:\\Users\\DELL\\Desktop\\ApplicationConfig.json",
                "C:\\Users\\DELL\\Desktop\\copyed.json");
    }

    private void transformer(String sourceFilePath, String outFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("source file does not exists");
        }
        File outFile = new File(outFilePath);
        if (!outFile.exists()) {
            boolean success = outFile.createNewFile();
            if (!success) {
                throw new RuntimeException("create file failed");
            }
        }
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileChannel channel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream(outFile);
        FileChannel outChannel = outputStream.getChannel();

        outChannel.transferFrom(channel, 0, channel.size());

        channel.close();
        outChannel.close();
        inputStream.close();
        outputStream.close();
    }
}
