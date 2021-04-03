package com.learn.controller;

import com.learn.config.Post3;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InsertTestImpll implements ApplicationContextAware {


	private  Post3 post3;

	public Post3 getPost3() {
		return post3;
	}

//	@Override
//	public void setPost3(Post3 post3) {
//		System.out.println(post3);
//		this.post3=post3;
//	}


	private ApplicationContext applicationContext;


	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Override
	public String toString() {
		return "InsertTestImpll";
	}
}
