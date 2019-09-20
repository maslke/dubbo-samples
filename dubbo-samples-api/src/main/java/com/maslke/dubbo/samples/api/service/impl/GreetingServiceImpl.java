package com.maslke.dubbo.samples.api.service.impl;

import com.maslke.dubbo.samples.api.api.GreetingService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
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
}
