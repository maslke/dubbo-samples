package com.maslke.dubbo.samples.generic.impl;

import com.maslke.dubbo.samples.generic.api.GenericType;
import com.maslke.dubbo.samples.generic.api.Greeting;
import com.maslke.dubbo.samples.generic.api.GreetingService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {

    private ExecutorService executorService = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000));

    @Override
    public String sayHello(String name, String greets) {
        return greets + "," + name;
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name, String greets) {
        CompletableFuture<String> future = new CompletableFuture<>();
        executorService.execute(() -> {
            try {
                Thread.sleep(4000);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            future.complete(greets + "," + name);
        });
        return future;
    }

    @Override
    public CompletableFuture<Greeting> sayHelloAsyncComplex(String name, String greets) {
        CompletableFuture<Greeting> future = new CompletableFuture<>();
        executorService.execute(() -> {
            try {
                Thread.sleep(4000);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            future.complete(new Greeting(greets, name));
        });
        return future;
    }

    @Override
    public CompletableFuture<GenericType<Greeting>> sayHelloAsyncGeneric(String name, String greets) {
        CompletableFuture<GenericType<Greeting>> future = new CompletableFuture<>();
        executorService.execute(() -> {
            try {
                Thread.sleep(4000);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            Greeting greeting = new Greeting(greets, name);
            future.complete(new GenericType<>(greeting));
        });
        return future;
    }
}
