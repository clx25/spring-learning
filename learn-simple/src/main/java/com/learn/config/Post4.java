package com.learn.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class Post4 implements FactoryBean<Post6> {
//	@Override
//	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//		System.out.println("Post4 @Component注入的BeanDefinitionRegistryPostProcessor");
//	}
//
//	@Override
//	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		System.out.println("Post4 @Component注入的BeanFactoryPostProcessor");
//	}

	@Override
	public Post6 getObject() throws Exception {
		System.out.println("post4 FactoryBean<Post6>");
		return new Post6();
	}

	@Override
	public Class<?> getObjectType() {
		return Post6.class;
	}
}
