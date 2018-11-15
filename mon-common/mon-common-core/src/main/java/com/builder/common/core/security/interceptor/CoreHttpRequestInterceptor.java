package com.builder.common.core.security.interceptor;

import com.builder.common.base.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * CoreHttpRequestInterceptor 核心http请求拦截器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-15 11:18:11
 */
@Slf4j
public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpRequestWrapper wrapper = new HttpRequestWrapper(httpRequest);
        String header = StringUtils.joinWith(SecurityConstants.HEADER_LABEL_SPLIT, CoreHeaderInterceptor.LABEL.get());
        log.info("core request interceptor, header={}", header);
        wrapper.getHeaders().add(SecurityConstants.HEADER_LABEL, header);

        return clientHttpRequestExecution.execute(wrapper, body);
    }
}
