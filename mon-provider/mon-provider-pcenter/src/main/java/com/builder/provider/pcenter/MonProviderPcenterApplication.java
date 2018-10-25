package com.builder.provider.pcenter;

import com.builder.common.datasources.config.DynamicDataSourceConfig;
import com.builder.common.datasources.config.MybatisPlusConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class, MybatisPlusConfig.class})
@EnableEncryptableProperties
@EnableTransactionManagement
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.builder")
/**
 * 权限中心服务App
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-09-14 16:20:20
 * */
public class MonProviderPcenterApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonProviderPcenterApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
