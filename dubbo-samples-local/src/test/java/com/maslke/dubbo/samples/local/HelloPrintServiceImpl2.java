package com.maslke.dubbo.samples.local;

public class HelloPrintServiceImpl2 implements PrintService {
    @Override
    public void printInfo() {
        System.out.println("hello world2!");
    }
}
