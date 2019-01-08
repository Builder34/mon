package com.builder.provider.pcenter.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.Query;
import com.builder.common.utils.service.impl.BaseServiceImpl;
import com.builder.provider.api.pcenter.entity.SysRoleEntity;
import com.builder.provider.pcenter.dao.SysRoleDao;
import com.builder.provider.pcenter.dao.SysRoleMenuDao;
import com.builder.provider.pcenter.service.SysRoleService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;


/**
 * @Description 角色管理 服务
 * @CreateTime 2018-08-23 22:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public PageUtils queryJoinPage(Map<String, Object> params) {
        Page<SysRoleEntity> page =  new Query<SysRoleEntity>(params).getPage();
        page.setRecords(this.baseMapper.selectJoinPage(page, params));

        return new PageUtils(page);
    }

    @Override
    public boolean insert(SysRoleEntity entity) {
        boolean flag = super.insert(entity);
        if(flag && CollectionUtils.isNotEmpty(entity.getMenuIdList())) {
            //新增选中的权限菜单项list
            Map<String, Object> params = Maps.newHashMap();
            params.put("roleId", entity.getRoleId());
            params.put("menuIdList", entity.getMenuIdList());
            sysRoleMenuDao.batchInsert(params);
        }
        return flag;
    }

    @Override
    public boolean updateById(SysRoleEntity entity) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("role_id", entity.getRoleId());
        //1.删除roleId在sys_role_menu对应的所有menuId
        sysRoleMenuDao.deleteByMap(params);
        if(entity.getMenuIdList() != null && entity.getMenuIdList().size()>0) {
            //2.新增选中的权限菜单项list
            params.put("menuIdList", entity.getMenuIdList());
            params.put("roleId", entity.getRoleId());
            params.put("updateUserId", entity.getUpdateUserId());
            sysRoleMenuDao.batchInsert(params);
        }
        //3.新增sys_role记录
        return super.updateById(entity);
    }

    @Override
    public SysRoleEntity selectById(Serializable id) {
        return this.baseMapper.selectByRoleId(id);
}
}
