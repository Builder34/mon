package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.builder.common.entity.pcenter.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 用户与角色对应关系Dao
 * @CreateTime 2018-08-23 17:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

}
