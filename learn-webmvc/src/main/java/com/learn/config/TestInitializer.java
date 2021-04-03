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
 * tomcat就会运行这个类，执行onStartup方法
 * @HandlesTypes 这个注解会把value的类或接口的子类（只有子类）注入到onStartup的第一个参数
 */
@HandlesTypes(TestHandlesTypes.class)
public class TestInitializer implements ServletContainerInitializer {
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		System.out.println("执行了TestInitializer");
		System.out.println(Arrays.toString(c.toArray()));
	}
}
