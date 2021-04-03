package com.learn.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeCircularReferenceTest {

	@Autowired
	private PrototypeCircularReference2Test prototypeCircularReference2Test;

	public PrototypeCircularReference2Test getPrototypeCircularReference2Test() {
		return prototypeCircularReference2Test;
	}

	public void setPrototypeCircularReference2Test(PrototypeCircularReference2Test prototypeCircularReference2Test) {
		this.prototypeCircularReference2Test = prototypeCircularReference2Test;
	}
}
