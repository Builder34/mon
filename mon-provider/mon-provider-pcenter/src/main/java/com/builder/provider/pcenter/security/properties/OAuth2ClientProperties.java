package com.builder.provider.pcenter.security.properties;

import lombok.Data;

/**
 * 认证服务器注册的第三方应用配置项
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-13 17:17:41
 */
@Data
public class OAuth2ClientProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     * 默认值 2小时
     */
    private int accessTokenValidateSeconds = 7200;

    /**
     * token刷新时间
     * 默认值 30天
     * */
    private int refreshTokenValiditySeconds = 2592000;

    private String scope;
}
