package com.builder.provider.pcenter.captcha.config;


import com.builder.provider.pcenter.captcha.CaptchaGenerator;
import com.builder.provider.pcenter.captcha.impl.image.ImageCaptchaGenerator;
import com.builder.provider.pcenter.security.properties.SecurityProperties;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码主要配置
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 14:40:26
 */
@Configuration
public class CaptchaBeanConfig {

    @Autowired
    private Producer captchaProducer;
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 验证码生成器
     * @return 图片验证码生成器
     * */
    @Bean
    @ConditionalOnMissingBean(name = "imageCaptchaGenerator")
    public CaptchaGenerator imageCaptchaGenerator() {
        ImageCaptchaGenerator imageCaptchaGenerator = new ImageCaptchaGenerator();
        imageCaptchaGenerator.setCaptchaProducer(captchaProducer);
        imageCaptchaGenerator.setSecurityProperties(securityProperties);
        return imageCaptchaGenerator;
    }
}
