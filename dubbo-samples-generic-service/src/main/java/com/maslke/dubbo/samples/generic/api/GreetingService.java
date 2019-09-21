package com.maslke.dubbo.samples.generic.api;

import java.util.concurrent.CompletableFuture;

public interface GreetingService {
    String sayHi(String name);
    CompletableFuture<String> sayHiAsync(String name);
}
