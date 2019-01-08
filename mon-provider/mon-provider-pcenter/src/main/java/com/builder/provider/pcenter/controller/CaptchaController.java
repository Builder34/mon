package com.builder.provider.pcenter.controller;

import com.builder.common.base.constant.SecurityConstants;
import com.builder.provider.pcenter.captcha.CaptchaProcessorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码请求接口
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 16:43:29
 */
@Slf4j
@RestController
public class CaptchaController {

    @Autowired
    private CaptchaProcessorHolder captchaProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link com.builder.provider.pcenter.captcha.CaptchaProcessor}接口实现
     *
     * @param request  请求
     * @param response 响应
     * @param type     验证码类型
     *
     * @throws Exception the exception
     */
    @RequestMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+ "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception{
        captchaProcessorHolder.findCaptchaProcess(type).create(new ServletWebRequest(request, response));
    }


}
