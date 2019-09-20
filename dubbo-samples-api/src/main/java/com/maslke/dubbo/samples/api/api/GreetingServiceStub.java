package com.maslke.dubbo.samples.api.api;

import com.maslke.dubbo.samples.api.api.GreetingService;

public class GreetingServiceStub implements GreetingService {

    private GreetingService greetingService;

    public GreetingServiceStub(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    @Override
    public String sayHi(String name) {
        System.out.println("before execute greeting servcie:" + name);
        String result = greetingService.sayHi(name);
        System.out.println("after execute greeting service");
        return "stub:" + result;
    }
}
