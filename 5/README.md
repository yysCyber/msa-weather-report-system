# README



### 概述

​	在 3 的“微服务架构”中，使用 Hystrix 来实现熔断机制。



### 相关介绍

​	Hystrix 是 Spring Cloud 的重要组件。

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

​	Hystrix 在 Spring Cloud 中提供重要的熔断机制，在微服务架构中，各个微服务之间存在着复杂的联系，一旦其中一个微服务出现了问题，例如“停止”等，极有可能会影响整个系统的问题，这个时候就需要“熔断”机制来进行暂时的处理。

​	“熔断”和“断路器”这两个概念实际上是源自电力，大家都知道一旦用电负荷过大，会烧断保险丝或跳闸，导致断电，进而起到保护电路的作用。

​	Spring Cloud 中使用 Hystrix，一旦某个微服务因为请求量过大或其他的原因（比如“停止”）而导致调用失败，Hystrix 会统计请求失败的次数，而失败的次数超过的规定的阈值时，Hystrix 便会启动熔断机制来尽可能确保系统整体的稳定性、告知外界不再继续请求以达到保护微服务的作用。

​	Hystrix 在熔断之后，并不是就简简单单地“熔断”。我们知道，调用微服务从代码上的体现就是调用接口中的方法，而这些这个接口以及其中的方法是为正常执行的情况来准备的，但是一旦出现异常，这些方法一定不会正常执行，所以 Hystrix 会通过配置来让开发者编写一些一样的方法，只不过这些方法是“熔断”后去调用的，里面的逻辑都是应对异常时的逻辑。



### 这里的做法

​	在`msa-wr-report-server`中，引入 Hystrix 。

​	在`msa-wr-report-server`中会通过`msa-wr-gateway`的 Zuul 来间接请求`msa-wr-city-data-server`和`msa-wr-weather-data-server`这两个微服务，使用 Hystrix 来对这两个微服务进行保护。同时，编写一个类`MsaDataServerClientFallback`，这个类会实现`MsaDataServerClient`这一微服务聚合接口，实现接口中的方法，当“熔断”发生后**暂时替代**正常接口中的方法执行。

```java
package com.yscyber.myspringcloud.projectd.msawrreportserver.client;

import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.WeatherJsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 聚合 API
 *
 * 使用 Zuul 作为 API 网关
 *
 * @author Yuan
 */
@FeignClient(name = "msa-wr-gateway", fallback = MsaDataServerClientFallback.class)
public interface MsaDataServerClient {

    @GetMapping("/json/city/weather/city")
    JsonDataObject<City> jsonCity();

    @GetMapping("/json/weather/weather/{key}")
    WeatherJsonObject jsonWeatherByCityKey(@PathVariable("key") String cityKey);

}
```

```java
package com.yscyber.myspringcloud.projectd.msawrreportserver.client;

import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.WeatherJsonObject;
import org.springframework.stereotype.Component;

/**
 * MsaDataServerClient Fallback
 *
 * Hystrix 实现在 Spring Cloud 中实现“断路器”的机制
 *
 * 1、当 msa-wr-city-data-server 微服务无法正常提供服务时，会自动调用这里的 jsonCity 方法而不再去调用 MsaDataServerClient 中的 jsonCity 方法
 * 2、同样，当 msa-wr-weather-data-server 微服务无法正常提供服务时，会自动调用这里的 jsonWeatherByCityKey 方法而不去调用 MsaDataServerClient 中的 jsonWeatherByCityKey 方法
 *
 * @author Yuan
 */
@Component
public class MsaDataServerClientFallback implements MsaDataServerClient {

    /**
     * 返回 null 即可
     *
     * @return null
     */
    @Override
    public JsonDataObject<City> jsonCity() {
        return null;
    }

    /**
     * 返回 null 即可
     *
     * @param cityKey 城市编号
     * @return null
     */
    @Override
    public WeatherJsonObject jsonWeatherByCityKey(String cityKey) {
        return null;
    }

}
```

​	然后，根据情况去修改与 Web 有关的 Controller、页面渲染等来达到较为友好的交互效果。这里的做法是，当出现“熔断”后返回“error.html”这一错误页面。



### 拆分出的微服务

| 微服务名称                            | 说明                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| msa-wr-city-data-server               | “获取城市数据”的微服务<br/>数据源是含“城市信息”的本地的 XML 文件 |
| msa-wr-weather-data-collection-server | “从第三方 API 获取天气数据并按照一定的时间间隔同步到本地 Redis”的微服务<br/>数据源是第三方 API |
| msa-wr-weather-data-server            | “从本地获取天气数据”的微服务<br/>数据源是本地的 Redis        |
| msa-wr-report-server                  | “天气预报”微服务<br/>与用户的交互的 Web 层                   |
| msa-wr                                | 负责管理微服务的 Server 即`Eureka Server`，上述均为`Eureka Client` |
| msa-wr-gateway                        | 聚合`msa-wr-city-data-server`和`msa-wr-weather-data-server`两个微服务对外接口，向`msa-wr-report-server`提供服务 |



### 各个微服务信息

| 微服务名                              | 实例端口/性质                    | 对外接口                                                     |
| ------------------------------------- | -------------------------------- | ------------------------------------------------------------ |
| msa-wr-city-data-server               | 8081<br/>Eureka Client           | `/weather/city`<br/>JSON<br/>`com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.JsonDataObject` |
| msa-wr-weather-data-collection-server | 8083<br/>Eureka Client           | 无                                                           |
| msa-wr-weather-data-server            | 8085<br/>Eureka Client           | `/weather/{cityKey}`<br/>JSON<br/>`com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject` |
| msa-wr-report-server                  | 8080<br/>Eureka Client           | `/weather/report/{cityKey}`<br/>View                         |
| msa-wr                                | 8761<br/>Eureka Server           | `http://localhost:8761/eureka`                               |
| msa-wr-gateway                        | 8087<br>Eureka Client & API 网关 | API 聚合<br/>请求转发规则：`/json/city/**`转发给`msa-wr-city-data-server`微服务，获取“城市”数据的“请求路径”即变为`/json/city/weather/city`（原“请求路径”加上“请求转发规则”）；`/json/weather/**`转发给`msa-wr-weather-data-server`微服务，获取“对应城市的天气”数据的请求数据即变为`/json/weather/weather/{cityKey}`（道理同上） |



### 最终效果

​	与“单体架构”是一致的。