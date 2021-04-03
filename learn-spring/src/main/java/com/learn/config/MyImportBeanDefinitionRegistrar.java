package com.learn.config;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AbstractBeanDefinition bd = (AbstractBeanDefinition) registry.getBeanDefinition("learnController");
//		bd.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
		bd.setAutowireCandidate(true);
	}
}
