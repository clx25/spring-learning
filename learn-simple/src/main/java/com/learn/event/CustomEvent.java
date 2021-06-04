package com.learn.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

	public CustomEvent() {
		super("自定义事件");
	}
}
