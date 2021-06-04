package com.learn.test;

import com.learn.event.CustomEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.stereotype.Component;

@Component
public class EventListenerTest {
	/**
	 * 处理器为{@link EventListenerMethodProcessor}
	 * 可以通过注解的熟悉来筛选监听的事件
	 * 也可以通过入参来筛选监听事件
	 * @param event 要监听的事件
	 */
	@EventListener//(CustomEvent.class)
	public void event(CustomEvent event){
		System.out.println("监听了"+event+"事件");
	}
}
