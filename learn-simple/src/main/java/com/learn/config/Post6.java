package com.learn.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//@Component
public class Post6   implements FactoryBean<Post5>,  ApplicationContextAware {


	private ApplicationContext applicationContext;


//	@Override
//	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		System.out.println("Post6 方法注入的BeanFactoryPostProcessor");
//	}


	@Override

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		System.out.println("Post6 ApplicationContextAware" + applicationContext);
	}

	@Override
	public Post5 getObject() throws Exception {
		return new Post5();
	}

	@Override
	public Class<?> getObjectType() {
		return Post5.class;
	}
}
