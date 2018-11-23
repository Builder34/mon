package com.builder.common.core;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.base.exception.BusinessException;
import com.builder.common.base.utils.ThreadLocalMap;
import com.builder.common.core.dto.LoginAuthDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 基础Controller, 用于获取当前登录用户信息
 * @CreateTime 2018-08-16 15:49:16
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Slf4j
public class BaseController {

    protected LoginAuthDto getCurrentUser() {
        LoginAuthDto loginAuthDto = (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (loginAuthDto == null) {
            throw new BusinessException(MonErrorCodeEnum.PCENTER100400);
        }
        return loginAuthDto;
    }

    protected Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }
}
