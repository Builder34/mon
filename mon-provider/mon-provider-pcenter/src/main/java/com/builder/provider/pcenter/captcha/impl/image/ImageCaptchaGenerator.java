package com.builder.provider.pcenter.captcha.impl.image;

import com.builder.provider.pcenter.captcha.CaptchaGenerator;
import com.builder.provider.pcenter.security.properties.SecurityProperties;
import com.google.code.kaptcha.Producer;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * 图片验证码生成器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 23:00:50
 */
public class ImageCaptchaGenerator implements CaptchaGenerator {

    private SecurityProperties securityProperties;
    private Producer captchaProducer;

    @Override
    public ImageCaptchaBean generate(ServletWebRequest request) {
        String code = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(code);
        return new ImageCaptchaBean(image, code, securityProperties.getCaptcha().getImage().getExpireIn());
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }
}
