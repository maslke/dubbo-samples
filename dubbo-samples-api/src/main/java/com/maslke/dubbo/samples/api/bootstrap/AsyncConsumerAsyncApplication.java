package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.api.GreettingServiceAsync;
import com.maslke.dubbo.samples.api.api.PoJo;
import com.maslke.dubbo.samples.api.api.Result;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @author maslke
 */
public class AsyncConsumerAsyncApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GreettingServiceAsync> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface(GreettingServiceAsync.class);
        referenceConfig.setGroup("dubbo");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(10000);
        referenceConfig.setAsync(true);
        GreettingServiceAsync greetingService = referenceConfig.get();
        CompletableFuture<String> future = greetingService.sayHello("maslke");
        future.whenComplete((value, throwable) -> {
            if (value != null) {
                System.out.println(value);
            } else if (throwable != null) {
                throwable.printStackTrace();
            }
        });
        new CountDownLatch(1).await();
    }
}
