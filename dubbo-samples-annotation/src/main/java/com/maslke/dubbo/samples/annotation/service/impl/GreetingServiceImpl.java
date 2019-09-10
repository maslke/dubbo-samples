package com.maslke.dubbo.samples.annotation.service.impl;

import com.maslke.dubbo.samples.annotation.api.GreetingService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " +
                name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "hello," + name + ", response from " + RpcContext.getContext().getLocalAddress();
    }
}
