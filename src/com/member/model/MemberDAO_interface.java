package com.member.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MemberDAO_interface {
	void memberAdd(MemberVO member);
	void memberUpdate(MemberVO member);
	MemberVO getOne(String mem_no);
	List<MemberVO> getAll();
	MemberVO checkLogin(String mem_account,String mem_password);
	void memberRegister(MemberVO member);
	boolean isMember(String mem_account);
	List<MemberVO> blurSearch(String mem_name);
	List<MemberVO> getAll(Map<String,String[]> map);
	void memIntro(MemberVO member);
	void memModify(MemberVO member);
	void memInsertPic(MemberVO member);
	void memUpdatePassword(String mem_password,String mem_no);
	Boolean memIsBan(String mem_account,String mem_password);
	void memBan(String mem_no,String mem_prohibit);
	List<MemberVO> memPopular();
	//紹永[禮物訂單使用]
	void updateDeposit(String mem_no, Integer delDeposit, Connection con);
	void updateDeposit(String mem_no, Integer delDeposit);
	void updateRecGift(String mem_no, Integer addRecGift, Connection con);
}
