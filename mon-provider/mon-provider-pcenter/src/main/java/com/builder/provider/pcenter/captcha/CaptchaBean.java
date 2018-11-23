package com.builder.provider.pcenter.captcha;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * CaptchaBean 验证码bean
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 11:14:26
 */
@Data
public class CaptchaBean implements Serializable {
    private static final long serialVersionUID = 1060672039210614122L;
    /**
     * 验证码值
     * */
    private String code;
    /**
     * 验证码类型
     * */
    private String type;
    /**
     * 几秒后过期
     * */
    private int expireIn;

    /**
     * 过期时间
     * */
    private LocalDateTime expireTime;

    public CaptchaBean() {

    }
    /**
     * 验证码构造方法
     * @param code 验证码值
     * @param expireIn 几秒后过期
     * */
    public CaptchaBean(String code, int expireIn) {
        this.code = code;
        this.expireIn = expireIn;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断验证码是否过期
     * */
    @JsonIgnore
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type",type)
                .append("code", code)
                .append("expireTime", expireTime)
                .toString();
    }
}
