package com.learn.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class Post3 implements BeanDefinitionRegistryPostProcessor, Ordered {
	@Autowired
	private Post4 post4;

	public Post4 getPost4() {
		return post4;
	}

	public void setPost4(Post4 post4) {
		this.post4 = post4;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("Post3 @Component注入的BeanDefinitionRegistryPostProcessor,Ordered");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("Post3 @Component注入的BeanFactoryPostProcessor,Ordered");
	}

	@Override
	public int getOrder() {
		return 0;
	}


}
