package com.learn.config;

import com.learn.controller.GenericServletController;
import com.learn.demo.ServiceLoaderDemo;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.util.ServiceLoader;

/**
 * servlet3.0会扫描所有包META-INF/services/javax.servlet.ServletContainerInitializer文件
 * 这个功能由{@link org.apache.catalina.startup.WebappServiceLoader}实现
 * jdk的{@link ServiceLoader}可以实现相同的功能，具体使用看{@link ServiceLoaderDemo}
 * 执行这个文件里配置的类的onStartup方法 ，在spring-web中配置了{@link SpringServletContainerInitializer}
 *
 * @see SpringServletContainerInitializer
 */
public class InitWeb {
//		implements WebApplicationInitializer {

	/**
	 * 注册DispatcherServlet与配置类,也可以继承spring提供的 {@link AbstractAnnotationConfigDispatcherServletInitializer}
	 * 里面的逻辑与下面这个方法相同
	 * 其他配置可以实现{@link WebMvcConfigurer}接口
	 */
//	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

//		servletContext.setAttribute("allowCasualMultipartParsing", true);
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(Config.class);

		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);

		//使用servlet3.0实现文件解析需要添加这个配置，就是给文件解析器添加属性
		registration.setMultipartConfig(new MultipartConfigElement(""));
		//拦截除了静态资源的请求，”/*“拦截所有请求，包括静态资源
		registration.addMapping("/");
		/**
		 * 设置启动优先级，只要这个值大于等于0，表示这个servlet会在{@link ServletContext}初始化期间实例化和初始化,
		 * 	在servlet初始化时会调用init()。在顶级接口{@link javax.servlet.Servlet}中定义了init(),destroy()
		 * 所以在初始化{@link DispatcherServlet}时会调用父类的init()，在这个init()中，会调用spring的refresh()，完成spring的初始化。
		 */
		registration.setLoadOnStartup(1);

		/**
		 * 这是一个直接实现{@link GenericServlet}的类，并设置了大于等于0的启动优先级
		 * 所以在tomcat启动期间会调用该类的init()
		 */
		ServletRegistration.Dynamic myserlet = servletContext.addServlet("g", new GenericServletController());
		myserlet.setLoadOnStartup(2);
	}
}
