package com.maslke.dubbo.samples.api.api;

// Mock服务
// Mock服务的是实现，路径和名称都有隐含的要求。
// 在测试Mock服务调用的时候，Provider不需要启动
public class GreetingServiceMock implements GreetingService {
    @Override
    public String sayHi(String name) {
        return "Hello, mock " + name;
    }

    @Override
    public String sayHello(String name) {
        return "sayHello, mock" + name;
    }

    @Override
    public Result<String> testGeneric(PoJo pojo) {
        return null;
    }
}
