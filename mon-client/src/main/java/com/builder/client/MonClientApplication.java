package com.builder.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
/**
 * @author Builder34
 * */
public class MonClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonClientApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
