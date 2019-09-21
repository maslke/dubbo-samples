package com.maslke.dubbo.samples.filter;

import com.maslke.dubbo.samples.filter.api.GreetingService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/consumer.xml");
        applicationContext.start();

        GreetingService greetingService = applicationContext.getBean(GreetingService.class);
        RpcContext.getContext().setAttachment("attachKey", "attachValue");

        System.out.println(greetingService.sayHi("maslke", " hello world"));
        System.out.println(greetingService.greeting("maslke"));
        greetingService.sayHello("maslke");
//        System.out.println(greetingService.sayHi(null));

        String prev = null;
//        for (int i = 0; i < 10; i++) {
//            String response = greetingService.getCache();
//            if (prev != null && !prev.equals(response)) {
//                System.out.println("error.resonse is not from cache");
//            } else {
//                System.out.println("ok.response is:" + response);
//            }
//            prev =response;
//        }

//        for (int i = 0; i < 10000;i++) {
//            String response = greetingService.getCache();
//            System.out.println(response);
//            if (prev == null) {
//                prev = response;
//            }
//        }
//        String response = greetingService.getCache();
//        if (!response.equals(prev)) {
//            System.out.println("the cache is expired.");
//        } else {
//            System.out.println("the cache is not expired");
//        }
    }
}
