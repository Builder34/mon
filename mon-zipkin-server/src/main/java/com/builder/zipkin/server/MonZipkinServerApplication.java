package com.builder.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * zipkin分布式服务追踪Application
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-25 15:19:47
 */
@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class MonZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonZipkinServerApplication.class, args);
    }
}
