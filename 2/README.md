# README



### 概述

​	将 1 中的“单体架构”改为“微服务架构”，使用 Spring Cloud 。



### 拆分出的微服务

| 微服务名称                            | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| msa-wr-city-data-server               | “获取城市数据”的微服务<br/>数据源是含“城市信息”的本地的 XML 文件 |
| msa-wr-weather-data-collection-server | “从第三方 API 获取天气数据并按照一定的时间间隔同步到本地 Redis”的微服务<br/>数据源是第三方 API |
| msa-wr-weather-data-server            | “从本地获取天气数据”的微服务<br/>数据源是本地的 Redis        |
| msa-wr-report-server                  | “天气预报”微服务<br/>与用户的交互的 Web 层                   |
| msa-wr                                | 负责管理微服务的 Server 即`Eureka Server`，上述均为`Eureka Client` |

​	各个微服务间的协作等见“图1.jpg”



### 各个微服务信息

| 微服务名                              | 实例端口 | 对外接口                                                     |
| ------------------------------------- | -------- | ------------------------------------------------------------ |
| msa-wr-city-data-server               | 8081     | `/weather/city`<br/>JSON<br/>`com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.JsonDataObject` |
| msa-wr-weather-data-collection-server | 8083     | 无                                                           |
| msa-wr-weather-data-server            | 8085     | `/weather/{cityKey}`<br/>JSON<br/>`com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject` |
| msa-wr-report-server                  | 8080     | `/weather/report/{cityKey}`<br/>View                         |



### 最终效果

​	与“单体架构”是一致的。