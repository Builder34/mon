package com.builder.common.utils.exception;

/**
 * @Description 自定义异常
 * @CreateTime 2018-08-04 vue-2.5.17:57:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class MBException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public MBException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MBException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MBException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MBException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}