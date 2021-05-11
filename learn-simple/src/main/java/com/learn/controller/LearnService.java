package com.learn.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LearnService implements EnvironmentAware {
	@Override
	public void setEnvironment(Environment environment) {
		System.setProperty("a","b");
		environment.getProperty("a");
	}
}
