package com.builder.provider.pcenter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * 解决前端跨域问题
 * 参考： http://www.jianshu.com/p/4ef9881090ec
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2018-07-20 18:33:02
 */
@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);

        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        // 预检请求的有效期，单位为秒。
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**",config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        //这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;

    }
}
