package com.maslke.dubbo.samples.api.bootstrap;

import com.maslke.dubbo.samples.api.api.PoJo;
import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author maslke
 */
// 泛化调用
public class ConsumerGenericBeanApplication {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("redis://localhost:6379"));
        referenceConfig.setApplication(new ApplicationConfig("dubbo-api-consumer"));
        referenceConfig.setInterface("com.maslke.dubbo.samples.api.api.GreetingService");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(10000);
        referenceConfig.setGeneric("bean");
        GenericService greetingService = referenceConfig.get();
        JavaBeanDescriptor descriptor = JavaBeanSerializeUtil.serialize("the-api-world");
        Object result = greetingService.$invoke("sayHello", new String[] {"java.lang.String"}, new Object[] {descriptor});
        System.out.println(JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor) result));
        new CountDownLatch(1).await();
    }
}
