package com.maslke.dubbo.samples.annotation.consumer;

import com.maslke.dubbo.samples.annotation.api.GreetingService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
@Component
public class GreetingServiceConsumer {
    @Reference
    GreetingService greetingService;

    public String sayHi(String name) {
        return greetingService.sayHi(name);
    }
}
