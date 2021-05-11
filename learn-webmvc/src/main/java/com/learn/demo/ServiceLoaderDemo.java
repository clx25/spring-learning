package com.learn.demo;

import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

@Component
public class ServiceLoaderDemo {

	public void load() {
		/**
		 * 获取META-INF\services下的文件名为ServiceLoaderInterface全类名的文件内容
		 * 内容应该为一个或多个ServiceLoaderInterface实现类的全类名
		 * ServiceLoader会创建这些实现类对象并返回，然后就可以遍历调用了
		 */
		ServiceLoader<ServiceLoaderInterface> demos
				= ServiceLoader.load(ServiceLoaderInterface.class);
		demos.forEach(ServiceLoaderInterface::show);

	}
}
