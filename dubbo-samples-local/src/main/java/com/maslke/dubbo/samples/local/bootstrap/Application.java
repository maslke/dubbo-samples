package com.maslke.dubbo.samples.local.bootstrap;

import com.maslke.dubbo.samples.local.api.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author:maslke
 * @date:2019/9/16
 * @version:0.0.1
 */
public class Application {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/dubbo" +
                ".xml");
        applicationContext.start();

        GreetingService greetingService = applicationContext.getBean("greetingService", GreetingService.class);
        System.out.println(greetingService.sayHello("maslke"));
        new CountDownLatch(1).await();
    }
}
