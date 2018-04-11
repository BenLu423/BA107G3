package com.member.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import com.sun.xml.internal.ws.api.model.MEP;

public class MemberService {
	private MemberDAO_interface memdao;
	
	public MemberService(){
		memdao = new MemberDAO();
	}
	/********回傳登入資料********/
	public MemberVO login(String mem_account,String mem_password){
		return memdao.checkLogin(mem_account, mem_password);
	}
	/********回傳帳號是否存在********/
	public boolean isMember(String mem_account){
		return memdao.isMember(mem_account);
	}
	/********回傳會員註冊資料********/
	public MemberVO memberRegisterService(String mem_account, String mem_password, String mem_name, java.sql.Date mem_birthday, String mem_bloodtype, String mem_gender, String mem_county, Integer mem_height, Integer mem_weight, String mem_emotion, String mem_contact){
		MemberVO memvo = new MemberVO();
		memvo.setMem_account(mem_account);
		memvo.setMem_password(mem_password);
		memvo.setMem_name(mem_name);
		memvo.setMem_birthday(mem_birthday);
		memvo.setMem_bloodtype(mem_bloodtype);
		memvo.setMem_gender(mem_gender);
		memvo.setMem_county(mem_county);
		memvo.setMem_height(mem_height);
		memvo.setMem_weight(mem_weight);
		memvo.setMem_emotion(mem_emotion);
		memvo.setMem_contact(mem_contact);
		memdao.memberRegister(memvo);
		return memvo;
	}
	/********得到會員所有資料********/
	public List<MemberVO> getallMem(){
		return memdao.getAll();
	}
	/********得到單筆會員所有資料********/
	public MemberVO getOneMem(String mem_no){
		return memdao.getOne(mem_no);
	}
	
	public MemberVO updateIntro(String mem_intro, String mem_no){
		MemberVO memvo = new MemberVO();
		memvo.setMem_intro(mem_intro);
		memvo.setMem_no(mem_no);
		memdao.memIntro(memvo);
		return memvo; 
	}
	
	public void uodateMember(Map<String,String[]> map){
		Set<String> keys = map.keySet();
		MemberVO memvo = new MemberVO();
		for(String key : keys){
			String value = map.get(key)[0];
			String interest = null;
//			System.out.println(key+":"+value);
				switch (key) {
					case "mem_no" :
						memvo.setMem_no(value);
						System.out.println(memvo.getMem_no());
						break;
					case "mem_name" :
						memvo.setMem_name(value);
						System.out.println(memvo.getMem_name());
						break;
					case "mem_phone" :
						memvo.setMem_phone(value);
						System.out.println(memvo.getMem_phone());
						System.out.println(123);
						break;
					case "mem_mail" :
						memvo.setMem_mail(value);
						System.out.println(memvo.getMem_mail());
						break;
					case "mem_height" :
						Integer height = Integer.valueOf(value);
						memvo.setMem_height(height);
						System.out.println(memvo.getMem_height());
						break;
					case "mem_weight" :
						Integer weight = Integer.valueOf(value);
						memvo.setMem_weight(weight);
						System.out.println(memvo.getMem_weight());
						break;
					case "mem_emotion" :
						memvo.setMem_emotion(value);
						System.out.println(memvo.getMem_emotion());
						break;
					case "mem_contact" :
						memvo.setMem_contact(value);
						System.out.println(memvo.getMem_contact());
						break;
					case "mem_interest" :	
						interest = "";
						memvo.setMem_interest(value);
						System.out.println(memvo.getMem_interest());
						break;	
					default:
						break;
					}	
		}
		memdao.memModify(memvo);
	}
	
	
	public List<MemberVO> blur(String mem_name){
		return memdao.blurSearch(mem_name);
	}
	
	public List<MemberVO> precise(Map<String,String[]> map){
		return memdao.getAll(map);
	}
	
	//紹永[禮物訂單使用]
	public void updateDeposit(String mem_no, Integer delDeposit, Connection con){
		memdao.updateDeposit(mem_no, delDeposit, con);
	}
	
	public void updateRecGift(String mem_no, Integer addRecGift, Connection con) {
		memdao.updateRecGift(mem_no, addRecGift, con);
	}
}
