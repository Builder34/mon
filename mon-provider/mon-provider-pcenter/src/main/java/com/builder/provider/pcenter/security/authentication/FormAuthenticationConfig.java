package com.builder.provider.pcenter.security.authentication;

import com.builder.common.base.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * FormAuthenticationConfig 表单登录认证配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 15:55:59
 */
@Component
public class FormAuthenticationConfig {


    protected final AuthenticationSuccessHandler monAuthenticationSuccessHandler;
    protected final AuthenticationFailureHandler monAuthenticationFailureHandler;

    @Autowired
    public FormAuthenticationConfig(AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler) {
        this.monAuthenticationSuccessHandler = authenticationSuccessHandler;
        this.monAuthenticationFailureHandler = authenticationFailureHandler;
    }

    /**
     * Configure.
     *
     * @param http the http
     *
     * @throws Exception the exception
     */
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .usernameParameter(SecurityConstants.DEFAULT_SIGN_IN_FORM_PARAM_USERNAME)
                .successHandler(monAuthenticationSuccessHandler)
                .failureHandler(monAuthenticationFailureHandler);
    }

}
