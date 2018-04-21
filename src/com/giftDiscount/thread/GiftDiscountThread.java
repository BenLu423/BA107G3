package com.giftDiscount.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.gift.model.*;
import com.gift.ws.GiftStatusWS;

public class GiftDiscountThread {
	GiftStatusWS giftStatusWS = new GiftStatusWS();
	GiftService giftSvc = new GiftService();
	public GiftDiscountThread() {
	}
	
	public void autoPushGD(String gift_no, Date startTime){
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				GiftVO giftVO = giftSvc.getOneGift(gift_no);
				String gift_is_on = giftVO.getGift_is_on();
				if("上架中".equals(gift_is_on)){
					giftStatusWS.broadcast("deleteGift", gift_no);
					giftStatusWS.broadcast("insertGift", gift_no);
				}
			}
			
		};
		Timer timer = new Timer();
		timer.schedule(task, startTime);
		System.out.println("已建立禮物["+gift_no+"]的上架排程! time: "+ startTime);
	}
}
