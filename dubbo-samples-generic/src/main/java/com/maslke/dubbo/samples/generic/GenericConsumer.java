package com.maslke.dubbo.samples.generic;

import com.maslke.dubbo.samples.generic.api.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class GenericConsumer {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-samples-generic-consumer");
        applicationConfig.setQosPort(3333);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(7000);
        referenceConfig.setGeneric(true);
        /**
         * 是否进行异步执行。如果为true的话，则进行异步执行。忽略返回值。需要借助RpcContext.getContext.getCompletableFuture()来获取返回值。
         */
        referenceConfig.setAsync(false);
        GenericService greetingService = referenceConfig.get();

        invokeSayHello(greetingService);
        invokeAsyncSayHello(greetingService);
        invokeSayHelloAsync(greetingService);
        invokeAsyncSayHelloAsync(greetingService);
    }

    private static void invokeSayHello(GenericService service) throws Exception {
        Object result = service.$invoke("sayHello", new String[]{"java.lang.String", "java.lang.String"}, new Object[]{"dubbo", "hello"});
        CompletableFuture<Object> future = RpcContext.getContext().getCompletableFuture();
        CountDownLatch latch = new CountDownLatch(1);
        future.whenComplete((u, v) -> {
            System.out.println("invokeSayHello.complete:" + u);
            latch.countDown();
        });
        latch.await();
        System.out.println("invokeSayHello.return:" + result);
    }


    private static void invokeAsyncSayHello(GenericService service) throws Exception {
        CompletableFuture<Object> result = service.$invokeAsync("sayHello", new String[]{"java.lang.String", "java.lang.String"},
                new Object[]{"dubbo", "hello"});
        CountDownLatch latch = new CountDownLatch(1);
        result.whenComplete((u, v) -> {
            System.out.println("invokeAsyncSayHello.complete:" + u);
            latch.countDown();
        });
        latch.await();
    }


    private static void invokeSayHelloAsync(GenericService service) throws Exception {
        Object result = service.$invoke("sayHelloAsync", new String[]{"java.lang.String", "java.lang.String"}
                , new Object[]{"dubbo", "helllo"});
        CountDownLatch latch = new CountDownLatch(1);
        CompletableFuture<Object> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((u, v) -> {
            System.out.println("in invokeSayHelloAsync complete:" + u);
            latch.countDown();
        });
        latch.await();
        System.out.println("in invokeSayHelloAsync return:" + result);
    }

    private static void invokeAsyncSayHelloAsync(GenericService service) throws Exception {
        CompletableFuture<Object> future = service.$invokeAsync("sayHelloAsync", new String[]{"java.lang.String", "java.lang.String"},
                new Object[]{"dubbo", "hello"});
        CountDownLatch latch = new CountDownLatch(1);
        future.whenComplete((u, v) -> {
            System.out.println("in invokeAsyncSayHelloAsync complete:" + u);
            latch.countDown();
        });
        latch.await();
    }


}
