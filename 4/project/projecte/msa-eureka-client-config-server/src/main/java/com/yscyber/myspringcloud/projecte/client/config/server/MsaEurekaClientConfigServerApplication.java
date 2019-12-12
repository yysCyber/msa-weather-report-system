package com.yscyber.myspringcloud.projecte.client.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Eureka Client
 *
 * Config Server
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class MsaEurekaClientConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaEurekaClientConfigServerApplication.class, args);
    }

}