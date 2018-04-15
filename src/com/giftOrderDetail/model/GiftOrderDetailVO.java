package com.giftOrderDetail.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GiftOrderDetailVO implements Serializable {
	String giftod_no;// 訂單明細編號
	String gift_no;// 禮物編號
	String gifto_no;// 訂單編號
	String giftd_no;// 限時優惠編號
	Integer giftod_unit;// 購買單價
	Integer giftod_amount;// 購買數量
	Integer giftod_money;// 購買小計
	Integer giftod_inventory;// 可用剩餘數量

	public GiftOrderDetailVO() {
		super();
	}

	public String getGiftod_no() {
		return giftod_no;
	}

	public String getGiftd_no() {
		return giftd_no;
	}

	public void setGiftd_no(String giftd_no) {
		this.giftd_no = giftd_no;
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

	public Integer getGiftod_unit() {
		return giftod_unit;
	}

	public void setGiftod_unit(Integer giftod_unit) {
		this.giftod_unit = giftod_unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gift_no == null) ? 0 : gift_no.hashCode());
		result = prime * result + ((gifto_no == null) ? 0 : gifto_no.hashCode());
		result = prime * result + ((giftod_no == null) ? 0 : giftod_no.hashCode());
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
		GiftOrderDetailVO other = (GiftOrderDetailVO) obj;
		if (gift_no == null) {
			if (other.gift_no != null)
				return false;
		} else if (!gift_no.equals(other.gift_no))
			return false;
		if (gifto_no == null) {
			if (other.gifto_no != null)
				return false;
		} else if (!gifto_no.equals(other.gifto_no))
			return false;
		if (giftod_no == null) {
			if (other.giftod_no != null)
				return false;
		} else if (!giftod_no.equals(other.giftod_no))
			return false;
		return true;
	}

}
