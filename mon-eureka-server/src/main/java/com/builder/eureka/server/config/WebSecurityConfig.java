package com.builder.eureka.server.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * eureka server安全验证的配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-28 16:49:02
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 默认情况下，当Spring Security位于类路径上时，它将要求每次向应用程序发送请求时都会发送一个有效的CSRF令牌。
     * Eureka客户端通常不会拥有有效的跨站点请求伪造（CSRF）令牌，您需要为/eureka/**端点禁用此要求
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
