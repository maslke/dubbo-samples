package com.maslke.dubbo.samples.annotation.configuration;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.maslke.dubbo.samples.annotation.service.impl")
public class ProducerConfiguration {
    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(1000);
        return providerConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName("dubbo-annotation-producer");
        return config;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig config = new RegistryConfig();
        config.setAddress("zookeeper://localhost:2181");
        return config;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig config = new ProtocolConfig();
        config.setName("dubbo");
        config.setPort(20880);
        return config;
    }
}
