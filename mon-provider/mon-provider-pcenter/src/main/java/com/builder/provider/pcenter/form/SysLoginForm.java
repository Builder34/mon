package com.builder.provider.pcenter.form;

import java.io.Serializable;

/**
 * @Description SysLoginForm
 * @CreateTime 2018-08-13 10:47:10
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class SysLoginForm implements Serializable {
    private static final long serialVersionUID = 4778638242503406133L;
    private String userName;
    private String password;
    private String captcha;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
