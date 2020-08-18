package com.maslke.dubbo.samples.api.service.impl;

import com.maslke.dubbo.samples.api.api.GreetingService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.maslke.dubbo.samples.api.api.PoJo;
import com.maslke.dubbo.samples.api.api.Result;
import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author maslke
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello, request from "
                + RpcContext.getContext().getRemoteAddress());
        return "Hello, " + name + ", response from " + RpcContext.getContext().getLocalAddress();
    }

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 从RpcContext中获取附加参数
        return "Hello, " + name + " " + RpcContext.getContext().getAttachment("company");
    }

    @Override
    public Result<String> testGeneric(PoJo pojo) {
        Result<String> result = new Result<>();
        result.setSuccess(true);
        try {
            result.setData(JSON.json(pojo));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
