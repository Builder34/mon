package com.builder.config.server;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务中心启动类
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-22 10:52:21
 */
@EnableEncryptableProperties
@EnableConfigServer
@SpringBootApplication
public class MonConfigServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonConfigServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
