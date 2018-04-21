package listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.giftDiscount.model.*;
import com.giftDiscount.thread.GiftDiscountThread;

public class GiftTimerInitListener implements ServletContextListener{
	GiftDiscountService giftDiscountSvc = new GiftDiscountService();
	GiftDiscountThread thread = new GiftDiscountThread();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("try");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		for(GiftDiscountVO giftDiscountVO: giftDiscountSvc.getNotStartAll()){
			String gift_no = giftDiscountVO.getGift_no();
			java.sql.Timestamp giftd_start = giftDiscountVO.getGiftd_start();
			//�ҰʱƵ{���A��[�|���}�l]������§���۰ʤW�[
			thread.autoPushGD(gift_no, new java.util.Date(giftd_start.getTime()));
		}
		
	}

}
