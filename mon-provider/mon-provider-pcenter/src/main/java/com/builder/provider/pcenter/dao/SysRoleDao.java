package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.builder.provider.api.pcenter.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description 系统角色Dao
 * @CreateTime 2018-08-23 17:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 关联表分页查询
     * @param page 分页工具类
     * @param  params 查询参数
     * @return  角色列表数据
     * */
    List<SysRoleEntity> selectJoinPage(Pagination page, @Param("params")Map<String, Object> params);
    /**
     * 查询角色信息详情
     * @param roleId 角色id
     * @return 角色信息
     * */
    SysRoleEntity selectByRoleId(Serializable roleId);
}
