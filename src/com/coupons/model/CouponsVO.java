package com.coupons.model;

import java.io.Serializable;
import java.sql.Date;

public class CouponsVO implements Serializable {
	
	private String coup_no;
	private String coup_name;
	private String coup_serial;
	private Integer coup_value;
	private Integer coup_thr;
	private Date coup_start;
	private Date coup_end;
	
	public String getCoup_no() {
		return coup_no;
	}
	public void setCoup_no(String coup_no) {
		this.coup_no = coup_no;
	}
	public String getCoup_name() {
		return coup_name;
	}
	public void setCoup_name(String coup_name) {
		this.coup_name = coup_name;
	}
	public String getCoup_serial() {
		return coup_serial;
	}
	public void setCoup_serial(String coup_serial) {
		this.coup_serial = coup_serial;
	}
	public Integer getCoup_value() {
		return coup_value;
	}
	public void setCoup_value(Integer coup_value) {
		this.coup_value = coup_value;
	}
	public Integer getCoup_thr() {
		return coup_thr;
	}
	public void setCoup_thr(Integer coup_thr) {
		this.coup_thr = coup_thr;
	}
	public Date getCoup_start() {
		return coup_start;
	}
	public void setCoup_start(Date coup_start) {
		this.coup_start = coup_start;
	}
	public Date getCoup_end() {
		return coup_end;
	}
	public void setCoup_end(Date coup_end) {
		this.coup_end = coup_end;
	}
	
}
