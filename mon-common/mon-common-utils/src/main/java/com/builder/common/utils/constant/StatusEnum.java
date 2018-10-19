package com.builder.common.utils.constant;

/**
 * @Description 状态枚举类
 * @CreateTime 2018-08-21 10:19:23
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public enum  StatusEnum {
    /**
     * 验证码不正确
     * */
    CAPTCHA_ERR(315,"验证码不正确"),
    /**
     * 参数缺失
     * */
    PARAM_MISS(316, "参数缺失");

    private int status;
    private String message;
    StatusEnum(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
