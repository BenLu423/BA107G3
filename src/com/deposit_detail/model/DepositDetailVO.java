package com.deposit_detail.model;

import java.io.Serializable;
import java.sql.Time;

public class DepositDetailVO implements Serializable {
	private String depod_no;
	private String mem_no;
	private String depo_no;
	private Time depod_time;
	
	public String getDepod_no() {
		return depod_no;
	}
	public void setDepod_no(String depod_no) {
		this.depod_no = depod_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getDepo_no() {
		return depo_no;
	}
	public void setDepo_no(String depo_no) {
		this.depo_no = depo_no;
	}
	public Time getDepod_time() {
		return depod_time;
	}
	public void setDepod_time(Time depod_time) {
		this.depod_time = depod_time;
	}
}
