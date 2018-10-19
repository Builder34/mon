package com.builder.common.utils.generator;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 表
 * @CreateTime 2018-09-13 11:34:25
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class TableEntity implements Serializable {

    private static final long serialVersionUID = -3587962409214580538L;

    /**
     * 表名
     * */
    private String tableName;
    /**
     * 表备注
     * */
    private String tableComment;
    /**
     * 主键列
     * */
    private ColumnEntity pk;
    /**
     * 表的列(不包含主键)
     */
    private List<ColumnEntity> columns;
    /**
     * 类名(首字母大写，下划线转驼峰格式)
     * */
    private String className;
    /**
     * 类引用名(首字母小写，下划线转驼峰格式)
     * */
    private String classname;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public ColumnEntity getPk() {
        return pk;
    }

    public void setPk(ColumnEntity pk) {
        this.pk = pk;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
