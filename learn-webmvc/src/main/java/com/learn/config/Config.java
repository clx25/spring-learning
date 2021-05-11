package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

@Configuration
@ComponentScan(basePackages = "com.learn")
@EnableWebMvc
public class Config implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		/**
		 * 添加一个{@link DefaultServletHttpRequestHandler}，
		 * 这是一个实现了HttpRequestHandler的controller，
		 * 他的作用是把spring无法处理的请求转发到默认servlet中
		 * spring提供的{@link DispatcherServlet}拦截了"/"，这个规则表示拦截除了静态资源的所有请求
		 * 而{@link DefaultServletHttpRequestHandler}是拦截”/**“，表示拦截一切请求，但是该拦截规则
		 * 的优先级最低，所以，当要获取静态资源时，需要注册这个handler。
		 *
		 */
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		//配置视图解析器寻找jsp文件的路径
		registry.jsp();
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		/**
		 * 文件解析器，但是springmvc不配置这个解析器也能上传文件，所以不知道这个是干什么用的
		 * 目前知道的就是在{@link DispatcherServlet}中，解析器会在其他操作之前判断这个请求是不是文件操作，
		 * 如果是，就会把HttpServletRequest转为MultipartHttpServletRequest，再执行其他操作
		 * 但是没有解析器最终也能转，只是调用链会长很多。
		 */
		return new StandardServletMultipartResolver();
	}



}
