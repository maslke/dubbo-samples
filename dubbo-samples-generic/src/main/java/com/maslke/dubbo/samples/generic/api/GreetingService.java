package com.maslke.dubbo.samples.generic.api;

import java.util.concurrent.CompletableFuture;

public interface GreetingService {
    String sayHello(String name, String greets);
    CompletableFuture<String> sayHelloAsync(String name, String greets);
    CompletableFuture<Greeting> sayHelloAsyncComplex(String name, String greets);
    CompletableFuture<GenericType<Greeting>> sayHelloAsyncGeneric(String name, String greets);
}
