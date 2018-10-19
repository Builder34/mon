package com.builder.provider.pcenter.controller;

import com.builder.common.entity.pcenter.SysUserEntity;
import com.builder.provider.pcenter.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 基础Controller, 用于获取当前登录用户信息
 * @CreateTime 2018-08-16 15:49:16
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected SysUserEntity getCurrentUser(){
        return (SysUserEntity) ShiroUtils.getSubject().getPrincipal();
    }

    protected Long getCurrentUserId(){
        return getCurrentUser().getUserId();
    }
}
