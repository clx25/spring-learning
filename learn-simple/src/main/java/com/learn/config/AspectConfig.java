package com.learn.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {

	@Before("execution(* com.learn.test.AopTest.*(..))")
	public void aspectConfig(){
		System.out.println("AspectConfig->aspectConfig->* com.learn.test.AopTest.*(..)");
	}
}
