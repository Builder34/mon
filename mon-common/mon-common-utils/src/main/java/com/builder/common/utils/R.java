package com.builder.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author builder34
 * @email luocb@asiainfo.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("status", 0);
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int status, String message) {
        R r = new R();
        r.put("status", status);
        r.put("message", message);
        return r;
    }

//    public static R ok(String message) {
//        R r = new R();
//        r.put("message", message);
//        return r;
//    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }
    /**
     * 成功并返回接口数据
     * @param data data数据
     * */
    public static R ok(Object data) {
        R r = new R();
        r.put("data",data);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public R setData(Object value){
        super.put("data", value);
        return this;
    }
}

