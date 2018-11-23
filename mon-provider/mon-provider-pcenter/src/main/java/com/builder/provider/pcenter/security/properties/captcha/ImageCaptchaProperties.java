package com.builder.provider.pcenter.security.properties.captcha;

import lombok.Data;

/**
 * ImageCaptchaProperties
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 23:18:31
 */
@Data
public class ImageCaptchaProperties {

    /**
     * 验证码长度
     * */
    private int length=6;
    /**
     * 过期时间(秒)
     * */
    private int expireIn=60;
    /**
     * 要拦截的url，多个url用逗号隔开，ant pattern
     * */
    private String url;
}
