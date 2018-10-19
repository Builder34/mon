package com.builder.provider.pcenter.handler;

import com.builder.common.utils.R;
import com.builder.common.utils.exception.MBException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2018-07-27 20:36:49
 */
@RestControllerAdvice
public class MBExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(MBException.class)
	public R handleMBException(MBException e){
		R r = R.error(e.getCode(), e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
}
