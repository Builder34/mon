package com.builder.provider.pcenter.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 22:49:08
 */
public interface CaptchaGenerator {
    /**
     * 生成验证码
     * @param request 请求
     * @return CaptchaBean 验证码bean
     * */
    CaptchaBean generate(ServletWebRequest request);
}
