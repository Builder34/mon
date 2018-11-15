package com.builder.provider.pcenter.service;

import com.builder.common.utils.R;
import com.builder.common.utils.service.BaseService;
import com.builder.provider.api.pcenter.entity.SysUserEntity;

import java.util.List;

/**
 * @Description 系统用户service
 * @CreateTime 2018-08-10 17:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    /**
     * 根据用户名查询列表
     * @param username 用户名
     * @return 用户列表
     * */
    List<SysUserEntity> selectByUsername(String username);

    /**
     * 自定义新增用户方法
     * @param entity 用户信息
     * @return 操作结果
     * */
    R insertCustom(SysUserEntity entity);
}
