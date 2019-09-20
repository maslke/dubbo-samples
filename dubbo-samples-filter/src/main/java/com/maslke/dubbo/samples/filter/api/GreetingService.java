package com.maslke.dubbo.samples.filter.api;

public interface GreetingService {
    String sayHi(String name);
    Greeting greeting(String name);
    String getCache();
}
