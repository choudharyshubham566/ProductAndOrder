package com.oms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("@annotation(com.oms.annotation.LogOms)")
	public Object logMethodEntryExit(ProceedingJoinPoint jpt) throws Throwable {
		String className = jpt.getClass().toString();
		String methodName = jpt.getSignature().getName();

		log.info("Entering Method: " + className + "." + methodName);

		Object object = jpt.proceed();

		log.info("Exiting Method: " + className + "." + methodName);

		return object;
	}
}
