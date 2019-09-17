dubbo-samples


官方demos消费记录:
<modules>
        <module>dubbo-maven-address-plugin</module>
        <module>dubbo-samples-annotation</module>
<!--        <module>dubbo-samples-api</module>-->
        <module>dubbo-samples-async</module>
        <module>dubbo-samples-attachment</module>
<!--        <module>dubbo-samples-basic</module>-->
        <module>dubbo-samples-cache</module>
        <module>dubbo-samples-chain</module>
        <module>dubbo-samples-callback</module>
        <module>dubbo-samples-compatible</module>
        <module>dubbo-samples-configcenter</module>
        <module>dubbo-samples-context</module>
        <module>dubbo-samples-direct</module>
        <module>dubbo-samples-docker</module>
<!--        <module>dubbo-samples-echo</module>-->
        <module>dubbo-samples-gateway</module>
        <module>dubbo-samples-generic</module>
<!--        <module>dubbo-samples-group</module>-->
        <module>dubbo-samples-governance</module>
<!--        <module>dubbo-samples-http</module>-->
        <module>dubbo-samples-jetty</module>
<!--        <module>dubbo-samples-local</module>-->
        <module>dubbo-samples-merge</module>
        <module>dubbo-samples-metadata-report</module>
        <module>dubbo-samples-mock</module>
        <module>dubbo-samples-monitor</module>
        <module>dubbo-samples-multi-registry</module>
        <module>dubbo-samples-notify</module>
        <module>dubbo-samples-resilience4j</module>
        <module>dubbo-samples-rest</module>
        <module>dubbo-samples-scala</module>
        <module>dubbo-samples-simplified-registry</module>
        <module>dubbo-samples-spi-compatible</module>
        <module>dubbo-samples-spring-hystrix</module>
        <module>dubbo-samples-spring-boot-hystrix</module>
        <module>dubbo-samples-stub</module>
        <module>dubbo-samples-switch-serialization-thread</module>
        <module>dubbo-samples-thrift</module>
        <module>dubbo-samples-validation</module>
<!--        <module>dubbo-samples-version</module>-->
        <module>dubbo-samples-zipkin</module>
        <module>dubbo-samples-zookeeper</module>
        <module>dubbo-samples-transaction</module>
        <module>dubbo-samples-consul</module>
        <module>dubbo-samples-edas</module>
        <module>dubbo-samples-nacos</module>
        <module>dubbo-samples-metrics</module>
        <module>dubbo-samples-environment-keys</module>
        <module>dubbo-samples-protostuff</module>
        <module>dubbo-samples-serialization</module>
        <module>dubbo-samples-sentinel</module>
    </modules>

# 2019/9/10
1. 基本掌握了dubbo:application、dubbo:service、dubbo:reference、dubbo:provider、dubbo:consumer配置的相关含义
2. 对于dubbo:service配置中的group配置和version配置进行了尝试。group、version和interface用于唯一确定接口，消费方和提供方配置必须一致。
3. 尝试了dubbo:provider配置的重试参数retries和timeout超时参数，并通过dubbo:service中的配置覆盖了此全局配置。
4. 分别使用了xml配置、api配置和注解的方式实现了简单的demo。

#2019/9/11
1. 测试了消费者reference设置version="*"这种通配符之后的效果。
2. 测试了loadbalance参数。设置为roundrobin之后，请求会分发的很均匀。如果是设置了random之后，则会呈现出随机状态。
3. dubbo官方的示例demos项目dubbo-samples-all中，关闭了dubbo-samples-api、dubbo-samples-basic、dubbo-samples-version，这几个项目都已了解清楚。


#2019/9/12

1. 测试dubbo官方提供的EchoService的$echo方法。
2. 测试了<dubbo:protol> 配置为name="http"的方式，使用内嵌tomcat方式的用例测试通过。
3. 测试server="jetty"的方式。


#2019/9/16

1. 测试了生产者和消费者在同一个包中的情况。
2. 笔记本中环境的zookeeper无法启动了，报错java.net.SocketException:权限不够。尚未解决。
