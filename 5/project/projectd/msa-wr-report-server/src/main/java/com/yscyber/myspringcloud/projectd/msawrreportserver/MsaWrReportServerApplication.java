package com.yscyber.myspringcloud.projectd.msawrreportserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * “天气预报 Web”微服务
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class MsaWrReportServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaWrReportServerApplication.class, args);
    }

}