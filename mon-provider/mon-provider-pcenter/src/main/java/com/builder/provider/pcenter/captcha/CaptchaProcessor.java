package com.builder.provider.pcenter.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 23:30:12
 */
public interface CaptchaProcessor {
    /**
     * 创建校验码
     *
     * @param request the request
     *
     * @throws Exception the exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码(验证后删除)
     *
     * @param servletWebRequest the servlet web request
     */
    void validate(ServletWebRequest servletWebRequest);

    /**
     * 校验验证码(验证后不删除)
     *
     * @param servletWebRequest the servlet web request
     */
    void check(ServletWebRequest servletWebRequest);
}
