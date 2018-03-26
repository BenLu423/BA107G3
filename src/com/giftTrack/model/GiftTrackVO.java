package com.giftTrack.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class GiftTrackVO implements Serializable{
	private String mem_no;
	private String gift_no;
	private Timestamp giftt_time;
	
	public GiftTrackVO() {
		super();
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getGift_no() {
		return gift_no;
	}

	public void setGift_no(String gift_no) {
		this.gift_no = gift_no;
	}

	public Timestamp getGiftt_time() {
		return giftt_time;
	}

	public void setGiftt_time(Timestamp giftt_time) {
		this.giftt_time = giftt_time;
	}
	
}
