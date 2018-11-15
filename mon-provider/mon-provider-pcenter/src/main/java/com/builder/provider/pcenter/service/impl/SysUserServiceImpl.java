package com.builder.provider.pcenter.service.impl;

import com.builder.common.utils.R;
import com.builder.provider.api.pcenter.entity.SysUserEntity;
import com.builder.common.utils.service.impl.BaseServiceImpl;
import com.builder.provider.pcenter.dao.SysUserDao;
import com.builder.provider.pcenter.service.SysUserService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Description 系统用户服务
 * @CreateTime 2018-08-10 17:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Override
    public List<SysUserEntity> selectByUsername(String username) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("username", username);
        return this.baseMapper.selectByMap(params);
    }

    @Override
    public R insertCustom(SysUserEntity entity) {
        List<SysUserEntity> checkList = this.selectByUsername(entity.getUsername());
        if (checkList != null && checkList.size() > 0) {
            return R.error("新增用户失败，该username已存在");
        } else {
            int count = this.baseMapper.insert(entity);
            if(count > 0){
                return R.ok("新增用户成功");
            } else {
                return R.error("新增用户失败");
            }
        }
    }
}
