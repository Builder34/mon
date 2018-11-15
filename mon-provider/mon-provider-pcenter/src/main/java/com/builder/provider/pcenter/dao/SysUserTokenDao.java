package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.builder.provider.api.pcenter.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 系统用户登录token Dao
 * @CreateTime 2018-08-04 vue-2.5.17:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

}
