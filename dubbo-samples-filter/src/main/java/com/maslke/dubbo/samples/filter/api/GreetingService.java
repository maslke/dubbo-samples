package com.maslke.dubbo.samples.filter.api;

public interface GreetingService {
    String sayHi(String name);
    String sayHi(String name, String greets);
    Greeting greeting(String name);
    Greeting greeting(String name, String greets);
    String getCache();
    void sayHello(String name);
}
