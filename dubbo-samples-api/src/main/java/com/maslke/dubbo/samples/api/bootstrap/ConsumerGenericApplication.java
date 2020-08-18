package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.PoJo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author maslke
 */
// 泛化调用
public class ConsumerGenericApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface("com.maslke.dubbo.samples.api.api.GreetingService");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(10000);
        referenceConfig.setGeneric(true);
        GenericService greetingService = referenceConfig.get();
        Object result = greetingService.$invoke("sayHi", new String[]{"java.lang.String"}, new String[]{"the-api-world"});
        System.out.println((String) result);
        RpcContext.getContext().setAttachment("company", "alibaba");
        Object result2 = greetingService.$invoke("sayHello", new String[]{"java.lang.String"}, new String[]{"world"});
        System.out.println((String) result2);
        PoJo poJo = new PoJo();
        poJo.setName("maslke");
        poJo.setId("123");
        Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("name", "maslke");
        map.put("class", "com.maslke.dubbo.samples.api.api.PoJo");
        Object result3 = greetingService.$invoke("testGeneric", new String[]{"com.maslke.dubbo.samples.api.api.PoJo"}, new Object[]{map});
        System.out.println(result3);
        new CountDownLatch(1).await();
    }
}
