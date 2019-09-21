package com.maslke.dubbo.samples.generic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provoider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/provider.xml");
        applicationContext.start();

        System.out.println("dubbo-samples-generic-service provider started");
        System.in.read();
    }
}
