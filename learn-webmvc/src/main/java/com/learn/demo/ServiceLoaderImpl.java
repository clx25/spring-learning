package com.learn.demo;

public class ServiceLoaderImpl implements ServiceLoaderInterface{
	@Override
	public void show() {
		System.out.println("ServiceLoaderImpl->ServiceLoaderInterface");
	}
}
