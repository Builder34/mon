package com.builder.provider.pcenter.security.handler;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.utils.JacksonUtil;
import com.builder.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebAccessDeniedHandler 访问控制处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 14:27:10
 */
@Slf4j
@Configuration
public class MonAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        log.error("处理权限异常，e={}", e);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(GlobalConstant.HTTP_RESPONSE_CONTENT_TYPE);
        response.getWriter().write(JacksonUtil.encode2(R.error(MonErrorCodeEnum.PCENTER100401.code(),MonErrorCodeEnum.PCENTER100401.msg())));
    }
}
