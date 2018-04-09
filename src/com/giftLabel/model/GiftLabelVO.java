package com.giftLabel.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GiftLabelVO implements Serializable {
	private String giftl_no;
	private String giftl_name;

	public GiftLabelVO() {
		super();
	}

	public String getGiftl_no() {
		return giftl_no;
	}

	public void setGiftl_no(String giftl_no) {
		this.giftl_no = giftl_no;
	}

	public String getGiftl_name() {
		return giftl_name;
	}

	public void setGiftl_name(String giftl_name) {
		this.giftl_name = giftl_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giftl_name == null) ? 0 : giftl_name.hashCode());
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
		GiftLabelVO other = (GiftLabelVO) obj;
		if (giftl_name == null) {
			if (other.giftl_name != null)
				return false;
		} else if (!giftl_name.equals(other.giftl_name))
			return false;
		if (giftl_no == null) {
			if (other.giftl_no != null)
				return false;
		} else if (!giftl_no.equals(other.giftl_no))
			return false;
		return true;
	}
	
}
