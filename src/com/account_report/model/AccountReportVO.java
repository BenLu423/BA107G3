package com.account_report.model;

import java.sql.Date;

public class AccountReportVO {
	private String mem_no_self;
	private String mem_no_other;
	private String accrep_reason;
	private String accrep_cnt;
	private Date accrep_time;
	private String accrep_permit;
	public AccountReportVO(){
		
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
	public String getAccrep_reason() {
		return accrep_reason;
	}
	public void setAccrep_reason(String accrep_reason) {
		this.accrep_reason = accrep_reason;
	}
	public String getAccrep_cnt() {
		return accrep_cnt;
	}
	public void setAccrep_cnt(String accrep_cnt) {
		this.accrep_cnt = accrep_cnt;
	}
	public Date getAccrep_time() {
		return accrep_time;
	}
	public void setAccrep_time(Date accrep_time) {
		this.accrep_time = accrep_time;
	}
	public String getAccrep_permit() {
		return accrep_permit;
	}
	public void setAccrep_permit(String accrep_permit) {
		this.accrep_permit = accrep_permit;
	}
}
