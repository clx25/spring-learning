package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeCircularReference2 {

	@Autowired
	private PrototypeCircularReference prototypeCircularReference;

	public PrototypeCircularReference getPrototypeCircularReference() {
		return prototypeCircularReference;
	}

	public void setPrototypeCircularReference(PrototypeCircularReference prototypeCircularReference) {
		this.prototypeCircularReference = prototypeCircularReference;
	}
}
