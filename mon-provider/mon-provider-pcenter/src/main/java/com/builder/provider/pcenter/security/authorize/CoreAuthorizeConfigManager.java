package com.builder.provider.pcenter.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * WebAuthorizeConfigManager
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 15:32:26
 */
@Component
public class CoreAuthorizeConfigManager implements AuthorizeConfigManager {
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Autowired
    public CoreAuthorizeConfigManager(List<AuthorizeConfigProvider> providers) {
        this.authorizeConfigProviders = providers;
    }

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName = null;
        for (AuthorizeConfigProvider provider : authorizeConfigProviders) {
            boolean currentIsAnyRequestConfig = provider.config(config);
            if(existAnyRequestConfig && currentIsAnyRequestConfig) {
                throw new RuntimeException("重复的anyRequest配置:" + existAnyRequestConfigName + ","
                        + provider.getClass().getSimpleName());
            }else if(currentIsAnyRequestConfig) {
                existAnyRequestConfig = true;
                existAnyRequestConfigName = provider.getClass().getSimpleName();
            }
        }
        if(!existAnyRequestConfig) {
            config.anyRequest().authenticated();
        }
    }
}
