package com.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");	
		String action = req.getParameter("action");
		
		
		if("getAccount_judge".equals(action)){
			
			List<String> errorMsgs = new ArrayList<String>();
			List<String> lostPassword = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("lostPassword", lostPassword);
			/*�P�_�b���K�X�O�_�ť�*/
			try{
				String mem_account = req.getParameter("mem_account");
				if(mem_account == null || mem_account.trim().isEmpty()){
					errorMsgs.add("�п�J�b��");
					System.out.println("�п�J�b��");
				}
				String mem_password = req.getParameter("mem_password");
				if(mem_password == null || mem_password.trim().isEmpty()){
					lostPassword.add("�п�J�K�X");
					System.out.println("�п�J�K�X");
				}
				/*�p�G���~�T�����O�Ū��N�^�ǿ��~�T��*/
				if(!errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.forward(req, res);
					return;
				}
				/*�P�_��Ʈw�O�_���b���K�X*/
				MemberVO memSelf = new MemberVO();
				MemberService ms = new MemberService();
				memSelf = ms.login(mem_account, mem_password);
				
				if(memSelf == null){
					errorMsgs.add("�L�Ī��b��");
					System.out.println("�L�Ī��b��");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.forward(req, res);
					return;
				}
				
				/*************** �o��|��Session��T**************/
				HttpSession memSession = req.getSession();
				memSession.setAttribute("memSelf", memSelf);
				
				String index = "/personal_page.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(index);
				rd.forward(req, res);
				return;
			}catch(Exception e){
				errorMsgs.add("�L�k���o���");
				System.out.println("�L�k���o���");
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, res);
				return;
			}
			
		}
		
		
		/*�|�����U*/
		if("getregister_judge".equals(action)){
			
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);
				MemberService ms = new MemberService();
			try{
				String mem_account = req.getParameter("mem_account");
				String mem_accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(mem_account == null || mem_account.trim().length() == 0){
					errorMsgs.put("mem_account", "�b��: �ФŪť�");
				}else if(!mem_account.trim().matches(mem_accountReg)){
					errorMsgs.put("mem_account", "�b��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
				}
				if(ms.isMember(mem_account)){
					errorMsgs.put("mem_account", "�b��: �w�s�b");
				}
				String mem_password = req.getParameter("mem_password");
				String mem_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(mem_password == null || mem_password.trim().length() == 0){
					errorMsgs.put("mem_password", "�K�X: �ФŪť�");
				}else if(!mem_password.trim().matches(mem_passwordReg)){
					errorMsgs.put("mem_password", "�K�X: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
				}
				String doublecheck = req.getParameter("doublecheck");
				
				if(!mem_password.equals(doublecheck)){
					errorMsgs.put("doublecheck", "�K�X���@�P");
				}
				String mem_name = req.getParameter("mem_name");
				if(mem_name == null || mem_name.trim().length() == 0){
					errorMsgs.put("mem_name", "�ʺ�: �ФŪť�");
				}
				
				java.sql.Date mem_birthday = null;
				try{
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				}catch(IllegalArgumentException e){
					errorMsgs.put("mem_birthday", "�п�J���");
				}
				
				String mem_gender = req.getParameter("mem_gender");
				if(mem_gender == null || mem_gender.trim().length() == 0){
					errorMsgs.put("mem_gender", "�п�ܩʧO");
				}else if(mem_gender.equals("�k") || mem_gender.equals("�k")){
				}else{
					errorMsgs.put("mem_gender", "�п�ܩʧO123");
				}
				
				
				String mem_bloodtype = req.getParameter("mem_bloodtype");
				if(mem_bloodtype == null || mem_bloodtype.trim().length() == 0){
					errorMsgs.put("mem_bloodtype", "�п�z���嫬");
				}else if(mem_bloodtype.equals("A") || mem_bloodtype.equals("B") || mem_bloodtype.equals("AB") || mem_bloodtype.equals("O")){
				}else{
					errorMsgs.put("mem_bloodtype", "�п�z���嫬");
				}
				
				String mem_county = req.getParameter("mem_county");
				if(mem_county == null || mem_county.trim().length() == 0){
					errorMsgs.put("mem_county", "�п�ܦa��");
				}else if("�򶩥�".equals(mem_county) || "�x�_��".equals(mem_county) || "�s�_��".equals(mem_county) || "��饫".equals(mem_county) || "�s�˥�".equals(mem_county) || "�s�˿�".equals(mem_county) || "�]�߿�".equals(mem_county) || "�x����".equals(mem_county) || "���ƿ�".equals(mem_county) || "�n�뿤".equals(mem_county) || "���L��".equals(mem_county) || "�Ÿq��".equals(mem_county) || "�Ÿq��".equals(mem_county) || "�x�n��".equals(mem_county) || "������".equals(mem_county) || "�̪F��".equals(mem_county) || "�x�F��".equals(mem_county) || "�Ὤ��".equals(mem_county) || "�y����".equals(mem_county)){
				}else{
					errorMsgs.put("mem_county", "�п�ܦa��");
				}
				
				Integer mem_height = null;
				try{
					mem_height = Integer.valueOf(req.getParameter("mem_height").trim());
//					if(mem_height < 120 || mem_height > 220)throw new NumberFormatException();
				}catch(NumberFormatException e){
					errorMsgs.put("mem_height", "�п�ܨ���");
				}
		
				Integer mem_weight = null;
				try{
					mem_weight = Integer.valueOf(req.getParameter("mem_weight").trim());
//					if(mem_weight < 30 || mem_weight > 220)throw new NumberFormatException();
					
				}catch(NumberFormatException e){
					errorMsgs.put("mem_weight", "�п���魫");
				}


				
				String mem_emotion = req.getParameter("mem_emotion");
				if(mem_emotion == null || mem_emotion.trim().length() == 0 || "�п��:".equals(mem_emotion)){
					errorMsgs.put("mem_emotion", "�п�ܷP�����A");
				}

				
				
				String mem_contact = req.getParameter("mem_contact");
				System.out.println(mem_contact);
				if(mem_contact == null || mem_contact.trim().length() == 0 || "�п��:".equals(mem_contact)){
					System.out.println("testcontact");
					errorMsgs.put("mem_contact", "�п�ܷP�����p");
				}
			
							
				if(! errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/member_register.jsp");
					rd.forward(req, res);
					return;
				}
				
				MemberVO memRegister = new MemberVO(); 
				memRegister = ms.memberRegisterService(mem_account, mem_password, mem_name, mem_birthday, mem_bloodtype, mem_gender, mem_county, mem_height, mem_weight, mem_emotion, mem_contact);
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, res);
			}catch(Exception e){
				System.out.println("�L�k���o���");
				RequestDispatcher rd = req.getRequestDispatcher("/member_register.jsp");
				rd.forward(req, res);
			}
			
		}

		
		
		
		
	}

}
