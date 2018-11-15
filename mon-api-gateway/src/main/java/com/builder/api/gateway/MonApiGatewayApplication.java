package com.builder.api.gateway;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
 * 注解@EnableDiscoveryClient与下面的@EnableEurekaClient作用一样,
 * 如注册中心[eureka、consul、zookeeper等]是eureka则建议使用@EnableEurekaClient
 */
/**
 * api网关Application
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-23 09:20:56
 */
@EnableOAuth2Sso
@EnableZuulProxy
@EnableHystrix
@EnableDiscoveryClient
@EnableSwaggerButler
@SpringBootApplication
public class MonApiGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonApiGatewayApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
