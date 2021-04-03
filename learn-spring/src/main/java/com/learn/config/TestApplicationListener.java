package com.learn.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

public class TestApplicationListener implements SmartApplicationListener {
	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return false;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {

	}
}
