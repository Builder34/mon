package com.builder.provider.api.pcenter.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 角色entity
 * @CreateTime 2018-08-23 21:24:00
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = 7452604362231286854L;

    @TableId
    private Long roleId;
    /**
     * 角色名称
     * */
    private String roleName;
    /**
     * 备注
     * */
    private String remark;
    /**
     * 部门id
     * */
    private Long deptId;
    /**
     * 创建时间
     * */
    private Date createTime;
    /**
     * 更新时间
     * */
    private Date updateTime;
    private Integer createUserId;
    private Integer updateUserId;
    @TableField(exist=false)
    private String createUserName;
    @TableField(exist=false)
    private String updateUserName;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
}
