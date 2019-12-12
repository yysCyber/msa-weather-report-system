package com.yscyber.myspringcloud.projecte.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server
 *
 * @author Yuan
 */
@SpringBootApplication
@EnableEurekaServer
public class MsaEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaEurekaServerApplication.class, args);
    }

}