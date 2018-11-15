package com.builder.common.core.security.interceptor;

import com.builder.common.base.constant.SecurityConstants;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CoreHeaderInterceptor
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-30 22:57:01
 */
@Slf4j
public class CoreHeaderInterceptor extends HandlerInterceptorAdapter {


    /**
     * The constant LABEL.
     */
    public static final HystrixRequestVariableDefault<List<String>> LABEL = new HystrixRequestVariableDefault<>();



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        CoreHeaderInterceptor.initHystrixRequestContext(request.getHeader(SecurityConstants.HEADER_LABEL));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        CoreHeaderInterceptor.shutdownHystrixRequestContext();
    }

    private static void initHystrixRequestContext(String labels) {
        log.info("LABEL={}", labels);
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }
        if (StringUtils.isBlank(labels)) {
           CoreHeaderInterceptor.LABEL.set(Arrays.asList(labels.split(SecurityConstants.HEADER_LABEL_SPLIT)));
        } else {
            CoreHeaderInterceptor.LABEL.set(Collections.emptyList());
        }
    }

    private static void shutdownHystrixRequestContext() {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }
}
