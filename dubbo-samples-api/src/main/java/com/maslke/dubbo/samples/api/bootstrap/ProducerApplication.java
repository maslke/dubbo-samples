package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.GreetingService;
import com.maslke.dubbo.samples.api.service.impl.GreetingServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class ProducerApplication {
    public static void main(String[] args) throws Exception {
        ServiceConfig<GreetingService> serviceServiceConfig = new ServiceConfig<>();
        serviceServiceConfig.setApplication(new ApplicationConfig("dubbo-api-producer"));
        serviceServiceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        serviceServiceConfig.setInterface(GreetingService.class);
        serviceServiceConfig.setRef(new GreetingServiceImpl());
        serviceServiceConfig.export();

        System.out.println("dubbo-api-producer started");
        System.in.read();
    }
}
