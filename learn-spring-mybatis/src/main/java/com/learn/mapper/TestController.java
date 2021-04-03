package com.learn.mapper;

import com.learn.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;

public class TestController {

	@Autowired
	public UserController userController;

	public void t(){
		System.out.println(userController);
	}
}
