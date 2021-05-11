package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowirePost {

	@Autowired
	private InAutowirePost inAutowirePost;

	public InAutowirePost getInAutowirePost() {
		return inAutowirePost;
	}

	public void setInAutowirePost(InAutowirePost inAutowirePost) {
		this.inAutowirePost = inAutowirePost;
	}
}
