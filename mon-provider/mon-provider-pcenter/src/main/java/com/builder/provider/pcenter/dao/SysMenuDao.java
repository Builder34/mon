package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.builder.common.entity.pcenter.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 系统菜单Dao
 * @CreateTime 2018-09-04 07:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 查询首层导航菜单列表(全部)
     * @return 菜单列表
     * */
    List<SysMenuEntity> getNavMenuListAll();
    /**
     * 根据首层导航菜单id查询对应模块下的菜单列表(全部)
     * @param navMenuId 首层菜单id
     * @return 菜单列表
     * */
    List<SysMenuEntity> getMenuListAll(@Param("navMenuId") Long navMenuId);

    /**
     * 查询首层导航菜单列表
     * @param userId 用户id
     * @return 菜单列表
     * */
    List<SysMenuEntity> getNavMenuListByUserId(Long userId);
    /**
     * 根据首层导航菜单id查询对应模块下的菜单列表
     * @param userId 用户id
     * @param navMenuId 首层菜单id
     * @return 菜单列表
     * */
    List<SysMenuEntity> getMenuList(@Param("userId") Long userId, @Param("navMenuId") Long navMenuId);

    /**
     * 根据父节点计算当前节点所在层数
     * @param parentId 父节点id
     * @return 层数
     * */
    Integer getCurrentLayer(@Param("parentId") Long parentId);

}
