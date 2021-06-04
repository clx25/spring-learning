package com.learn.test;

import com.learn.model.A;
import com.learn.model.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 构造器自动注入模式
 * {@link AbstractAutowireCapableBeanFactory#determineConstructorsFromBeanPostProcessors}
 * 中调用{@link AutowiredAnnotationBeanPostProcessor#determineCandidateConstructors}推断构造方法
 * 构造器需要特别明确，spring才能选择。如：只有一个构造器，且参数个数大于0，某个构造器有{@link Autowired}注解，
 * spring才会去解析指定的构造器的入参，如果只是无参构造器，直接使用newInstance即可。
 * 如果是构造器注入模式{@link AbstractBeanDefinition#AUTOWIRE_CONSTRUCTOR}
 * spring会把所有构造器根据参数数量从大到小排序
 * 找到一个能注入的参数最多的构造器，创建对象。
 * {@link Autowired}的优先级比构造器自动注入模式高
 */
@Component
public class AutowireConstructorModeTest {

	private A a;

	@Autowired
	public AutowireConstructorModeTest() {
		System.out.println("b");
	}


	public AutowireConstructorModeTest(@Value("${a}") String a) {
		System.out.println(a);
	}

	public AutowireConstructorModeTest(A a, B b) {
		this.a = a;
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
