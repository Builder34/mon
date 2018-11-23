package com.builder.provider.pcenter.captcha;

import com.builder.common.base.constant.SecurityConstants;
import com.builder.common.core.security.exception.CaptchaException;
import com.builder.provider.pcenter.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 校验验证码的过滤器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-21 15:04:46
 */
@Component("captchaFilter")
@Slf4j
public class CaptchaFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     * */
    @Autowired
    private AuthenticationFailureHandler monAuthenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private CaptchaProcessorHolder captchaProcessorHolder;
    /**
     * 存放需要验证码的url
     * */
    private Map<String, CaptchaType> urlMap = new HashMap<>();
    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final String GET = "get";

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, CaptchaType.IMAGE);
        //urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, CaptchaType.SMS);
        /**下面是通过配置文件配置的需要验证码的url路径*/
        addUrlToMap(securityProperties.getCaptcha().getImage().getUrl(), CaptchaType.IMAGE);

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CaptchaType type = getCaptchaType(request);
        if(type != null) {
            log.info("正在校验请求"+ request.getRequestURI()+"中的验证码，验证码类型: {}", type.getParamNameOnValidate());
            try {
                captchaProcessorHolder.findCaptchaProcess(type).validate(new ServletWebRequest(request, response));
                log.info("验证码校验通过...");
            }catch (CaptchaException e) {
                monAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                //下面的return很关键，因为异常需要中断过滤器链
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
    public void addUrlToMap(String urlString, CaptchaType type) {
        if(StringUtils.isNotBlank(urlString)) {
            String[] urlArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urlArray) {
                urlMap.put(url, type);
            }
        }
    }
    /**
     * 通过匹配url，获取需要校验的验证码类型
     * */
    private CaptchaType getCaptchaType(HttpServletRequest request) {
        CaptchaType type = null;
        Set<String> urls = urlMap.keySet();
        for (String url : urls){
            if(pathMatcher.match(url, request.getRequestURI())) {
                type = urlMap.get(url);
            }
        }
        return type;
    }
}
