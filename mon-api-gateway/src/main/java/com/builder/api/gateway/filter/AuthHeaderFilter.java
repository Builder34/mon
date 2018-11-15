package com.builder.api.gateway.filter;

import com.builder.api.gateway.util.Constant;
import com.builder.api.gateway.util.MonRequestUtil;
import com.builder.common.base.constant.SecurityConstants;
import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.base.exception.BusinessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * auth header 过滤器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-23 14:31:45
 */
@Slf4j
@Component
public class AuthHeaderFilter extends ZuulFilter {

    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";
    private static final String LOGOUT_URI = "/oauth/token";

    /**
     * <p>filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：</p>
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return pre
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高
     * */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * */
    @Override
    public Object run() throws BusinessException {
        log.info("AuthHeaderFilter - 开始认证权限...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            doSomething(requestContext);
        } catch (Exception e) {
            log.error("AuthHeaderFilter - [ERROR] EXCEPTION={}", e.getMessage(), e);
            throw new BusinessException(MonErrorCodeEnum.PCENTER100400);
        }
        return null;
    }

    private void doSomething(RequestContext requestContext) throws ZuulException {
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        if (OPTIONS.equalsIgnoreCase(request.getMethod()) || !requestURI.contains(AUTH_PATH)
                || !requestURI.contains(LOGOUT_URI) ) {
            return;
        }
        String authHeader = MonRequestUtil.getAuthHeader(request);
        if (StringUtils.isBlank(authHeader)) {
            throw new ZuulException("刷新页面重试", 403, "check token fail");
        }
        if (authHeader.startsWith(Constant.BEARER_TOKEN_TYPE)) {
            requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
            log.info("authHeader={}", authHeader);
            //传递给后续微服务
            requestContext.addZuulRequestHeader(SecurityConstants.HEADER_LABEL, authHeader);
        }
    }
}
