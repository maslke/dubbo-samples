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


#2019/9/16

1. 测试了生产者和消费者在同一个包中的情况。
2. 笔记本中环境的zookeeper无法启动了，报错java.net.SocketException:权限不够。尚未解决。


#2019/9/18
1. 测试了mock配置的作用。在配置reference的时候，配置mock，可以在service调用出现超时等问题的时候，调用mock实现的接口。
2. RpcContext中的属性
3. dubbo:reference中merger="true"的作用
4. 配置了<dubbo:parameter key="qos.port" value="3333" />

#2019/9/19

1. dubbo:reference中的url配置，将会绕过注册中心，进行点对点的来查找服务提供者地址。经测试，需要在url中将group和version都配置进去。同时，发现此部分功能存在着疑似bug。
2. stub的配置
3. filter的配置和作用

#2019/9/20

1. 为什么在RpcContext中设置的Attachments，经过一次调用之后，第二次调用就不生效了？
2. dubbo中消费者关于cache的配置。
3. 



