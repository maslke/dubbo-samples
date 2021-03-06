package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.service.impl.GreetingServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * @author maslke
 */
public class ProducerApplication {
    public static void main(String[] args) throws Exception {
        ServiceConfig<GreetingService> serviceServiceConfig = new ServiceConfig<>();
        serviceServiceConfig.setApplication(new ApplicationConfig("dubbo-api-producer"));
        serviceServiceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        serviceServiceConfig.setInterface(GreetingService.class);
        serviceServiceConfig.setRef(new GreetingServiceImpl());
        serviceServiceConfig.setGroup("dubbo");
        serviceServiceConfig.setVersion("1.0.0");
        serviceServiceConfig.export();
        System.out.println("dubbo-api-producer started");
        new CountDownLatch(1).await();
    }
}
