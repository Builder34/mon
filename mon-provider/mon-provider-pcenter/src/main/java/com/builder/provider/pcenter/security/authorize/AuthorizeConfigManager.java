package com.builder.provider.pcenter.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 *  AuthorizeConfigManager 授权信息管理器
 *  用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 15:29:21
 */
public interface AuthorizeConfigManager {

    /**
     * 加载配置
     * @param config 配置
     * */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
