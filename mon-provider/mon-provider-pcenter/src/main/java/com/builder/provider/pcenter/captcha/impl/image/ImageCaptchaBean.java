package com.builder.provider.pcenter.captcha.impl.image;

import com.builder.provider.pcenter.captcha.CaptchaBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;

/**
 * ImageCaptchaBean 图片验证码
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 11:52:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCaptchaBean extends CaptchaBean {

    private static final long serialVersionUID = 8851553095917162695L;

    /**
     * 验证码图片
     * */
    private BufferedImage image;

    public ImageCaptchaBean(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}
