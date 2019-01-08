package com.builder.common.core.dto;

import lombok.Data;

/**
 * LoginAuthDto 登录人信息
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-19 15:02:29
 */
@Data
public class LoginAuthDto {
    /**
     * 用户id
     * */
    private Long userId;
    /**
     * 用户名称
     * */
    private String username;
    /**
     * 用户登录名称
     * */
    private String loginName;

}
