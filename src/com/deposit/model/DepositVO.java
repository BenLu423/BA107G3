package com.deposit.model;

import java.io.Serializable;

public class DepositVO implements Serializable{
	private String depo_no;
	private String depo_name;
	private Integer depo_value;
	private Double depo_percent;
	public String getDepo_no() {
		return depo_no;
	}
	public void setDepo_no(String depo_no) {
		this.depo_no = depo_no;
	}
	public String getDepo_name() {
		return depo_name;
	}
	public void setDepo_name(String depo_name) {
		this.depo_name = depo_name;
	}
	public Integer getDepo_value() {
		return depo_value;
	}
	public void setDepo_value(Integer depo_value) {
		this.depo_value = depo_value;
	}
	public Double getDepo_percent() {
		return depo_percent;
	}
	public void setDepo_percent(Double depo_percent) {
		this.depo_percent = depo_percent;
	}
}