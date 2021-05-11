package com.learn.test;

import com.learn.model.A;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class LookupTest {

	//cglib会对lookup方法进行代理，所以可以是抽象类或接口
	@Lookup //往单例中注入多例
	public A lookup() {
		return null;
	}

}
