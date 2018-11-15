package com.builder.provider.pcenter.security.properties;

import lombok.Data;

/**
 * OAuth2Properties
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-13 17:16:15
 */
@Data
public class OAuth2Properties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "mon";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
