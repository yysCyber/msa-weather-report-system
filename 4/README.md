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



### 这里的做法

​	远程配置中心设置在<https://github.com/yysCyber/msa-weather-report-system/tree/master/4/config-repo>，在这个目录下有一个`msa-eureka-client-config-client-dev.yml`配置文件，其中有一个自定义的配置项`myself.name`，值为`yscyber`。

​	在`msa-eureka-client-config-server`中的配置如下：

```yaml
server:
  port: 8888
  
spring:
  application:
    name: msa-eureka-client-config-server
  cloud:
    config:
      server:
        git:
          # 远程配置中心所在的 Git 仓库
          uri: https://github.com/yysCyber/msa-weather-report-system
          # 配置中心具体的目录，注意这个不是根据浏览器上的 URL 来的，而直接就是“目录”
          search-paths: 4/config-repo
          
eureka:
  client:
    service-url.defaultZone: http://localhost:8761/eureka/
```

​	在`msa-eureka-client-config-client`中通过`msa-eureka-client-config-server`从“远程的配置中心”获取`myself.name`这一自定义配置。

​	`msa-eureka-client-config-client`中的配置如下：

```yaml
server:
  port: 8083

spring:
  application:
    name: msa-eureka-client-config-client
  cloud:
    config:
      # config server 的请求路径，通过 msa-eureka-client-config-server 微服务从“远程配置中心”获取配置
      uri: http://localhost:8888
      # profile 需要参考“配置中心配置文件的命名规则”
      # 这里将会读取到远程配置中心中的配置文件是 msa-eureka-client-config-client.dev.yml
      profile: dev

eureka:
  client:
    service-url.defaultZone: http://localhost:8761/eureka/
```

​	最终，如何验证是否成功，在`msa-eureka-client-config-client`中有一个“单元测试”，`GitConfigTest.java`，运行看输出结果是否为`yscyber`。

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GitConfigTest {

    // 使用 @Value 注解以及 ${} 将配置文件中的配置项的值注入给成员变量
    @Value("${myself.name}")
    private String myselfName;

    @Test
    public void demo() {
        System.out.println(myselfName);
    }
    
}
```



### 各个微服务信息

| 微服务名                        | 实例端口/性质                            | 说明                                               |
| ------------------------------- | ---------------------------------------- | -------------------------------------------------- |
| msa-eureka-server               | 8761<br/>Eureka Server                   | 微服务的管理者 Eureka Server                       |
| msa-eureka-client-config-server | 8888<br/>Eureka Client<br/>Config Server | 提供“配置”的微服务，可以向其他微服务提供相关的配置 |
| msa-eureka-client-config-client | 8083<br/>Eureka Client<br/>Config Client | 通过请求“配置”微服务来获取“远程配置中心”的配置     |

​	各个微服务间的协作等见“图1.jpg”