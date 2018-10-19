package com.builder.common.utils.service;

import com.baomidou.mybatisplus.service.IService;
import com.builder.common.utils.PageUtils;

import java.util.Map;

/**
 * @Description 封装mybatis-plus的基础service接口类
 * @CreateTime 2018-08-04 vue-2.5.17:39:59
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface BaseService<T> extends IService<T> {

    PageUtils queryPage(Map<String, Object> params);
}
