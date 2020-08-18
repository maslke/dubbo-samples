package com.maslke.dubbo.samples.api.api;

import java.util.concurrent.CompletableFuture;

// 演示基于定义CompletableFuture签名的接口如何实现异步调用
public interface GreettingServiceAsync {

    CompletableFuture<String> sayHello(String name);
}
