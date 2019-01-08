package com.builder.provider.pcenter.captcha.impl;

import com.builder.common.base.utils.RequestBaseUtils;
import com.builder.common.core.security.exception.CaptchaException;
import com.builder.common.core.util.JacksonUtil;
import com.builder.common.core.util.RedisClientUtils;
import com.builder.provider.pcenter.captcha.CaptchaBean;
import com.builder.provider.pcenter.captcha.CaptchaRepository;
import com.builder.provider.pcenter.captcha.CaptchaType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 基于redis的验证码存取器，解决微服务建无session存取验证码的问题
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 09:37:52
 */
@Component
@Slf4j
public class RedisCaptchaRepository implements CaptchaRepository {
    private final RedisClientUtils redisClientUtils;
    @Autowired
    public RedisCaptchaRepository(RedisClientUtils redisClientUtils) {
        this.redisClientUtils = redisClientUtils;
    }

    @Override
    public void save(ServletWebRequest request, CaptchaBean captchaBean, CaptchaType captchaType) {
        String redisKey = generateKey(request, captchaType);
        redisClientUtils.set(redisKey, captchaBean, captchaBean.getExpireIn());
    }

    @Override
    public void remove(ServletWebRequest request, CaptchaType captchaType) {
        redisClientUtils.delete(generateKey(request, captchaType));
    }

    @Override
    public CaptchaBean get(ServletWebRequest request, CaptchaType captchaType) {
        String beanJson = redisClientUtils.get(generateKey(request, captchaType));
        if(StringUtils.isNotBlank(beanJson)){
            return JacksonUtil.decode2(beanJson, CaptchaBean.class);
        }
        return null;
    }

    private String generateKey(ServletWebRequest request, CaptchaType type) {
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)) {
            throw new CaptchaException("请在请求头header中携带deviceId参数,值是设备id");
        }
        StringBuilder builder = new StringBuilder(RequestBaseUtils.getRemoteAddr(request.getRequest()));
        builder.append("_captcha:")
                .append(type.getParamNameOnValidate().toLowerCase())
                .append(deviceId);
        log.info("==> 验证码的key为: {}", builder.toString());
        return builder.toString();
    }
}
