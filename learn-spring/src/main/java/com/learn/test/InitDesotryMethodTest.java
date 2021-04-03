package com.learn.test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


//在配置文件中通过@bean
public class InitDesotryMethodTest implements InitializingBean, DisposableBean {

	/*
	在destroyMethod = AbstractBeanDefinition.INFER_METHOD
	且没有实现DisposableBean接口时，spring会自动寻找close或者shutdown方法
	也就是说DisposableBean的destroy优先于close优先于shutdown
	 */
	public void shutdown() {
		System.out.println("InitMethodTest->destroyMethod->AbstractBeanDefinition.INFER_METHOD");
	}

	public void initMethod() {
		System.out.println("InitMethodTest->initMethod->initMethod");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("InitMethodTest->@PostConstruct");
	}

	@PreDestroy
	public void predesotry() {
		System.out.println("InitMethodTest->@PreDestroy");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("InitMethodTest->DisposableBean->destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitMethodTest->InitializingBean->afterPropertiesSet");
	}
}
