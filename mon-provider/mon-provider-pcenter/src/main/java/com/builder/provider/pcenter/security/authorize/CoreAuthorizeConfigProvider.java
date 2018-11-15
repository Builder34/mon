package com.builder.provider.pcenter.security.authorize;

import com.builder.common.base.constant.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * CoreAuthorizeConfigProvider 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 15:36:25
 */
@Component
@Order(Integer.MIN_VALUE)
public class CoreAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*", "/pay/alipayCallback",
                "/druid/**", "/auth/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs").permitAll();
        return false;
    }
}
