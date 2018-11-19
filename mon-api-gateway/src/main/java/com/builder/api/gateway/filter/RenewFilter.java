package com.builder.api.gateway.filter;

import com.builder.common.base.constant.SecurityConstants;
import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.base.exception.BusinessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * RenewFilter token续租
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-30 22:57:01
 */
@Component
@Slf4j
public class RenewFilter extends ZuulFilter {

	@Resource
	private JwtTokenStore jwtTokenStore;
	private static final int EXPIRES_IN = 60 * 20;

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws BusinessException {
		log.info("RenewFilter - token续租...");
		RequestContext requestContext = RequestContext.getCurrentContext();
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("RenewFilter - token续租. [ERROR] EXCEPTION={}", e.getMessage(), e);
			throw new BusinessException(MonErrorCodeEnum.PCENTER100400);
		}
		return null;
	}

	private void doSomething(RequestContext requestContext) {
		HttpServletRequest request = requestContext.getRequest();
		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), SecurityConstants.BEARER_TOKEN_TYPE);
		if (StringUtils.isBlank(token)) {
			return;
		}
		OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
		int expiresIn = oAuth2AccessToken.getExpiresIn();

		if (expiresIn < EXPIRES_IN) {
			HttpServletResponse servletResponse = requestContext.getResponse();
			servletResponse.addHeader("Renew-Header", "true");
		}
	}

}