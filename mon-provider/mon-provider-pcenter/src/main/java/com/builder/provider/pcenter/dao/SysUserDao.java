package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.builder.common.entity.pcenter.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description 系统用户Dao
 * @CreateTime 2018-08-04 vue-2.5.17:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);
}
