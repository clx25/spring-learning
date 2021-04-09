package com.learn;


import com.learn.config.*;
import com.learn.controller.InsertTestImpll;
import com.learn.controller.LearnController;
import com.learn.test.LookupTest;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LearnApp {
	public static void main(String[] args) throws NoSuchMethodException {

		//把cglib代理类输出到指定目录
//		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		ac.register(LearnConfig.class);
//		ac.registerBean(LearnConfig.class, ()->{
//					System.out.println("这个是registerBean的Supplier");
//					return new LearnConfig();
//		}, null);
		ac.addBeanFactoryPostProcessor(new MyPostProcessor());
//		ac.addBeanFactoryPostProcessor(new Post6());
		System.setProperty("555", "77");
		ac.addApplicationListener(new TestApplicationListener());
		ac.getEnvironment().setRequiredProperties("555");
//		ac.registerBean("applicationEventMulticaster", TestApplicationEventMulticaster.class);
		ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
//		beanFactory.ignoreDependencyInterface(InsertTest.class);
//		ac.publishEvent(new MyPostProcessor("666"));
		ac.refresh();



//		System.out.println(ac.getEnvironment().getProperty("a"));
		LearnController bean = ac.getBean("learnController", LearnController.class);
		System.out.println(bean.getPost3());

		Post4 post4 = ac.getBean("&post4", Post4.class);
		System.out.println(post4);
		Post6 post6 = ac.getBean("post4", Post6.class);
		System.out.println(post6);
		Post3 post3 = ac.getBean("post3", Post3.class);
		System.out.println(post3);
		PrototypeCircularReference p = ac.getBean("prototypeCircularReference", PrototypeCircularReference.class);
		System.out.println(p);
		InsertTestImpll bean3 = ac.getBean("insertTestImpll",InsertTestImpll.class);
		System.out.println(bean3.getApplicationContext());

		LookupTest lookupTest = ac.getBean("lookupTest", LookupTest.class);
		System.out.println(lookupTest.lookup());

//		ProfileTest profileTest = ac.getBean("profileTest", ProfileTest.class);
//		System.out.println(profileTest);

		ac.getBeanFactory().destroySingletons();
	}


}
