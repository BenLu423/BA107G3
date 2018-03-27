package com.diary_message_report.model;

import java.io.Serializable;
import java.sql.Date;

public class DiaryMessageReportVO implements Serializable {
	private String mem_no;
	private String diam_no;
	private String diamr_reason;
	private String diamr_cnt;
	private Date diamr_time;
	private String diamr_permit;
	 DiaryMessageReportVO(){
		 
	 }
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getDiam_no() {
		return diam_no;
	}
	public void setDiam_no(String diam_no) {
		this.diam_no = diam_no;
	}
	public String getDiamr_reason() {
		return diamr_reason;
	}
	public void setDiamr_reason(String diamr_reason) {
		this.diamr_reason = diamr_reason;
	}
	public String getDiamr_cnt() {
		return diamr_cnt;
	}
	public void setDiamr_cnt(String diamr_cnt) {
		this.diamr_cnt = diamr_cnt;
	}
	public Date getDiamr_time() {
		return diamr_time;
	}
	public void setDiamr_time(Date diamr_time) {
		this.diamr_time = diamr_time;
	}
	public String getDiamr_permit() {
		return diamr_permit;
	}
	public void setDiamr_permit(String diamr_permit) {
		this.diamr_permit = diamr_permit;
	}
	 
	
}
