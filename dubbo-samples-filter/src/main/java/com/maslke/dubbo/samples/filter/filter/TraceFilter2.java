package com.maslke.dubbo.samples.filter.filter;

import com.maslke.dubbo.samples.filter.api.GreetingService;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

@Activate
public class TraceFilter2 implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("in filter2:" + invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
