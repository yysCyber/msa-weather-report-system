package com.yscyber.myspringcloud.projectd.msawrweatherdataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * “从本地获取天气数据”微服务
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MsaWrWeatherDataServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaWrWeatherDataServerApplication.class, args);
    }

}