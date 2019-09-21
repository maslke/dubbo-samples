package com.maslke.dubbo.samples.filter.api;

public interface NotifyService {
    void onReturn(String result, String parameter);
    void onReturn2(String result, String name, String greets);
    void onGreetingReturn(Greeting greeting, String name);
    void onGreetingReturn2(Greeting greeting, String name, String greets);
    void onThrow(Throwable ex, String parameter);
    void onSayHelloReturn(Object result, String name);
}
