package com.maslke.dubbo.samples.generic.service;

import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;

public class GreetingServiceImpl implements GenericService {

    @Override
    public Object $invoke(String s, String[] strings, Object[] objects) throws GenericException {
        if ("sayHi".equals(s)) {
            throw new GenericException(new RpcException("throw exceptions"));
        }
        else if ("sayHiAsync".equals(s)) {
            return CompletableFuture.completedFuture("sayHelloAsync:" + objects[0]);
        }
        else {
            throw new GenericException(new RpcException("method does not exists"));
        }
    }
}
