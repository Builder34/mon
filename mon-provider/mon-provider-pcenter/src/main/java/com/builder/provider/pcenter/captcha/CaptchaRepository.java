package com.builder.provider.pcenter.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * CaptchaRepository 验证码存取器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 08:40:08
 */
public interface CaptchaRepository {

    /**
     * 保存验证码
     * @param request 请求
     * @param captchaBean 验证码bean
     * @param captchaType 验证码类型
     * */
    void save(ServletWebRequest request, CaptchaBean captchaBean, CaptchaType captchaType);
    /**
     * 移除验证码
     * @param request 请求
     * @param captchaType 验证码类型
     * */
    void remove(ServletWebRequest request, CaptchaType captchaType);
    /**
     * 获取验证码
     * @param request 请求
     * @param captchaType 验证码类型
     * @return 验证码bean
     * */
    CaptchaBean get(ServletWebRequest request, CaptchaType captchaType);
}
