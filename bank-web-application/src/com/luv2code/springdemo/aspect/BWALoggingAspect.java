package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BWALoggingAspect {
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void pointcutForCotrollerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void pointcutForServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.DAO.*.*(..))")
	private void pointcutForDAOPackage() {}
	
	@Pointcut("pointcutForCotrollerPackage() || pointcutForServicePackage() || pointcutForDAOPackage()")
	private void pointcutForAppFlow() {}
	
	@Before("pointcutForAppFlow()")
	public void beforeAdvice(JoinPoint joinPoint) {
		
		log.info("=====> @Before: Calling Method: "+joinPoint.getSignature().toShortString());
		for(Object arg : joinPoint.getArgs())
			log.info("=====> Argument: "+ arg);
		
	}
	
	@AfterReturning(
			pointcut = "pointcutForAppFlow()",
			returning = "result"
			)
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		
		log.info("=====> @AfterReturning: Calling Method: "+joinPoint.getSignature().toShortString());
		log.info("=====> Result: "+ result);
		
	}
	

}
