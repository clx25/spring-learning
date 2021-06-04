package com.learn.test.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.stereotype.Component;

/**
 * 代理的解析与创建在
 *   {@link AbstractAutoProxyCreator#postProcessAfterInitialization}
 * 切入点表达式的获取在
 *   {@link ReflectiveAspectJAdvisorFactory#getAdvisor}
 *
 */
@Aspect//("perthis(execution(* com.learn.test.AopTest.*(..)))")
@Component
public class AopAspect {

	@Before("execution(* com.learn.test.aop.AopTarget.*(..))")
	public void aspectConfig(){
		System.out.println("AspectConfig->aspectConfig->* com.learn.test.AopTest.*(..)");
	}
}
