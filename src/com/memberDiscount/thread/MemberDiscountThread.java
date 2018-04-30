package com.memberDiscount.thread;


import java.util.Timer;
import java.util.TimerTask;

import com.member.model.MemberService;
import com.popular_wall.ws.PopularWallWS;

public class MemberDiscountThread{
	PopularWallWS pwws = new PopularWallWS();
	MemberService ms = new MemberService();
	Timer popTimer = null;
	public MemberDiscountThread(){
	}

	public void autoPop() {
		System.out.println("poptop");
		TimerTask task = new TimerTask(){
			@Override
			public void run(){	
				pwws.popReference();
				System.out.println("runpop");
			}
		};
		popTimer = new Timer();
		popTimer.scheduleAtFixedRate(task, 1000, 1*60*1000);

	}
}

