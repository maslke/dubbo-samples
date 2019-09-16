dubbo-samples

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