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
		memvo.setMem_longitude(120.7908715);
		memvo.setMem_latitude(22.087433);
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
	
	/*修改密碼*/
	public void updatePass(String mem_password,String mem_no){
		memdao.memUpdatePassword(mem_password, mem_no);
	}
	/*自我介紹*/
	public MemberVO updateIntro(String mem_intro, String mem_no){
		MemberVO memvo = new MemberVO();
		memvo.setMem_intro(mem_intro);
		memvo.setMem_no(mem_no);
		memdao.memIntro(memvo);
		return memvo; 
	}
	/*會員修改*/
	public void uodateMember(Map<String,String[]> map){
		Set<String> keys = map.keySet();
		MemberVO memvo = new MemberVO();
		StringBuffer sb = new StringBuffer();
		for(String key : keys){
			String value = map.get(key)[0];	
				switch (key) {
					case "mem_no" :
						memvo.setMem_no(value);
						break;
					case "mem_name" :
						memvo.setMem_name(value);
						break;
					case "mem_phone" :
						memvo.setMem_phone(value);
						break;
					case "mem_mail" :
						memvo.setMem_mail(value);
						System.out.println(memvo.getMem_mail());
						break;
					case "mem_height" :
						Integer height = Integer.valueOf(value);
						memvo.setMem_height(height);
						break;
					case "mem_weight" :
						Integer weight = Integer.valueOf(value);
						memvo.setMem_weight(weight);
						break;
					case "mem_emotion" :
						memvo.setMem_emotion(value);
						break;
					case "mem_contact" :
						memvo.setMem_contact(value);
						break;
					case "mem_interest1":
					case "mem_interest2":
					case "mem_interest3":
					case "mem_interest4":
					case "mem_interest5":
					case "mem_interest6":
					case "mem_interest7":
					case "mem_interest8":
						sb.append(value + ",");
						memvo.setMem_interest(sb.toString());
						System.out.println(memvo.getMem_interest());
						break;	
					default:
						break;
					}
		}
		memdao.memModify(memvo);
	}
	/*上傳大頭貼*/
	public void uploadPic(byte[] mem_photo,String mem_no){
		MemberVO memvo = new MemberVO();
		memvo.setMem_photo(mem_photo);
		memvo.setMem_no(mem_no);
		memdao.memInsertPic(memvo);
	}
	
	/*基本查詢*/
	public List<MemberVO> blur(String mem_name){
		return memdao.blurSearch(mem_name);
	}
	/*進階查詢*/
	public List<MemberVO> precise(Map<String,String[]> map){
		return memdao.getAll(map);
	}
	/*會員是否為黑名單*/
	public Boolean memIsBan(String mem_account, String mem_password){
		return memdao.memIsBan(mem_account, mem_password);
	}
	/*封鎖會員*/
	public void MemBanBoshan(String mem_no, String mem_prohibit){
		memdao.memBan(mem_no, mem_prohibit);
	}
	//紹永[禮物訂單使用]
	public void updateDeposit(String mem_no, Integer delDeposit, Connection con){
		memdao.updateDeposit(mem_no, delDeposit, con);
	}

	public void updateDeposit(String mem_no, Integer delDeposit){
		memdao.updateDeposit(mem_no, delDeposit);
	}
	
	public void updateRecGift(String mem_no, Integer addRecGift, Connection con) {
		memdao.updateRecGift(mem_no, addRecGift, con);
	}
}
