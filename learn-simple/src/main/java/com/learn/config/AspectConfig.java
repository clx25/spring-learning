package com.learn.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect("perthis(execution(* com.learn.test.AopTest.*(..)))")
@Scope("prototype")
@Component
public class AspectConfig {

	@Before("execution(* com.learn.test.AopTest.*(..))")
	public void aspectConfig(){
		System.out.println("AspectConfig->aspectConfig->* com.learn.test.AopTest.*(..)");
	}
}
