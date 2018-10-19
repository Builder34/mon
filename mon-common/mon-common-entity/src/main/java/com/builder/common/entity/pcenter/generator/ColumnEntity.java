package com.builder.common.entity.pcenter.generator;

import java.io.Serializable;

/**
 * @Description 列
 * @CreateTime 2018-09-13 11:33:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class ColumnEntity implements Serializable {
    private static final long serialVersionUID = -3224770022495425933L;
    /**
     * 列名
     * */
    private String columnName;
    /**
     * 列名数据类型
     * */
    private String dataType;
    /**
     * 列名备注
     * */
    private String columnComment;
    /**
     * 类属性名称(用于方法名填充Xxx，类似setXxx)
     * */
    private String attrName;
    /**
     * 类属性名称
     * */
    private String attrname;
    /**
     * 类属性数据类型
     * */
    private String attrType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * auto increment
     * */
    private String extra;

}
