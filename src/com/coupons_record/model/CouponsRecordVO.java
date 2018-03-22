package com.coupons_record.model;

import java.io.Serializable;

public class CouponsRecordVO implements Serializable{
	private String mem_no;
	private String coup_no;
	private String coupr_is_use;
	
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
	public String getCoupr_is_use() {
		return coupr_is_use;
	}
	public void setCoupr_is_use(String coupr_is_use) {
		this.coupr_is_use = coupr_is_use;
	}
}

