package com.builder.provider.pcenter.security.properties.captcha;

import lombok.Data;

/**
 * 验证码配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 23:17:12
 */
@Data
public class CaptchaProperties {

    /**
     * 图片验证码配置
     * */
    private ImageCaptchaProperties image = new ImageCaptchaProperties();
}
