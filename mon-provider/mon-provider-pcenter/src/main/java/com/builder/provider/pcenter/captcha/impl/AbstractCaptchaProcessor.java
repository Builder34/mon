package com.builder.provider.pcenter.captcha.impl;

import com.builder.common.core.security.exception.CaptchaException;
import com.builder.provider.pcenter.captcha.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * 抽象的验证码处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 08:36:39
 */
@Slf4j
public abstract class AbstractCaptchaProcessor<C extends CaptchaBean> implements CaptchaProcessor {

    /**
     * 收集系统中{@link CaptchaGenerator}接口所有的实现
     * */
    private final Map<String, CaptchaGenerator> captchaGeneratorMap;

    private final CaptchaRepository captchaRepository;

    @Autowired
    public AbstractCaptchaProcessor(Map<String, CaptchaGenerator> generatorMap, CaptchaRepository captchaRepository) {
        this.captchaGeneratorMap = generatorMap;
        this.captchaRepository = captchaRepository;
    }

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C captcha = generate(request);
        save(request, captcha);
        send(request, captcha);
    }

    @Override
    public void validate(ServletWebRequest request) {
        CaptchaType type = getCaptchaType();
        check(request);
        captchaRepository.remove(request, type);
    }

    @Override
    public void check(ServletWebRequest request) {
        CaptchaType type = getCaptchaType();
        //保存在redis的验证码bean
        C captchaBeanInRedis;
        //请求参数中的验证码值
        String captchaCodeInRequest;
        try {
            captchaBeanInRedis = (C)captchaRepository.get(request, type);
            captchaCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), type.getParamNameOnValidate()+"Captcha");
        } catch (Exception e) {
            log.error("==> get captcha form redis, error: {}",e.getMessage(), e);
            throw new CaptchaException("获取会话中的验证码值失败");
        }
        if(StringUtils.isBlank(captchaCodeInRequest)) {
            throw new CaptchaException("验证码的值不能为空");
        }
        if(captchaBeanInRedis == null || captchaBeanInRedis.isExpire()) {
            captchaRepository.remove(request, type);
            throw new CaptchaException("验证码已过期");
        }
        if(!StringUtils.equals(captchaBeanInRedis.getCode(), captchaCodeInRequest)) {
            throw new CaptchaException("输入的验证码不正确");
        }
    }
    private CaptchaType getCaptchaType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CaptchaProcessor");
        return CaptchaType.valueOf(type.toUpperCase());
    }
    /**
     * 生成验证码
     * */
    private C generate(ServletWebRequest request) {
        String type = getCaptchaType().getParamNameOnValidate().toLowerCase();
        String generatorName = type + CaptchaGenerator.class.getSimpleName();
        CaptchaGenerator captchaGenerator = captchaGeneratorMap.get(generatorName);
        if(captchaGenerator == null) {
            throw new CaptchaException("验证码生成器"+generatorName+"不存在");
        }
        return (C) captchaGenerator.generate(request);
    }
    /**
     * 保存验证码
     * */
    private void save(ServletWebRequest request, C captcha) {
        CaptchaBean bean = new CaptchaBean(captcha.getCode(), captcha.getExpireIn());
        captchaRepository.save(request, bean, getCaptchaType());
    }
    /**
     * 发送验证码，由子类实现
     * */
    protected abstract void send(ServletWebRequest request, C captchaBean) throws IOException;
}
