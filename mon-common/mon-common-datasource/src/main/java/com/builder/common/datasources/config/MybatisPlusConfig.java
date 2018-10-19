package com.builder.common.datasources.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description MybatisPlus配置
 * @CreateTime 2018-08-14 22:26:37
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
