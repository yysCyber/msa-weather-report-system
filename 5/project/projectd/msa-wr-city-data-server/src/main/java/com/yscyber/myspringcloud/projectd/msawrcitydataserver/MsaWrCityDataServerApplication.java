package com.yscyber.myspringcloud.projectd.msawrcitydataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * “获取城市数据”的微服务
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MsaWrCityDataServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaWrCityDataServerApplication.class, args);
    }

}