package com.builder.provider.pcenter.service.impl;

import com.builder.common.entity.pcenter.SysUserTokenEntity;
import com.builder.common.utils.R;
import com.builder.provider.pcenter.dao.SysUserTokenDao;
import com.builder.provider.pcenter.security.oauth2.TokenGenerator;
import com.builder.provider.pcenter.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description 系统用户Token服务
 * @CreateTime 2018-08-10 17:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
public class SysUserTokenServiceImpl implements SysUserTokenService {

    @Autowired
    private SysUserTokenDao sysUserTokenDao;
    //过期时间：12小时
    private final static int EXPIRE = 12 * 3600  * 1000; //单位：毫秒

    @Override
    public SysUserTokenEntity queryByUserId(Long userId) {
        Map<String,Object> params = new HashMap<>();
        params.put("user_id",userId);
        List<SysUserTokenEntity> list = sysUserTokenDao.selectByMap(params);
        return (list.size()>0) ? list.get(0) : null;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        Map<String,Object> params = new HashMap<>();
        params.put("token",token);
        List<SysUserTokenEntity> list = sysUserTokenDao.selectByMap(params);
        return (list.size()>0) ? list.get(0) : null;
    }

    @Override
    public void save(SysUserTokenEntity entity) {
        sysUserTokenDao.insert(entity);
    }

    @Override
    public void update(SysUserTokenEntity entity) {
        sysUserTokenDao.updateById(entity);
    }

    @Override
    public R createToken(Long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE);
        SysUserTokenEntity tokenEntity = queryByUserId(userId);
        if(tokenEntity == null){
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            save(tokenEntity);
        }else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            update(tokenEntity);
        }
        R r = R.ok().put("token", token).put("expire", (EXPIRE/1000)+"秒");
        return r;
    }
}
