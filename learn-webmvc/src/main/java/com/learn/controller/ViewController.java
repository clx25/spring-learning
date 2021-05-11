package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

	@RequestMapping("/view")
	public ModelAndView toView(){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("data"," toView");
		return modelAndView;
	}
}
