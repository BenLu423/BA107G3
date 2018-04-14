package com.giftReceive.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class GiftReceiveVO implements Serializable {
	String giftr_no;// ����§�s��
	String mem_no_self;// �|���s��(��§�H)
	String mem_no_other;// �|���s��(��§�H)
	String giftod_no;// �q����ӽs��
	Integer giftr_amount;// ����§�ƶq
	Timestamp giftr_time;// ����§�ɶ�
	String giftr_is_found;// ����§�O�_����
	String giftr_is_open;// ����§�O�_���}
	String giftr_notice;// ����§�q��
	String giftr_message;// ����§�d��

	public GiftReceiveVO() {
		super();
	}

	public String getGiftr_no() {
		return giftr_no;
	}

	public void setGiftr_no(String giftr_no) {
		this.giftr_no = giftr_no;
	}

	public String getMem_no_self() {
		return mem_no_self;
	}

	public void setMem_no_self(String mem_no_self) {
		this.mem_no_self = mem_no_self;
	}

	public String getMem_no_other() {
		return mem_no_other;
	}

	public void setMem_no_other(String mem_no_other) {
		this.mem_no_other = mem_no_other;
	}

	public String getGiftod_no() {
		return giftod_no;
	}

	public void setGiftod_no(String giftod_no) {
		this.giftod_no = giftod_no;
	}

	public Integer getGiftr_amount() {
		return giftr_amount;
	}

	public void setGiftr_amount(Integer giftr_amount) {
		this.giftr_amount = giftr_amount;
	}

	public Timestamp getGiftr_time() {
		return giftr_time;
	}

	public void setGiftr_time(Timestamp giftr_time) {
		this.giftr_time = giftr_time;
	}

	public String getGiftr_is_found() {
		return giftr_is_found;
	}

	public void setGiftr_is_found(String giftr_is_found) {
		this.giftr_is_found = giftr_is_found;
	}

	public String getGiftr_is_open() {
		return giftr_is_open;
	}

	public void setGiftr_is_open(String giftr_is_open) {
		this.giftr_is_open = giftr_is_open;
	}

	public String getGiftr_notice() {
		return giftr_notice;
	}

	public void setGiftr_notice(String giftr_notice) {
		this.giftr_notice = giftr_notice;
	}

	public String getGiftr_message() {
		return giftr_message;
	}

	public void setGiftr_message(String giftr_message) {
		this.giftr_message = giftr_message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giftr_no == null) ? 0 : giftr_no.hashCode());
		result = prime * result + ((mem_no_other == null) ? 0 : mem_no_other.hashCode());
		result = prime * result + ((mem_no_self == null) ? 0 : mem_no_self.hashCode());
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
		GiftReceiveVO other = (GiftReceiveVO) obj;
		if (giftr_no == null) {
			if (other.giftr_no != null)
				return false;
		} else if (!giftr_no.equals(other.giftr_no))
			return false;
		if (mem_no_other == null) {
			if (other.mem_no_other != null)
				return false;
		} else if (!mem_no_other.equals(other.mem_no_other))
			return false;
		if (mem_no_self == null) {
			if (other.mem_no_self != null)
				return false;
		} else if (!mem_no_self.equals(other.mem_no_self))
			return false;
		return true;
	}

}
