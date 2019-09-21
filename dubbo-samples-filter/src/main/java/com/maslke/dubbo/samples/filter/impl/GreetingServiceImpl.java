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
        if (name == null) {
            throw new IllegalArgumentException("name is null.");
        }
        return "hi," + name;
    }

    @Override
    public String sayHi(String name, String greets) {
        System.out.println("sayHi.");

        String value = RpcContext.getContext().getAttachment("attachKey");
        System.out.println("value:" + value);
        if (name == null) {
            throw new IllegalArgumentException("name is null.");
        }
        return greets + "," + name;
    }

    @Override
    public Greeting greeting(String name) {
        System.out.println("greeting.");
        Greeting greeting = new Greeting();
        greeting.setName(name);
        greeting.setContent("hi," + name);
        return greeting;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("in sayHello:" + name);
    }

    @Override
    public Greeting greeting(String name, String greets) {
        Greeting greeting = new Greeting();
        greeting.setName(name);
        greeting.setContent(greets + "," + name);
        return greeting;
    }

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public String getCache() {
        return "Response:" + atomicInteger.getAndIncrement();
    }
}
