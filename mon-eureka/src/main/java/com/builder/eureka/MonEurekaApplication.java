package com.builder.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
/**
 * @author Builder34
 * */
public class MonEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonEurekaApplication.class, args);
    }
}
