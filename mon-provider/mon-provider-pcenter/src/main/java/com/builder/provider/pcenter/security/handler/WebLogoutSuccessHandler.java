package com.builder.provider.pcenter.security.handler;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.utils.JacksonUtil;
import com.builder.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebLogoutSuccessHandler web端退出登录成功处理
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 10:31:39
 */
@Slf4j
public class WebLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("web端退出登录成功...");
        response.setContentType(GlobalConstant.HTTP_RESPONSE_CONTENT_TYPE);
        response.getWriter().write(JacksonUtil.encode2(R.ok("退出成功！")));
    }
}
