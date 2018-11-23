package com.builder.provider.pcenter.security;

import com.builder.provider.pcenter.captcha.config.CaptchaSecurityConfig;
import com.builder.provider.pcenter.security.authentication.FormAuthenticationConfig;
import com.builder.provider.pcenter.security.authorize.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * WebResourceServerConfig 资源服务器配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 15:06:18
 */
@Configuration
@EnableResourceServer
public class WebResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler monSecurityExpressionHandler;
    @Autowired
    private AccessDeniedHandler webAccessDeniedHandler;
    @Autowired
    protected AuthenticationSuccessHandler monAuthenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler monAuthenticationFailureHandler;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    @Autowired
    private CaptchaSecurityConfig captchaSecurityConfig;

//    @Resource
//    private DataSource dataSource;
//    /**
//     * 记住我功能的token存取器配置
//     *
//     * @return the persistent token repository
//     */
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        return tokenRepository;
//    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(monSecurityExpressionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置表单认证
        formAuthenticationConfig.configure(http);

        http.headers().frameOptions().disable();
        http.apply(captchaSecurityConfig)
                .and()
                .headers().frameOptions()
                .and()
                .exceptionHandling().accessDeniedHandler(webAccessDeniedHandler)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
