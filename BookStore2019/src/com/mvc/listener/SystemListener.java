package com.mvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mvc.util.DbInfo;

public class SystemListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Listener ends!");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Listener starts!");
		DbInfo dbinfo = DbInfo.getInstance();
	}
	
}
