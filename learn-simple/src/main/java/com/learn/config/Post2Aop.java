package com.learn.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Post2Aop {

	@Before("execution(* com.learn.config.PrototypeCircularReference*.*(..))")
	public void before() {
		System.out.println("before LearnController");
	}
}
