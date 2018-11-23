package com.builder.provider.pcenter.captcha;

import com.builder.common.core.security.exception.CaptchaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码生成器管理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 14:14:19
 */
@Component
public class CaptchaProcessorHolder {
    private final Map<String, CaptchaProcessor> processorMap;

    @Autowired
    public CaptchaProcessorHolder(Map<String, CaptchaProcessor> processorMap) {
        this.processorMap = processorMap;
    }
    /**
     * 寻找对应类型的验证码生成器
     * @param type 验证码类型
     * */
    public CaptchaProcessor findCaptchaProcess(String type) {
        String name = type.toLowerCase() + CaptchaProcessor.class.getSimpleName();
        CaptchaProcessor processor = processorMap.get(name);
        if(processor == null) {
            throw new CaptchaException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
    /**
     * 寻找对应类型的验证码生成器
     * @param type 验证码类型没枚举类
     * */
    public CaptchaProcessor findCaptchaProcess(CaptchaType type) {
        return findCaptchaProcess(type.getParamNameOnValidate());
    }
}
