package com.learn.test;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FactoryBeanTest implements FactoryBean<FactoryBeanTest.FactoryBeanInner> {

	@Override
	public FactoryBeanInner getObject() throws Exception {
		return new FactoryBeanInner();
	}

	@Override
	public Class<?> getObjectType() {
		return FactoryBeanInner.class;
	}

	public static class FactoryBeanInner{

	}
}


