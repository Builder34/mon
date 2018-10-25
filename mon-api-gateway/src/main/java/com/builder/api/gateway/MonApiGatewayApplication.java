package com.builder.api.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * api网关Application
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-23 09:20:56
 */
@EnableZuulProxy
@SpringCloudApplication
public class MonApiGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonApiGatewayApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
