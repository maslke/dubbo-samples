package com.maslke.dubbo.samples.merger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

public class MergerProvider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/provider.xml");
        applicationContext.start();
        System.out.println("dubbo-merger-provider started");
        new CountDownLatch(1).await();
    }
}
