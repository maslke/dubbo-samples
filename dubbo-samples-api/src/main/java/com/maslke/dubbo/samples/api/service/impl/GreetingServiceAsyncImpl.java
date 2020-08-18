package com.maslke.dubbo.samples.api.service.impl;

import com.maslke.dubbo.samples.api.api.GreettingServiceAsync;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreetingServiceAsyncImpl implements GreettingServiceAsync {

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 1, TimeUnit.MINUTES,
            new SynchronousQueue<>(), new NamedThreadFactory("biz-thread-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public CompletableFuture<String> sayHello(String name) {
        return CompletableFuture.supplyAsync(() -> {
            RpcContext context = RpcContext.getContext();
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "Hello " + name + " " + context.getAttachment("company");
        }, threadPoolExecutor);
    }
}
