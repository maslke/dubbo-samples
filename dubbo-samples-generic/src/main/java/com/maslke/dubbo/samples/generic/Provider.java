package com.maslke.dubbo.samples.generic;

import com.maslke.dubbo.samples.generic.api.Greeting;
import com.maslke.dubbo.samples.generic.api.GreetingService;
import com.maslke.dubbo.samples.generic.impl.GreetingServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class Provider {
    public static void main(String[] args) throws Exception {
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-samples-generic-provider");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://localhost:2181");
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo",20880);
        ServiceConfig<GreetingService> serviceServiceConfig = new ServiceConfig<>();
        serviceServiceConfig.setApplication(applicationConfig);
        serviceServiceConfig.setRegistry(registryConfig);
        serviceServiceConfig.setProtocol(protocolConfig);
        serviceServiceConfig.setInterface(GreetingService.class);
        serviceServiceConfig.setRef(new GreetingServiceImpl());
        serviceServiceConfig.export();
        System.out.println("dubbo-samples-generic-provider started");
        System.in.read();
    }
}
