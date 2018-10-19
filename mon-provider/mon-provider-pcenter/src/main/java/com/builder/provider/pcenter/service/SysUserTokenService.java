package com.builder.provider.pcenter.service;

import com.builder.common.entity.pcenter.SysUserTokenEntity;
import com.builder.common.utils.R;

/**
 * @Description 系统用户Token服务
 * @CreateTime 2018-08-10 17:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface SysUserTokenService {

    SysUserTokenEntity queryByUserId(Long userId);
    SysUserTokenEntity queryByToken(String token);
    void save(SysUserTokenEntity entity);
    void update(SysUserTokenEntity entity);

    /**
     * 生成token
     * @param userId 系统用户id
     * */
    R createToken(Long userId);
}
