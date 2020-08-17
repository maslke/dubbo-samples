package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.api.PoJo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author maslke
 */
// 测试远程服务Mock
public class MockConsumerApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setGroup("dubbo");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(10000);
        referenceConfig.setCheck(false);
        referenceConfig.setMock(true);
        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHi("the-api-world"));
        RpcContext.getContext().setAttachment("company", "alibaba");
        System.out.println(greetingService.sayHello("world"));
        PoJo poJo = new PoJo();
        poJo.setName("maslke");
        poJo.setId("123");
        System.out.println(greetingService.testGeneric(poJo));
        new CountDownLatch(1).await();
    }
}
