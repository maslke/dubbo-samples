package com.maslke.dubbo.samples.xml.joint.bootstrap;

import java.util.concurrent.CountDownLatch;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class ProducerApplication2 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/producer2.xml");
        applicationContext.start();
        System.out.println("the dubbo-greeting-service-producer-2 started");
        new CountDownLatch(1).await();

    }
}
