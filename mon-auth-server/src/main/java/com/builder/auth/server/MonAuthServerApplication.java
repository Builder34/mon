package com.builder.auth.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@EnableDiscoveryClient
@SpringCloudApplication
/**
 * mon授权认证服务中心Application
 * @author Builder34
 * */
public class MonAuthServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonAuthServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
