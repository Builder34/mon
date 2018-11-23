package com.builder.common.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * CaptchaException 验证码异常类
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 09:18:45
 */
public class CaptchaException extends AuthenticationException {
    private static final long serialVersionUID = -3604624423241648191L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
