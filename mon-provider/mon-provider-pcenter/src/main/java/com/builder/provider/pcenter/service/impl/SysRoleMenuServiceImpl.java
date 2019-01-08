package com.builder.provider.pcenter.service.impl;

import com.builder.provider.api.pcenter.entity.SysRoleMenuEntity;
import com.builder.common.utils.service.impl.BaseServiceImpl;
import com.builder.provider.pcenter.dao.SysRoleMenuDao;
import com.builder.provider.pcenter.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Description 角色与菜单对应关系管理 服务
 * @CreateTime 2018-08-23 22:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {
    @Override
    public void batchInsert(Map<String, Object> map) {
        this.baseMapper.batchInsert(map);
    }

    @Override
    public List<Long> getMenuIdList(Long roleId) {
        return this.baseMapper.getMenuIdList(roleId);
    }
}
