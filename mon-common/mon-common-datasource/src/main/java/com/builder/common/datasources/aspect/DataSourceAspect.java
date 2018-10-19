package com.builder.common.datasources.aspect;

import com.builder.common.datasources.DataSourceNames;
import com.builder.common.datasources.DynamicDataSource;
import com.builder.common.datasources.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类 (需要在各自模块extends此类，并@Component实例化此切面类)
 * @author Builder34
 * @email lcbiao34@gmail.com
 * @date 2018-08-04 22:20:22
 */
@Aspect
@Order(-1)
public class DataSourceAspect{
    protected Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.builder.common.datasources.annotation.DataSource)")
    public void dataSourcePointCut() {
        logger.debug("数据源aop Pointcut...");
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
            logger.debug("set default datasource is " + DataSourceNames.FIRST);
        }else {
            DynamicDataSource.setDataSource(ds.name());
            logger.debug("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.debug("clean datasource");
        }
    }

    @Before("dataSourcePointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
    }
    @AfterReturning(pointcut = "dataSourcePointCut()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("response: "+ ret);
        logger.info("spend time: {}", (System.currentTimeMillis()-startTime.get()));
    }
}
