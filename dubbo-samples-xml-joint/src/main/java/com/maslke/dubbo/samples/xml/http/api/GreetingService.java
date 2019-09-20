package com.maslke.dubbo.samples.xml.http.api;

import java.util.concurrent.CompletableFuture;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public interface GreetingService {
    String sayHi(String name);

    default CompletableFuture<String> sayHi(String name, String word) {
        System.out.println("name:" + name);
        System.out.println("word:" + word);
        return CompletableFuture.completedFuture(sayHi(name));
    }

    default String replayHi(String name) {
        return "Hi," + name;
    }

}
