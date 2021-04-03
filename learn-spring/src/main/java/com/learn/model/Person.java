package com.learn.model;

import org.springframework.stereotype.Component;

@Component("ppp")
public class Person {
	int i = 10;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return "Person{" +
				"i=" + i +
				'}';
	}
}
