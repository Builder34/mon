package com.builder.common.base.enums;

/**
 * MonErrorCodeEnum 错误码枚举类
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-12 10:52:26
 */
public enum MonErrorCodeEnum {

    //全局
    GL999110(999110, "参数异常"),

    //权限中心
    PCENTER100400(100400, "页面已过期,请重新登录"),
    PCENTER100401(999401, "无权限访问"),
    PCENTER100410(100410, "token解析异常"),
    PCENTER100411(100411, "验证token失败")

    //订单中心

    //商品中心

    ;




    private int code;
    private String msg;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }
    MonErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    /**
     * @return MonErrorCodeEnum
     * */
    public static MonErrorCodeEnum getEnum(int code) {
        for (MonErrorCodeEnum ele : MonErrorCodeEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}
