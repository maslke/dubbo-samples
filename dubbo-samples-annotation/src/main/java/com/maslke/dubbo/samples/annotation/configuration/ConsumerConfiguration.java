package com.maslke.dubbo.samples.annotation.configuration;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.maslke.dubbo.samples.annotation.consumer")
@ComponentScan(basePackages = {"com.maslke.dubbo.samples.annotation.consumer"})
public class ConsumerConfiguration {
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-annotation-consumer");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://localhost:2181");
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20881);
        return protocolConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        return new ConsumerConfig();
    }
}
