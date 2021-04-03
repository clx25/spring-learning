package com.learn.controller;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import com.learn.config.Post3;

@Component
public class LearnController {


	private Post3 post3;

	public Post3 getPost3() {
		return post3;
	}

	public void setPost3(Post3 post3) {
		System.out.println("555555555");
		this.post3 = post3;
	}

	@Lookup
	public LearnService getLearnService() {
		return null;
	}


}
