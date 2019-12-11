package com.yscyber.myspringcloud.projectd.msawr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Yuan
 */
@SpringBootApplication
@EnableEurekaServer
public class MsaWrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaWrApplication.class, args);
    }

}