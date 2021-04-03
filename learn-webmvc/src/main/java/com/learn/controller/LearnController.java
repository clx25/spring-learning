package com.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearnController {

	@GetMapping("/")
	public String test(){
		return "success";
	}
}
