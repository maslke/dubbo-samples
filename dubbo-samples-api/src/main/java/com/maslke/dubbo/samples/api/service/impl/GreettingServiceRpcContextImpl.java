package com.maslke.dubbo.samples.api.service.impl;

import com.maslke.dubbo.samples.api.api.GreettingServiceRpcContext;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 用于演示AsyncContext如何实现异步调用
public class GreettingServiceRpcContextImpl implements GreettingServiceRpcContext {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 1, TimeUnit.MINUTES,
            new SynchronousQueue<>(), new NamedThreadFactory("biz-thread-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());
    public String sayHello(String name) {
        final AsyncContext asyncContext =RpcContext.startAsync();
        threadPoolExecutor.execute(() -> {
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            asyncContext.write("hello " + name);
        });
        return null;
    }
}
