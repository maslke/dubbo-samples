package com.maslke.dubbo.samples.generic;

import com.maslke.dubbo.samples.generic.api.GenericType;
import com.maslke.dubbo.samples.generic.api.Greeting;
import com.maslke.dubbo.samples.generic.api.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-samples-generic-consumer");
        applicationConfig.setQosPort(3333);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(7000);
        GreetingService greetingService = referenceConfig.get();
        //execute sync
        System.out.println(greetingService.sayHello("dubbo", "hello"));

        // execute async
        CompletableFuture<String> future = greetingService.sayHelloAsync("dubbo2", "hello");
        future.whenComplete((u, v) -> {
            System.out.println("execute sayHelloAsync complete. result:" + u);
        });

        // execute async2

        CompletableFuture<Greeting> future1 = greetingService.sayHelloAsyncComplex("dubbo3", "hello");
        System.out.println("execute sayHelloAsyncComplex. result:" + future1.get());

        // execute async3
        CountDownLatch latch = new CountDownLatch(1);
        CompletableFuture<GenericType<Greeting>> future2 = greetingService.sayHelloAsyncGeneric("dubbo4", "hello");
        future2.whenComplete((u, v) -> {
            System.out.println("execute sayHelloAsyncGeneric.result:" + u.getType());
            latch.countDown();
        });
        latch.await();


    }
}
