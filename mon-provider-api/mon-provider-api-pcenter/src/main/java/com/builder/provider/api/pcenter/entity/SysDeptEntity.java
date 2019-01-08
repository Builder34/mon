package com.builder.provider.api.pcenter.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 部门entity
 * @CreateTime 2018-08-23 21:23:42
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
    private static final long serialVersionUID = -8998539329034679054L;

    @TableId
    private Long deptId;
    /**
     * 上级部门ID，一级部门为0
     * */
    private Long parentId;
    private String parentName;
    /**
     * 部门名称
     * */
    private String name;
    /**
     * 排序号，小的排在前面
     * */
    private Integer orderNum;
    /**
     * 是否删除  0：禁用  1：正常
     * */
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer createUserId;
    private Integer updateUserId;
    @TableField(exist=false)
    private String createUserName;
    @TableField(exist=false)
    private String updateUserName;
    @TableField(exist = false)
    private List<SysDeptEntity> children;
    /**
     * 标记该节点所在树的层级,从0开始
     * */
    private Integer layer;

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public List<SysDeptEntity> getChildren() {
        return children;
    }

    public void setChildren(List<SysDeptEntity> children) {
        this.children = children;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
