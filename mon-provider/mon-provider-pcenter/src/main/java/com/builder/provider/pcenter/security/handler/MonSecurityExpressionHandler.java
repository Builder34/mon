package com.builder.provider.pcenter.security.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * WebSecurityExpressionHandler
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 13:47:59
 */
@Configuration
public class MonSecurityExpressionHandler extends OAuth2WebSecurityExpressionHandler {

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler handler = new OAuth2WebSecurityExpressionHandler();
        handler.setApplicationContext(applicationContext);
        return handler;
    }
}
