# README



### 概述

​	在 2 的“微服务架构”中，使用 Zuul 来作为 API 网关。



### 相关介绍

​	Zuul 是 Spring Cloud 重要组件，是微服务 API 网关。

​	在微服务架构中，往往会有大量的微服务提供者以及大量的微服务消费者，它们之间的联系在大型的系统中是“千丝万缕”的。

​	从 2 的编写中可以看出，微服务`msa-wr-report-server`需要通过正确的“微服务名”（`@FeignClient`注解中指定）和正确的“请求路径”（`@GetMapping`等注解中指定），但是如果在开发过程中，像“请求路径”这样的稍有变动，在这个小型的“天气预报”系统中可能感觉不到什么，但是在大型系统，真的是“牵一发而动全身”。

​	为了减少这种情况的发生，保持整个稳定性，于是 Spring Cloud 便开始使用 Zuul 作为“微服务 API 网关”，将提供微服务的 API 进行聚合，使微服务的消费者直接依赖的是 Zuul 网关而不是各种微服务的提供者，也就是说 Zuul 会负责将消费者的请求转发给提供者。

​	当然，上面只是现阶段能力内对 Zuul 功能的理解，其实，Zuul 还具有像监控、弹性负载等高级功能。



### 这里的做法

​	在 2 中，`msa-wr-report-server`需要依赖两个微服务，一个是`msa-wr-city-data-server`来提供所需的城市数据，另一个是`msa-wr-weather-data-server`来提供对应的城市的天气数据。

​	使用 Zuul 将`msa-wr-city-data-server`和`msa-wr-weather-data-server`对外提供的接口进行聚合，聚合为`msa-wr-gateway`，使`msa-wr-report-server`直接依赖于`msa-wr-gateway`即可。

 

### 拆分出的微服务

| 微服务名称                            | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| msa-wr-city-data-server               | “获取城市数据”的微服务<br/>数据源是含“城市信息”的本地的 XML 文件 |
| msa-wr-weather-data-collection-server | “从第三方 API 获取天气数据并按照一定的时间间隔同步到本地 Redis”的微服务<br/>数据源是第三方 API |
| msa-wr-weather-data-server            | “从本地获取天气数据”的微服务<br/>数据源是本地的 Redis        |
| msa-wr-report-server                  | “天气预报”微服务<br/>与用户的交互的 Web 层                   |
| msa-wr                                | 负责管理微服务的 Server 即`Eureka Server`，上述均为`Eureka Client` |
| msa-wr-gateway                        | 聚合`msa-wr-city-data-server`和`msa-wr-weather-data-server`两个微服务对外接口，向`msa-wr-report-server`提供服务 |

​	各个微服务间的协作等见“图1.jpg”



### 各个微服务信息

| 微服务名                              | 实例端口 | 对外接口                                                     |
| ------------------------------------- | -------- | ------------------------------------------------------------ |
| msa-wr-city-data-server               | 8081     | `/weather/city`<br/>JSON<br/>`com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.JsonDataObject` |
| msa-wr-weather-data-collection-server | 8083     | 无                                                           |
| msa-wr-weather-data-server            | 8085     | `/weather/{cityKey}`<br/>JSON<br/>`com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject` |
| msa-wr-report-server                  | 8080     | `/weather/report/{cityKey}`<br/>View                         |
| msa-wr                                |          |                                                              |



### 最终效果

​	与“单体架构”是一致的。