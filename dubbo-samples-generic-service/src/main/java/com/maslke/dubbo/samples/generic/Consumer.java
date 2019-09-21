package com.maslke.dubbo.samples.generic;

import com.maslke.dubbo.samples.generic.api.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/consumer.xml");
        applicationContext.start();

        GreetingService greetingService = applicationContext.getBean(GreetingService.class);
//        System.out.println(greetingService.sayHi("maslke"));
        CompletableFuture<String> future = greetingService.sayHiAsync("maslke future");
        CountDownLatch latch = new CountDownLatch(1);
        future.whenComplete((u, v) -> {
            System.out.println(u);
            latch.countDown();
        });
        latch.await();
    }
}
