package com.builder.provider.pcenter.security.impl;

import com.builder.provider.api.pcenter.entity.SysUserEntity;
import com.builder.provider.pcenter.service.SysMenuService;
import com.builder.provider.pcenter.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MonUserDetailsServiceImpl
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-05 17:32:45
 */
@Component
@Slf4j
public class MonUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SysUserEntity> list = sysUserService.selectByUsername(username);
        if( list ==null || list.size() < 1 ) {
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }
        SysUserEntity user = list.get(0);
        List<GrantedAuthority> authorities = sysMenuService.getPermissionListByUserId(user.getUserId());
        log.debug("==============sysUserEntity: {}", user);
        return new MonUserDetails(user, authorities);
    }
}
