package com.learn.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class AutowireModeBeanDefinitionRegistryPostProcessorTest implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		AbstractBeanDefinition byTypeTest = (AbstractBeanDefinition) registry.getBeanDefinition("byTypeTest");
		byTypeTest.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		AbstractBeanDefinition byNameTest = (AbstractBeanDefinition) registry.getBeanDefinition("byNameTest");
		byNameTest.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
