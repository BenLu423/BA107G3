package com.diary_message.model;

import java.sql.Date;

public class DiaryMessageVO {
	private String diam_no;
	private String mem_no;
	private String dia_no;
	private Date diam_time;
	private String diam_cnt;
	private Integer diam_like;
	private Date diam_restime;
	private String diam_rescnt;
	private Integer diam_reslike;
	DiaryMessageVO(){
		
	}
	public String getDiam_no() {
		return diam_no;
	}
	public void setDiam_no(String diam_no) {
		this.diam_no = diam_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getDia_no() {
		return dia_no;
	}
	public void setDia_no(String dia_no) {
		this.dia_no = dia_no;
	}
	public Date getDiam_time() {
		return diam_time;
	}
	public void setDiam_time(Date diam_time) {
		this.diam_time = diam_time;
	}
	public String getDiam_cnt() {
		return diam_cnt;
	}
	public void setDiam_cnt(String diam_cnt) {
		this.diam_cnt = diam_cnt;
	}
	public Integer getDiam_like() {
		return diam_like;
	}
	public void setDiam_like(Integer diam_like) {
		this.diam_like = diam_like;
	}
	public Date getDiam_restime() {
		return diam_restime;
	}
	public void setDiam_restime(Date diam_restime) {
		this.diam_restime = diam_restime;
	}
	public String getDiam_rescnt() {
		return diam_rescnt;
	}
	public void setDiam_rescnt(String diam_rescnt) {
		this.diam_rescnt = diam_rescnt;
	}
	public Integer getDiam_reslike() {
		return diam_reslike;
	}
	public void setDiam_reslike(Integer diam_reslike) {
		this.diam_reslike = diam_reslike;
	}
	
	
}
