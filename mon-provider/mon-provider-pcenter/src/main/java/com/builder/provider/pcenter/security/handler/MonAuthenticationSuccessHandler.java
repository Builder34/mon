package com.builder.provider.pcenter.security.handler;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.core.dto.LoginAuthDto;
import com.builder.common.core.security.RequestAuthUtils;
import com.builder.common.core.util.JacksonUtil;
import com.builder.common.utils.R;
import com.builder.common.core.util.RedisClientUtils;
import com.builder.provider.pcenter.security.impl.MonUserDetails;
import com.builder.provider.pcenter.security.properties.OAuth2ClientProperties;
import com.builder.provider.pcenter.security.properties.SecurityProperties;
import com.builder.common.core.util.RedisKeyGenerator;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MonAuthenticationSuccessHandler 认证成功处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 13:53:12
 */
@Slf4j
@Component("monAuthenticationSuccessHandler")
public class MonAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    //认证类型
    private static final String AUTHORIZATION_TOKEN_TYPE = "Basic ";

    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private RedisClientUtils redisClientUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(AUTHORIZATION_TOKEN_TYPE)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        String[] tokens = RequestAuthUtils.extractAndDecodeHeader(header);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if(clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在: "+clientId);
        }else if(!StringUtils.equals(clientSecret, clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配: "+clientSecret);
        }
        TokenRequest tokenRequest = new TokenRequest(Maps.newHashMap(), clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        MonUserDetails loginUser = (MonUserDetails)authentication.getPrincipal();
        log.info("==> 登录用户信息: {}", JacksonUtil.encode2(loginUser));
        //保存用户登录信息到redis作为session登录用户
        LoginAuthDto dto = new LoginAuthDto();
        dto.setLoginName(loginUser.getUsername());
        dto.setUserId(loginUser.getUserId());
        dto.setUsername(loginUser.getUsername());
        OAuth2ClientProperties prop = securityProperties.getOauth2().getClients()[0];
        redisClientUtils.set(RedisKeyGenerator.getAccessTokenKey(token.getValue()), dto,
                prop.getAccessTokenValidateSeconds());
        //TODO:记录用户登录日志, 保存token信息到数据库
        response.setContentType(GlobalConstant.HTTP_RESPONSE_CONTENT_TYPE);
        response.getWriter().write(JacksonUtil.encode2(R.ok().put("data", token.getValue())));

        log.info("认证通过，登录成功...");
    }
}
