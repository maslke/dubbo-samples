package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import java.lang.ref.Reference;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class ConsumerApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface(GreetingService.class);
        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHi("the-api-world"));
        System.in.read();
    }
}
