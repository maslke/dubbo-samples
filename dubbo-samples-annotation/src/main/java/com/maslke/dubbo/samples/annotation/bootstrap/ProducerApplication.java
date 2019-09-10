package com.maslke.dubbo.samples.annotation.bootstrap;

import com.maslke.dubbo.samples.annotation.configuration.ProducerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class ProducerApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext configApplicationContext
                = new AnnotationConfigApplicationContext(ProducerConfiguration.class);
        configApplicationContext.start();
        System.out.println("the dubbo-annotation-greeting-service started");
        System.in.read();
    }
}
