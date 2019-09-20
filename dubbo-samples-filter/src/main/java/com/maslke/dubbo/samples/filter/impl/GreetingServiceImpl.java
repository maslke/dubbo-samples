package com.maslke.dubbo.samples.filter.impl;

import com.maslke.dubbo.samples.filter.api.Greeting;
import com.maslke.dubbo.samples.filter.api.GreetingService;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.atomic.AtomicInteger;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        System.out.println("sayHi.");

        String value = RpcContext.getContext().getAttachment("attachKey");
        System.out.println("value:" + value);

        return "hi," + name;
    }

    @Override
    public Greeting greeting(String name) {
        System.out.println("greeting.");
        Greeting greeting = new Greeting();
        greeting.setName(name);
        greeting.setContent("hi," + name);
        return greeting;
    }

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public String getCache() {
        return "Response:" + atomicInteger.getAndIncrement();
    }
}
