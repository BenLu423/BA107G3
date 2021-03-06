package com.gift.model;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("serial")
public class GiftVO implements Serializable {
	private String gift_no;
	private String gift_name;
	private String gift_cnt;
	private Integer gift_price;
	private byte[] gift_pic;
	private String gift_is_on;
	private Integer gift_track_qty;
	private Integer gift_buy_qty;

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

	public Integer getGift_track_qty() {
		return gift_track_qty;
	}

	public void setGift_track_qty(Integer gift_track_qty) {
		this.gift_track_qty = gift_track_qty;
	}

	public Integer getGift_buy_qty() {
		return gift_buy_qty;
	}

	public void setGift_buy_qty(Integer gift_buy_qty) {
		this.gift_buy_qty = gift_buy_qty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gift_no == null) ? 0 : gift_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiftVO other = (GiftVO) obj;
		if (gift_no == null) {
			if (other.gift_no != null)
				return false;
		} else if (!gift_no.equals(other.gift_no))
			return false;
		return true;
	}

}
