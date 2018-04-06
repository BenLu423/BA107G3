package com.giftOrderDetail.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GiftOrderDetailVO implements Serializable {
	String giftod_no;//訂單明細編號
	String gift_no;//禮物編號
	String gifto_no;//訂單編號
	Integer giftod_amount;//購買數量
	Integer giftod_money;//購買金額
	Integer giftod_inventory;//可用剩餘數量

	public GiftOrderDetailVO() {
		super();
	}

	public String getGiftod_no() {
		return giftod_no;
	}

	public void setGiftod_no(String giftod_no) {
		this.giftod_no = giftod_no;
	}

	public String getGift_no() {
		return gift_no;
	}

	public void setGift_no(String gift_no) {
		this.gift_no = gift_no;
	}

	public String getGifto_no() {
		return gifto_no;
	}

	public void setGifto_no(String gifto_no) {
		this.gifto_no = gifto_no;
	}

	public Integer getGiftod_amount() {
		return giftod_amount;
	}

	public void setGiftod_amount(Integer giftod_amount) {
		this.giftod_amount = giftod_amount;
	}

	public Integer getGiftod_money() {
		return giftod_money;
	}

	public void setGiftod_money(Integer giftod_money) {
		this.giftod_money = giftod_money;
	}

	public Integer getGiftod_inventory() {
		return giftod_inventory;
	}

	public void setGiftod_inventory(Integer giftod_inventory) {
		this.giftod_inventory = giftod_inventory;
	}

}
