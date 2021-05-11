package com.learn.test;

import com.learn.model.A;
import com.learn.model.Common;
import com.learn.model.Person;
import org.springframework.stereotype.Component;

@Component
public class ByNameTest {

	private Common common;

	public Common getCommon() {
		return common;
	}

	public void setCC(A common) {
		this.common = common;
	}


	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
