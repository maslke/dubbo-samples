package com.maslke.dubbo.samples.xml.joint.service.impl;

import com.maslke.dubbo.samples.xml.joint.api.GreetingService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author:maslke
 * @date:2019/9/10
 * @version:0.0.1
 */
public class GreetingServiceImpl implements GreetingService {
    private final static Random random = new Random();
    @Override
    public String sayHi(String name) {
        try {

            Thread.sleep(random.nextInt(10000));
        } catch (Exception ex) {
            //
        }
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " +
                name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "[group=type1]Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
