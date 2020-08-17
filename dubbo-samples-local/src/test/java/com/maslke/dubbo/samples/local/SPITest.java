package com.maslke.dubbo.samples.local;

import java.util.ServiceLoader;

public class SPITest {
    public static void main(String[] args) {
        ServiceLoader<PrintService> serviceServiceLoader = ServiceLoader.load(PrintService.class);
        for (PrintService printService : serviceServiceLoader) {
            printService.printInfo();
        }
    }
}
