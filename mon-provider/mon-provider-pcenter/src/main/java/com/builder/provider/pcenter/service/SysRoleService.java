package com.builder.provider.pcenter.service;

import com.builder.common.utils.PageUtils;
import com.builder.common.utils.service.BaseService;
import com.builder.provider.api.pcenter.entity.SysRoleEntity;

import java.util.Map;

/**
 * @Description SysRoleService
 * @CreateTime 2018-08-23 22:36:34
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    /**
     * 关联表分页查询
     * @param  params 参数
     * @return  角色分页数据
     * */
    PageUtils queryJoinPage(Map<String, Object> params);
}
