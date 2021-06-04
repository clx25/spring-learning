package com.learn.test.aop;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.stereotype.Component;

/**
 * {@link AbstractAutoProxyCreator#postProcessAfterInitialization}
 */
@Component
public class AopTarget {

	public void aopTest(){
		System.out.println("AopTest->aopTest");
	}


}
