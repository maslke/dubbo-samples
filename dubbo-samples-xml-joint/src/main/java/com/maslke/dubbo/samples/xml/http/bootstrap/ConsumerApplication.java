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
        EchoService echoService = (EchoService) greetingService;
        System.out.println(echoService.$echo("hello"));

        System.out.println("sayHi:" + greetingService.sayHi("maslke"));
        System.out.println("replyHi:" + greetingService.replayHi("maslke"));
        System.out.println("sayHi:" + greetingService.sayHi("maslke", "hello world").get());
        System.out.println("replyHi:" + greetingService.replayHi("maslke"));

        System.in.read();
    }
}
