package com.mvc.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener{
	private int usercount;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("!Listener starts!");
		usercount = usercount+1;
		System.out.println("usercount" + usercount);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
