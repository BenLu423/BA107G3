package com.giftOrder.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class GiftOrderVO implements Serializable {
	String gifto_no;//訂單編號
	String mem_no;//會員編號
	String coup_no;//折價券編號
	Timestamp gifto_time;//訂單時間
	String gifto_remark;//訂單備註

	public GiftOrderVO() {
		super();
	}

	public String getGifto_no() {
		return gifto_no;
	}

	public void setGifto_no(String gifto_no) {
		this.gifto_no = gifto_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getCoup_no() {
		return coup_no;
	}

	public void setCoup_no(String coup_no) {
		this.coup_no = coup_no;
	}

	public Timestamp getGifto_time() {
		return gifto_time;
	}

	public void setGifto_time(Timestamp gifto_time) {
		this.gifto_time = gifto_time;
	}

	public String getGifto_remark() {
		return gifto_remark;
	}

	public void setGifto_remark(String gifto_remark) {
		this.gifto_remark = gifto_remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gifto_no == null) ? 0 : gifto_no.hashCode());
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
		GiftOrderVO other = (GiftOrderVO) obj;
		if (gifto_no == null) {
			if (other.gifto_no != null)
				return false;
		} else if (!gifto_no.equals(other.gifto_no))
			return false;
		return true;
	}

}
