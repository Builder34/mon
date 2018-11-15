package com.builder.provider.pcenter.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SecurityProperties
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-13 17:09:02
 */
@Component
@ConfigurationProperties(prefix = "mon.security")
@Data
public class SecurityProperties {

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();
}
