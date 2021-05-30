package com.learn.test;

import com.learn.model.TargetSourceReturn;
import org.springframework.aop.TargetSource;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TargetSourceTest implements TargetSource {
	@Override
	public Class<?> getTargetClass() {
		return TargetSourceReturn.class;
	}

	@Override
	//如果这个方法返回true，表示getTarget()的方法的返回值将会被缓存，多次调用就只会返回同一个值
	public boolean isStatic() {
		return false;
	}

	@Override
	public Object getTarget() throws Exception {
		return new Random(5).nextInt() % 2 == 1 ? new TargetSourceReturn(1) : new TargetSourceReturn(2);
	}

	@Override
	public void releaseTarget(Object target) throws Exception {
		System.out.println(target);
	}
}
