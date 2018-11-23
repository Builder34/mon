package com.builder.provider.pcenter.captcha;

import com.builder.common.base.constant.SecurityConstants;

/**
 * CaptchaType  验证码类型
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 11:12:58
 */
public enum  CaptchaType {
    /**
     * 图片验证码
     * */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CAPTCHA_IMAGE;
        }
    },
    /**
     * 短信验证码
     * */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CAPTCHA_SMS;
        }
    },
    /**
     * 邮箱验证码
     * */
    EMAIL{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CAPTCHA_EMAIL;
        }
    }
    ;

    /**
     * 校验时从请求中获取的参数的名字
     *
     * @return param name on validate
     */
    public abstract String getParamNameOnValidate();
}
