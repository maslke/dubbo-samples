package com.maslke.dubbos.xml;

import com.maslke.dubbo.samples.xml.GreetingService;
import java.util.concurrent.CountDownLatch;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class GreetingServiceConsumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/consumer.xml");
        applicationContext.start();
        GreetingService greetingService = applicationContext.getBean("greetingService", GreetingService.class);
        System.out.println(greetingService.sayHi("the-whole-world"));
        new CountDownLatch(1).await();
    }
}
