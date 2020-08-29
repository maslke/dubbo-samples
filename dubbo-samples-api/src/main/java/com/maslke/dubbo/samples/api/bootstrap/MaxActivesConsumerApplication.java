package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.api.PoJo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author maslke
 */
public class MaxActivesConsumerApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setGroup("dubbo");
        referenceConfig.setVersion("1.0.0");
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHi");
        methodConfig.setActives(1);
        methodConfig.setTimeout(10 * 1000 * 60);
        methodConfig.setAsync(true);
//        referenceConfig.setActives(1);
        referenceConfig.setMethods(Collections.singletonList(methodConfig));
        GreetingService greetingService = referenceConfig.get();

        for (long i = 0; i < 3; i++) {
            System.out.println("calling sayHi " + i);
            greetingService.sayHi("the-api-world");
            CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
            future.whenComplete((s, throwable) -> {
                System.out.println("异步回调完成");
                if (s != null) {
                    System.out.println(s);
                } else if (throwable != null) {
                    throwable.printStackTrace();
                }
            });
        }



        new CountDownLatch(1).await();
    }
}
