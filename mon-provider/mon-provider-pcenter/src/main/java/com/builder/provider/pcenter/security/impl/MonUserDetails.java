package com.builder.provider.pcenter.security.impl;

import com.builder.provider.api.pcenter.entity.SysUserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * MonUserDetails
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-06 17:32:15
 */
@Data
public class MonUserDetails implements UserDetails {

    private long userId;
    private String username;
    private String password;
    private String salt;
    private Collection<GrantedAuthority> authorities;

    public MonUserDetails(long userId, String username, String password, String salt, Collection<GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.authorities = authorities;
    }
    public MonUserDetails(SysUserEntity entity, Collection<GrantedAuthority> authorities) {
        this.userId = entity.getUserId();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.salt = entity.getSalt();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
