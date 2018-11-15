package com.builder.provider.pcenter.handler;

import com.builder.common.base.enums.MonErrorCodeEnum;
import com.builder.common.base.exception.BusinessException;
import com.builder.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler 全局异常处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-14 16:25:02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @Resource
//    private TaskExecutor taskExecutor;
    @Value("${spring.profiles.active}")
    String profile;
    @Value("${spring.application.name}")
    String applicationName;

    /**
     * 参数非法异常
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return R.error(MonErrorCodeEnum.GL999110.code(), e.getMessage());
    }
    /**
     * 业务异常
     * */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R businessException(BusinessException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return e.getCode() == 0 ? R.error(e.getMessage()) : R.error(e.getCode(), e.getMessage());
    }

    /**
     * 全局异常.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public R exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        //taskExecutor.execute(() -> {
            //TODO: 保存错误信息到数据库
        //});
        return R.error();
    }
    /**
     * 无权限访问异常
     * */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public R unAuthorizedException(AccessDeniedException e) {
        log.error("无权限访问={}", e.getMessage(), e);
        return R.error(MonErrorCodeEnum.GL999401.code(), MonErrorCodeEnum.GL999401.msg());
    }
}
