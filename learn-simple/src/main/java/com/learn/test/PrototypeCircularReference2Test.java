package com.learn.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeCircularReference2Test {

	@Autowired
	private PrototypeCircularReferenceTest prototypeCircularReferenceTest;

	public PrototypeCircularReferenceTest getPrototypeCircularReferenceTest() {
		return prototypeCircularReferenceTest;
	}

	public void setPrototypeCircularReferenceTest(PrototypeCircularReferenceTest prototypeCircularReferenceTest) {
		this.prototypeCircularReferenceTest = prototypeCircularReferenceTest;
	}
}
