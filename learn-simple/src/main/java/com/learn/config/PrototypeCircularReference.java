package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeCircularReference {
	@Autowired
	private PrototypeCircularReference2 prototypeCircularReference2;

	public PrototypeCircularReference2 getPrototypeCircularReference2() {
		return prototypeCircularReference2;
	}

	public void setPrototypeCircularReference2(PrototypeCircularReference2 prototypeCircularReference2) {
		this.prototypeCircularReference2 = prototypeCircularReference2;
	}

}
