package com.builder.provider.pcenter.service;

import com.builder.common.entity.pcenter.SysMenuEntity;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Description 系统菜单service
 * @CreateTime 2018-08-20 17:36:34
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

    /**
     * 获取顶层导航栏菜单
     * @param userId 用户id
     * @return 菜单列表
     * */
    List<SysMenuEntity> getNavMenuListByUserId(Long userId);

    Long getMenuIdByName(String menuName);

    /**
     * 获取左侧菜单列表
     * @param userId 系统用户id
     * @param navMenuId 顶层菜单id
     * @return 菜单列表
     * */
    List<SysMenuEntity> getMenuList(Long userId, Long navMenuId);


    /**
     * 获取全部菜单数据，组合成树
     * */
    List<SysMenuEntity> getTreeList();
    /**
     * 分页查询
     * */
    PageUtils selectPage(Map<String, Object> params);
}
