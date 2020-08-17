package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.api.PoJo;
import com.maslke.dubbo.samples.api.api.Result;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

/**
 * @author maslke
 */
public class CompletableFutureConsumerApplication {
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
        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((s, throwable) -> {
            System.out.println("异步回调完成");
            if (s != null) {
                System.out.println(s);
            } else if (throwable != null) {
                throwable.printStackTrace();
            }
        });
        System.out.println(greetingService.sayHi("the-another-api-world"));
        CompletableFuture<String> future2 = RpcContext.getContext().getCompletableFuture();
        future2.whenComplete((s, throwable) -> {
            System.out.println("异步回调完成");
            if (s != null) {
                System.out.println(s);
            } else if (throwable != null) {
                throwable.printStackTrace();
            }
        });
        new CountDownLatch(1).await();
    }
}
