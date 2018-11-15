package com.builder.provider.api.pcenter.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户与角色对应关系entity
 * @CreateTime 2018-08-23 21:24:18
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = -8721454256707996363L;

    @TableId
    private Long id;
    //@NotBlank(message = "用户id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long userId;
    //@NotBlank(message = "角色id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long roleId;
    private Date createTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
