package com.maslke.dubbo.samples.api.api;

/**
 * @author maslke
 */
// 演示同步调用
public interface GreetingService {
    String sayHi(String name);

    String sayHello(String name);

    Result<String> testGeneric(PoJo pojo);
}
