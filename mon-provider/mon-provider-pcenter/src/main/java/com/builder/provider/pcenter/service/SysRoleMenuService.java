package com.builder.provider.pcenter.service;

import com.builder.provider.api.pcenter.entity.SysRoleMenuEntity;
import com.builder.common.utils.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Description SysRoleMenuService
 * @CreateTime 2018-08-23 22:36:34
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {

    /**
     * 批量插入
     * @param map {roleId:String, menuIdList:List<Long>}
     * */
    void batchInsert(Map<String, Object> map);
    /**
     * 查询权限菜单id列表
     * @param roleId 角色id
     * @return 菜单id列表
     * */
    List<Long> getMenuIdList(Long roleId);
}
