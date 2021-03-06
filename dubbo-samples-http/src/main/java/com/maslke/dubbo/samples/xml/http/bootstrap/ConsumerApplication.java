package com.maslke.dubbo.samples.xml.http.bootstrap;

import com.maslke.dubbo.samples.xml.http.api.GreetingService;
import org.apache.dubbo.rpc.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class ConsumerApplication {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/consumer.xml");
        applicationContext.start();
        GreetingService greetingService = applicationContext.getBean("greetingService", GreetingService.class);
        for (int i = 0; i < 2; i++) {
            System.out.println(greetingService.sayHi("the-whole-world"));
        }
        System.in.read();
    }
}
