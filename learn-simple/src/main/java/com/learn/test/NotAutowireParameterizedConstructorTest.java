package com.learn.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//不自动注入的有参构造器，spring无法处理
//@Component
public class NotAutowireParameterizedConstructorTest {

	private int i;

	public NotAutowireParameterizedConstructorTest(int i){
		this.i=i;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
