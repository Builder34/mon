package com.builder.provider.pcenter.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("认证通过，登录成功...");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        //TODO:
    }
}
