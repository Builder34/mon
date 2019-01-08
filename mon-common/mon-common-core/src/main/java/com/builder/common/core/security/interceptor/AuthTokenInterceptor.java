package com.builder.common.core.security.interceptor;

import com.builder.common.base.constant.GlobalConstant;
import com.builder.common.base.constant.SecurityConstants;
import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.base.utils.ThreadLocalMap;
import com.builder.common.core.dto.LoginAuthDto;
import com.builder.common.core.security.NoNeedAccessAuthentication;
import com.builder.common.core.util.RedisClientUtils;
import com.builder.common.core.util.RedisKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * AuthTokenInterceptor token认证拦截器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-15 11:49:19
 */
@Slf4j
public class AuthTokenInterceptor implements HandlerInterceptor {

    @Value("${mon.oauth2.jwtSigningKey}")
    private String jwtSigningKey;
    @Autowired
    private RedisClientUtils redisClientUtils;

    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH1 = "/auth";
    private static final String AUTH_PATH2 = "/oauth";
    private static final String AUTH_PATH3 = "/error";
    private static final String AUTH_PATH4 = "/api";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("==> preHandle - 权限拦截器. 请求url={}", uri);
        if(uri.contains(AUTH_PATH1) || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
            log.info("==> preHandle - 属于配置可通过的URL不走认证，url={}", uri);
            return true;
        }
        if(OPTIONS.equalsIgnoreCase(request.getMethod())) {
            log.info("==> preHandle - OPTIONS的请求不走认证，method={}", request.getMethod().toUpperCase());
            return true;
        }
        if(isHaveAccess(handler)) {
            log.info("==> preHandle - 添加了不需要认证注解的请求不走认证...");
            return true;
        }
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = StringUtils.substringAfter(authHeader, SecurityConstants.BEARER_TOKEN_TYPE);
        log.info("==> perHandler - 权限拦截器. token={}", token);
        //TODO: 从redis中读取用户信息，并保持在本地线程Map
        LoginAuthDto dto = redisClientUtils.get(RedisKeyGenerator.getAccessTokenKey(token), LoginAuthDto.class);
        if(dto == null) {
            log.error("获取用户信息失败, 不允许操作");
            return false;
        }
        log.info("==> perHandler - 权限拦截器. url= {}, loginUser={}", uri, dto);
        ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, dto);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //无需操作
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if(e != null) {
            log.error("==> afterCompletion - 解析token失败={}", e.getMessage(), e);
            this.handlerException(httpServletResponse);
        }
    }


    private void handlerException(HttpServletResponse response) throws IOException {
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"status\": "+ MonErrorCodeEnum.PCENTER100411.code() +", \"message\": \""+MonErrorCodeEnum.PCENTER100411.msg()+"\"}");
        response.flushBuffer();
    }

    private boolean isHaveAccess(Object handler) {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            NoNeedAccessAuthentication responseBody = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);

            return responseBody != null;
        }
        return false;
    }
}
