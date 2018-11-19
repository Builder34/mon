package com.builder.provider.pcenter.security.handler;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.core.security.RequestAuthUtils;
import com.builder.common.utils.JacksonUtil;
import com.builder.common.utils.R;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * WebAuthenticationSuccessHandler 认证成功处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 13:53:12
 */
@Slf4j
@Component("webAuthenticationSuccessHandler")
public class WebAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final String BEARER_TOKEN_TYPE = "Basic ";

    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        String[] tokens = RequestAuthUtils.extractAndDecodeHeader(header);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if(clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在: "+clientId);
        }else if(StringUtils.equals(clientSecret, clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配: "+clientSecret);
        }
        TokenRequest tokenRequest = new TokenRequest(Maps.newHashMap(), clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        Object loginUser = authentication.getPrincipal();
        log.info("==> 登录用户信息: {}", JacksonUtil.encode2(loginUser));
        //TODO:记录用户登录日志
        response.setContentType(GlobalConstant.HTTP_RESPONSE_CONTENT_TYPE);
        response.getWriter().write(JacksonUtil.encode2(R.ok().put("token", token)));

        log.info("认证通过，登录成功，返回响应token: {}", token.getValue());
    }
}
