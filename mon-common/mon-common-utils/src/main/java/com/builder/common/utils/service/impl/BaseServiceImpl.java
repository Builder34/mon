package com.builder.common.utils.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.Query;
import com.builder.common.utils.service.BaseService;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Description 封装mybatis-plus的service接口实现类
 * @CreateTime 2018-08-04 vue-2.5.17:45:16
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M ,T> implements BaseService<T> {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String kwd = (String)params.get("kwd");

        Page<T> page = this.selectPage(
                new Query<T>(params).getPage(),
                new EntityWrapper<T>().like(StringUtils.isNotBlank(kwd),"username", kwd)
        );

        return new PageUtils(page);
    }
}
