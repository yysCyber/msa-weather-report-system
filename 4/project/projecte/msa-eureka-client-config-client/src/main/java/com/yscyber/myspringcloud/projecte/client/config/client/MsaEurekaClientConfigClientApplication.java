package com.yscyber.myspringcloud.projecte.client.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Eureka Client
 *
 * Config Client
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MsaEurekaClientConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaEurekaClientConfigClientApplication.class, args);
    }

}