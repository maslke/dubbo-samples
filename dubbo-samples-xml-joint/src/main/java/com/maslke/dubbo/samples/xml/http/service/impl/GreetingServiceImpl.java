package com.maslke.dubbo.samples.xml.http.service.impl;

import com.maslke.dubbo.samples.xml.http.api.GreetingService;
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
        RpcContext rpcContext = RpcContext.getContext();
        System.out.println("RemoteApplicationName:" + rpcContext.getRemoteApplicationName());
        System.out.println("isProviderSide:" + rpcContext.isProviderSide());
        System.out.println("isConsumerSide:" + rpcContext.isConsumerSide());
        System.out.println("methodName:" + rpcContext.getMethodName());
        System.out.println("remoteHostName:" + rpcContext.getRemoteHostName());
        System.out.println("remoteHost:" + rpcContext.getRemoteHost());
        System.out.println("remoteAddressString:" + rpcContext.getRemoteAddressString());
        System.out.println("remoteAddress:" + rpcContext.getRemoteAddress());
        System.out.println("remotePort:" + rpcContext.getRemotePort());
        System.out.println("localHostName:" + rpcContext.getLocalHostName());
        System.out.println("localHost:" + rpcContext.getLocalHost());
        System.out.println("localAddressString:" + rpcContext.getLocalAddressString());
        System.out.println("localAddress:" + rpcContext.getLocalAddress());
        System.out.println("localPort:" + rpcContext.getLocalPort());
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " +
                name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "[group=type1]Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
