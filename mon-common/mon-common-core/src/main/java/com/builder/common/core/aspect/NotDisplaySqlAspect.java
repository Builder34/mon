package com.builder.common.core.aspect;

import com.builder.common.base.utils.ThreadLocalMap;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * The class Not display sql aspect.
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-15 11:18:11
 */
@Aspect
@Component
public class NotDisplaySqlAspect {
	/**
	 * The constant DISPLAY_SQL.
	 */
	public static final String DISPLAY_SQL = "DISPLAY_SQL";

	@Pointcut("@annotation(com.builder.common.core.annotation.NotDisplaySql)")
	private void myPointCut() {
	}

	/**
	 * Before.
	 */
	@Before(value = "myPointCut()")
	public void before() {
		ThreadLocalMap.put(DISPLAY_SQL, Boolean.FALSE);
	}

	/**
	 * After.
	 */
	@After(value = "myPointCut()")
	public void after() {
		ThreadLocalMap.remove(DISPLAY_SQL);
	}
}
