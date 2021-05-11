package com.learn.controller;

import javax.servlet.*;
import java.io.IOException;

public class GenericServletController  extends GenericServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("GenericServletController->GenericServlet");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init GenericServletController");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
}
