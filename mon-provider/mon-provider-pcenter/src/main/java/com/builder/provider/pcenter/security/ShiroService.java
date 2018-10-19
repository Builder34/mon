package com.builder.provider.pcenter.security;


import com.builder.common.entity.pcenter.SysUserEntity;
import com.builder.common.entity.pcenter.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2017-06-06 8:49
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
