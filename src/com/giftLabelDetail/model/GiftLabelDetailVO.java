package com.giftLabelDetail.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GiftLabelDetailVO implements Serializable{
	private String gift_no;
	private String giftl_no;
	
	public GiftLabelDetailVO() {
		super();
	}

	public String getGift_no() {
		return gift_no;
	}

	public void setGift_no(String gift_no) {
		this.gift_no = gift_no;
	}

	public String getGiftl_no() {
		return giftl_no;
	}

	public void setGiftl_no(String giftl_no) {
		this.giftl_no = giftl_no;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gift_no == null) ? 0 : gift_no.hashCode());
		result = prime * result + ((giftl_no == null) ? 0 : giftl_no.hashCode());
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
		GiftLabelDetailVO other = (GiftLabelDetailVO) obj;
		if (gift_no == null) {
			if (other.gift_no != null)
				return false;
		} else if (!gift_no.equals(other.gift_no))
			return false;
		if (giftl_no == null) {
			if (other.giftl_no != null)
				return false;
		} else if (!giftl_no.equals(other.giftl_no))
			return false;
		return true;
	}
	
}
