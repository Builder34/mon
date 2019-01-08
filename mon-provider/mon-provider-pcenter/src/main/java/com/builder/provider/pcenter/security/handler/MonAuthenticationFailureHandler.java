package com.builder.provider.pcenter.security.handler;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.core.util.JacksonUtil;
import com.builder.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MonAuthenticationFailureHandler 认证失败处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 13:59:07
 */
@Slf4j
@Component("monAuthenticationFailureHandler")
public class MonAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("认证不通过，登录失败...");
        //TODO: 记录失败次数 和原因 ip等信息 5次登录失败,锁定用户, 不允许在此登录

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(GlobalConstant.HTTP_RESPONSE_CONTENT_TYPE);
        response.getWriter().write(JacksonUtil.encode2(R.error(exception.getMessage())));

    }
}
