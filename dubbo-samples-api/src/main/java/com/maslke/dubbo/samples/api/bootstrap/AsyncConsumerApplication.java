package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.api.PoJo;
import com.maslke.dubbo.samples.api.api.Result;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @author maslke
 */
public class AsyncConsumerApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setGroup("dubbo");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(10000);
        referenceConfig.setAsync(true);
        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHi("the-api-world"));
        Future<String> future = RpcContext.getContext().getFuture();
        System.out.println(future.get());
        RpcContext.getContext().setAttachment("company", "alibaba");
        System.out.println(greetingService.sayHello("world"));
        future = RpcContext.getContext().getFuture();
        System.out.println(future.get());
        PoJo poJo = new PoJo();
        poJo.setName("maslke");
        poJo.setId("123");
        System.out.println(greetingService.testGeneric(poJo));
        Future<Result<String>> future2 = RpcContext.getContext().getFuture();
        System.out.println(future2.get());
        new CountDownLatch(1).await();
    }
}
