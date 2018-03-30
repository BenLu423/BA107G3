package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	void memberAdd(MemberVO member);
	void memberUpdate(MemberVO member);
	MemberVO memberSelect(String mem_account);
	List<MemberVO> getAll();
	MemberVO checkLogin(String mem_account,String mem_password);
	void memberRegister(MemberVO member);
	boolean isMember(String mem_account);
}
