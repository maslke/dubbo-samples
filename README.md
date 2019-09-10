dubbo-samples

# 2019/9/10
1. 基本掌握了dubbo:application、dubbo:service、dubbo:reference、dubbo:provider、dubbo:consumer配置的相关含义
2. 对于dubbo:service配置中的group配置和version配置进行了尝试。group、version和interface用于唯一确定接口，消费方和提供方配置必须一致。
3. 尝试了dubbo:provider配置的重试参数retries和timeout超时参数，并通过dubbo:service中的配置覆盖了此全局配置。
4. 分别使用了xml配置、api配置和注解的方式实现了简单的demo。