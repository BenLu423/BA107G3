package android.com.giftbox.model;

import java.io.Serializable;

public class GiftboxVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mem_no_self;
	private String gift_no;
	private Integer giftr_amount;
	
	
	public GiftboxVO() {
		super();
	}


	public String getMem_no_self() {
		return mem_no_self;
	}


	public void setMem_no_self(String mem_no_self) {
		this.mem_no_self = mem_no_self;
	}


	public String getGift_no() {
		return gift_no;
	}


	public void setGift_no(String gift_no) {
		this.gift_no = gift_no;
	}


	public Integer getGiftr_amount() {
		return giftr_amount;
	}


	public void setGiftr_amount(Integer giftr_amount) {
		this.giftr_amount = giftr_amount;
	}
	

}
