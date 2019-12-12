# README



### 概述

​	Spring Cloud Config 的 Demo



### 相关介绍

​	在微服务架构中，可以使用一个建立在 Git 上远程的配置中心对各个微服务进行配置，也就是说“配置中心”作为一个服务，其他的作为客户端，来向“配置中心”请求配置。

```xml
<!-- 配置服务端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

```xml
<!-- 配置客户端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-client</artifactId>
</dependency>
```

