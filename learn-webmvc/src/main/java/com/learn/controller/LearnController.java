package com.learn.controller;

import com.learn.model.User;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearnController {

	@PostMapping("/user")
	public String test(@RequestBody User user){
		System.out.println(user);

		return "success";
	}
	@GetMapping("/user")
	public String get(){
		return "success";
	}

}
