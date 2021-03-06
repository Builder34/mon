package com.builder.eureka.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author Builder34
 * */
@SpringBootApplication
@EnableEurekaServer
public class MonEurekaServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonEurekaServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
