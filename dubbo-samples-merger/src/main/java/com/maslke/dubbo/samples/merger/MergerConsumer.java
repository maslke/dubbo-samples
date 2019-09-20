package com.maslke.dubbo.samples.merger;

import com.maslke.dubbo.samples.merger.api.MergerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MergerConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/consumer.xml");
        applicationContext.start();

        MergerService mergerService = applicationContext.getBean(MergerService.class);
        System.out.println(mergerService.merge());
    }
}
