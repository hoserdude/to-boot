package com.hoserdude.toboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect that times the execution time of methods annotated with the {@link Instrumentable} 
 * Interface.  
 */
@Aspect
@Component
public class InstrumentationAspect {
	final static Logger logger = LoggerFactory.getLogger(InstrumentationAspect.class);

	@Around("@annotation(com.hoserdude.toboot.aop.Instrumentable)")
	public Object profile(ProceedingJoinPoint call) throws Throwable {

		long startTime = System.currentTimeMillis();
		try {
			return call.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;

			final Signature sig = call.getSignature();
			final String declaringTypeName = sig.getDeclaringTypeName();
			final String methodName = sig.getName();
			final String operation = declaringTypeName + "." + methodName;

			logger.info("profile; op={}; time={}",operation,executeTime);
		}
	}
}
