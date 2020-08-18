package com.maslke.dubbo.samples.api.api;

// 用于演示AsyncContext如何实现异步调用
public interface GreettingServiceRpcContext {
    String sayHello(String name);
}
