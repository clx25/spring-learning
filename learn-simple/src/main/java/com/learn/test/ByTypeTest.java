package com.learn.test;

import com.learn.model.A;
import com.learn.model.Common;
import com.learn.model.Person;
import org.springframework.stereotype.Component;

@Component
public class ByTypeTest {

	private Common ccc;

	public Common getCommon() {
		return ccc;
	}

	public void setO(A oo) {
		this.ccc = oo;
	}
//
//	private A zzz;
//
//	public A getA() {
//		return zzz;
//	}
//
//	public void setZzz(A zz) {
//		this.zzz = zz;
//	}
}
