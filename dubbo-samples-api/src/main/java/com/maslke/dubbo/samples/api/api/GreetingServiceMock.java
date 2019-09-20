package com.maslke.dubbo.samples.api.api;

public class GreetingServiceMock implements GreetingService {
    @Override
    public String sayHi(String name) {
        return "mock:" + name;
    }
}
