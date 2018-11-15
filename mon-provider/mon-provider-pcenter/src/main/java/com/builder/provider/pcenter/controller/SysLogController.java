package com.builder.provider.pcenter.controller;

import com.builder.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description 系统日志接口
 * @CreateTime 2018-08-16 15:54:59
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @PostMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(Map<String,Object> params){

        return R.ok();
    }
}
