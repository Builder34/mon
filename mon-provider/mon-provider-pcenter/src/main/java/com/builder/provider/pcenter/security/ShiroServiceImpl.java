package com.builder.provider.pcenter.security;

import com.builder.common.entity.pcenter.SysMenuEntity;
import com.builder.common.entity.pcenter.SysUserEntity;
import com.builder.common.entity.pcenter.SysUserTokenEntity;
import com.builder.common.utils.constant.Constant;
import com.builder.provider.pcenter.dao.SysMenuDao;
import com.builder.provider.pcenter.dao.SysUserDao;
import com.builder.provider.pcenter.dao.SysUserTokenDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        Map<String,Object> params = new HashMap<>();
        params.put("token",token);
        List<SysUserTokenEntity> list = sysUserTokenDao.selectByMap(params);
        return (list.size()>0) ? list.get(0) : null;
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
