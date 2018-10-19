package com.builder.common.entity.pcenter;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.builder.common.utils.validator.group.AddGroup;
import com.builder.common.utils.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 角色与菜单对应关系entity
 * @CreateTime 2018-08-23 21:24:18
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 4728112665596644978L;

    @TableId
    private Long id;
    @NotBlank(message = "角色id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long roleId;
    @NotBlank(message = "菜单id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long menuId;
    private Date updateTime;
    private Integer updateUserId;
    @TableField(exist=false)
    private String updateUserName;

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }


}
