package com.dss.springVaadinBasicProject.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
	
	@Before("bean(*Service)")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("log advice got executed before call to method : "+joinPoint.getSignature().getName());
	}
	
	@After("bean(*Service)")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("log advice got executed after call to method : "+joinPoint.getSignature().getName());
	}
}
