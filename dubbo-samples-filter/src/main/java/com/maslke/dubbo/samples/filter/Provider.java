package com.maslke.dubbo.samples.filter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/provider.xml");
        applicationContext.start();

        System.out.println("dubbo filter provider started");
        new CountDownLatch(1).await();
    }
}
