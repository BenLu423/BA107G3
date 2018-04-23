package com.deposit_detail.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import com.deposit.model.DepositVO;
import com.member.model.MemberVO;

public class DepositDetailVO implements Serializable {
	private String depod_no;
	private Timestamp depod_time;
	private MemberVO memberVO;
	private DepositVO depositVO;
	
	public String getDepod_no() {
		return depod_no;
	}
	public void setDepod_no(String depod_no) {
		this.depod_no = depod_no;
	}
	public Timestamp getDepod_time() {
		return depod_time;
	}
	public void setDepod_time(Timestamp depod_time) {
		this.depod_time = depod_time;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public DepositVO getDepositVO() {
		return depositVO;
	}
	public void setDepositVO(DepositVO depositVO) {
		this.depositVO = depositVO;
	}
}
