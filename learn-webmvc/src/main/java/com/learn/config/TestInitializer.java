package com.learn.config;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Arrays;
import java.util.Set;
/**
 * tomcat在启动时会扫描所有项目META-INF/services/javax.servlet.ServletContainerInitializer这个文件
 * 这个文件中保存了一个实现了ServletContainerInitializer接口的类的全路径
 * tomcat就会执行onStartup方法
 */
@HandlesTypes(TestHandlesTypes.class)
public class TestInitializer implements ServletContainerInitializer {
	/**
	 *
	 * @param c {@link HandlesTypes}注解中的类(接口)的子类(接口)
	 * @param ctx ServletContext域，一个web应用只有一个ServletContext域，所有servlet共享
	 *           可以使用该域在启动时注册组件（Filter Listener UserServlet）
	 */
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		System.out.println("执行了TestInitializer");
		System.out.println(Arrays.toString(c.toArray()));

	}
}
