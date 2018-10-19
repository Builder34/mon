package com.builder.provider.pcenter;

import com.builder.common.datasources.config.DynamicDataSourceConfig;
import com.builder.common.datasources.config.MybatisPlusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class, MybatisPlusConfig.class})
@EnableTransactionManagement
@ServletComponentScan
@EnableEurekaClient
/**
 * 权限中心服务App
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-09-14 16:20:20
 * */
public class MonProviderPcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonProviderPcenterApplication.class, args);
    }
}
