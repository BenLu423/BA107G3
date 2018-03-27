package com.member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberMain {

	public static void main(String[] args) {
		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = new MemberVO();
		String picstr = "d:/photo/1.png";
		/*新增*/
//		mvo.setMemAccount("aaaa");
//		mvo.setMemPassword("aaaa");
//		mvo.setMemJoinTime(java.sql.Date.valueOf("2018-03-03"));
//		mvo.setMemName("setMemName");
//		mvo.setMemGender("male");
//		mvo.setMemBirthday(java.sql.Date.valueOf("1990-12-31"));
//		mvo.setMemCounty("桃園市");
//		mvo.setMemDeposit(0);
//		mvo.setMemContact("單純交友");
//		mvo.setMemEmotion("已婚");
//		mvo.setMemBonus(0);
//		mvo.setMemBloodType("A");
//		mvo.setMemHeight(220);
//		mvo.setMemWeight(130);
//		mvo.setMemInterest("睡覺,吃飯");
//		mvo.setMemIntro("hello~~~~~~");
//		mvo.setMemOnline("上線");
//		mvo.setMemLongitude(0.0);
//		mvo.setMemLatitude(0.0);
//		mvo.setMemPhone("0966554411");
//		mvo.setMemMail("abcd123@gmail.com");
//		byte[] pic = mdao.getPic(picstr);
//		mvo.setMemPhoto(pic);
//		mvo.setMemProhibit("否");
//		mvo.setMemSetNotify("是");
//		mvo.setMemTimeNotify(java.sql.Date.valueOf("2018-04-04"));
//		mdao.memberAdd(mvo);
		
		/*修改*/
//		MemberVO mvo2 = new MemberVO();
//		mvo2.setMemAccount("update222222");
//		mvo2.setMemPassword("update333333");
//		mvo2.setMemNo("M010");
//		mdao.memberUpdate(mvo2);
		/*查詢*/
//		MemberVO mvo3 = mdao.memberSelect("M002");
//		System.out.println(mvo3.getMemAccount());
//		System.out.println(mvo3.getMemPassword());
		
		/*查詢*/
		List<MemberVO> list = new ArrayList<>();
		list = mdao.getAll();
		for(int x=0;x<list.size();x++){
			System.out.println(list.get(x).getMem_no());
			System.out.println(list.get(x).getMem_account());
			System.out.println(list.get(x).getMem_password());
			System.out.println(list.get(x).getMem_join_time());
			System.out.println(list.get(x).getMem_name());	
		}
		for(MemberVO mvo4 : list){
			System.out.println(mvo4.getMem_account());
			System.out.println(mvo4.getMem_password());
		
		}
//		try{
//			Scanner scn = new Scanner(System.in);
//			String user = scn.nextLine();
//			String pass = scn.nextLine();
//			MemberVO memvo = new MemberVO();
//			memvo = mdao.checkLogin(user, pass);
//		
//					System.out.println("登入成功!!");
//					System.out.println(memvo.getMemAccount());
//					System.out.println(memvo.getMemPassword());
//			
//		}catch(NullPointerException e){
//			System.out.println("輸入錯誤");
//		}
	}
}
