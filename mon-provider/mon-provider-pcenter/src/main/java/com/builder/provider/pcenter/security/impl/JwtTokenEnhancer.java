package com.builder.provider.pcenter.security.impl;

import com.google.common.collect.Maps;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * JwtTokenEnhancer 使用jwt增强token
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 11:33:24
 */
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> jwtInfo  = Maps.newHashMap();
        jwtInfo.put("timestamp", System.currentTimeMillis());
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Object principal = authentication.getPrincipal();
            jwtInfo.put("loginName", ((UserDetails)principal).getUsername());
        }
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(jwtInfo);

        return oAuth2AccessToken;
    }
}
