package com.maslke.dubbo.samples.annotation.bootstrap;

import com.maslke.dubbo.samples.annotation.configuration.ConsumerConfiguration;
import com.maslke.dubbo.samples.annotation.consumer.GreetingServiceConsumer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class ConsumerApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        applicationContext.start();
        GreetingServiceConsumer consumer = applicationContext.getBean(GreetingServiceConsumer.class);
        System.out.println(consumer.sayHi("the-annotation-world"));
        System.in.read();
    }


}
