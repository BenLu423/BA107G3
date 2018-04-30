package com.memberDiscount.thread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.popular_wall.ws.PopularWallWS;

public class MemberDiscountListener implements ServletContextListener{
	PopularWallWS pwws = new PopularWallWS();
	MemberDiscountThread mdThread = new MemberDiscountThread();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		mdThread.popTimer.cancel();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		mdThread.autoPop();
	}

}
