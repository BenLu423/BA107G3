package com.gift.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GiftVO implements Serializable {
	private String gift_no;
	private String gift_name;
	private String gift_cnt;
	private Integer gift_price;
	private byte[] gift_pic;
	private String gift_is_on;

	public GiftVO() {
		super();
	}

	public String getGift_no() {
		return gift_no;
	}

	public void setGift_no(String gift_no) {
		this.gift_no = gift_no;
	}

	public String getGift_name() {
		return gift_name;
	}

	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}

	public String getGift_cnt() {
		return gift_cnt;
	}

	public void setGift_cnt(String gift_cnt) {
		this.gift_cnt = gift_cnt;
	}

	public Integer getGift_price() {
		return gift_price;
	}

	public void setGift_price(Integer gift_price) {
		this.gift_price = gift_price;
	}

	public byte[] getGift_pic() {
		return gift_pic;
	}

	public void setGift_pic(byte[] gift_pic) {
		this.gift_pic = gift_pic;
	}

	public String getGift_is_on() {
		return gift_is_on;
	}

	public void setGift_is_on(String gift_is_on) {
		this.gift_is_on = gift_is_on;
	}
	
}
