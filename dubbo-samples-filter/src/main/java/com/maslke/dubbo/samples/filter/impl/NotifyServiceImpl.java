package com.maslke.dubbo.samples.filter.impl;

import com.maslke.dubbo.samples.filter.api.Greeting;
import com.maslke.dubbo.samples.filter.api.NotifyService;

public class NotifyServiceImpl implements NotifyService {
    @Override
    public void onReturn(String result, String parameter) {
        System.out.println("call successfully. Result:" + result + ", parameter:" + parameter);
    }

    @Override
    public void onThrow(Throwable ex, String paramter) {
        System.out.println("call throws exception. message:" + ex.getMessage() + ", paramter:" + paramter);
    }

    @Override
    public void onReturn2(String result, String name, String greets) {
        System.out.println("call successfully. Result:" + result + ", name:" + name + ",greets:" + greets);
    }

    @Override
    public void onGreetingReturn(Greeting greeting, String name) {
        System.out.println("call successfully. Result:" + greeting + ",name:" + name);
    }


    @Override
    public void onSayHelloReturn(Object result, String name) {
        System.out.println("onSayHelloReturn,result:" + result + ",name:" + name);
    }

    @Override
    public void onGreetingReturn2(Greeting greeting, String name, String greets) {
        System.out.println("call successfully. Result:" + greeting + ",name:" + name + ",greets:" + greets);

    }
}
