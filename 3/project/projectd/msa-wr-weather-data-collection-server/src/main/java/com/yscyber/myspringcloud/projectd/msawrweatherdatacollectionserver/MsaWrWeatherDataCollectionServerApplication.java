package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * “从网络获取天气数据并将天气数据同步至本地”的微服务
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsaWrWeatherDataCollectionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaWrWeatherDataCollectionServerApplication.class, args);
    }

}