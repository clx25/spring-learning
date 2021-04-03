package com.learn.test;

import com.learn.model.A;
import org.springframework.stereotype.Component;
//能够自动注入的有参构造器
@Component
public class AutowireConstructorModeTest {
	private A a;

	public AutowireConstructorModeTest(A a) {
		this.a = a;
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
