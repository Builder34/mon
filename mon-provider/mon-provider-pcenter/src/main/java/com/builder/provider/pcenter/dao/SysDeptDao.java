package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.builder.provider.api.pcenter.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 部门Dao
 * @CreateTime 2018-08-23 17:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    /**
     * 获取顶层的部门
     * @return 顶层部门列表
     * */
    List<SysDeptEntity> getDeptFirstLevelList();

    /**
     * 根据首层导航菜单id查询对应模块下的菜单列表
     * @param parentId 上级部门id
     * @return 菜单列表
     * */
    List<SysDeptEntity> getDeptListByParentId(@Param("parentId") Long parentId);

    /**
     * 根据父节点计算当前节点所在层数
     * @param parentId 上级部门id
     * @return 层数
     * */
    Integer getCurrentLayer(@Param("parentId") Long parentId);
}
