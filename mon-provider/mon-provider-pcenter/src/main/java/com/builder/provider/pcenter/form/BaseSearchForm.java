package com.builder.provider.pcenter.form;

import java.io.Serializable;

/**
 * @Description 基础搜索条件form
 * @CreateTime 2018-09-05 15:05:39
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class BaseSearchForm implements Serializable {
    private static final long serialVersionUID = -8288324341754431962L;

    /**
     * 当前页码
     * */
    private int current = 1;
    /**
     * 每页大小
     * */
    private int size = 10;
    /**
     * 关键字
     * */
    private String kwd;
    /**
     * 排序字段
     * */
    private String orderByField;
    /**
     * 是否正序, 默认false(即是desc)
     * */
    private boolean isAsc = false;



    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getKwd() {
        return kwd;
    }

    public void setKwd(String kwd) {
        this.kwd = kwd;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }
}
