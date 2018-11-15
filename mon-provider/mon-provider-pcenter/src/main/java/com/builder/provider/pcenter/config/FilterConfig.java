package com.builder.provider.pcenter.config;

import com.builder.common.utils.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * @Description Filter配置
 * @CreateTime 2018-08-16 14:45:26
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean xssFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);

        return registration;
    }
}
