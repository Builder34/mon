package com.builder.provider.pcenter.controller;

import com.builder.provider.api.pcenter.entity.SysUserEntity;
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
        //TODO:
        return null;
    }

    protected Long getCurrentUserId(){
        return getCurrentUser().getUserId();
    }

}
