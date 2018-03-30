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
			/*判斷帳號密碼是否空白*/
			try{
				String mem_account = req.getParameter("mem_account");
				if(mem_account == null || mem_account.trim().isEmpty()){
					errorMsgs.add("請輸入帳號");
					System.out.println("請輸入帳號");
				}
				String mem_password = req.getParameter("mem_password");
				if(mem_password == null || mem_password.trim().isEmpty()){
					lostPassword.add("請輸入密碼");
					System.out.println("請輸入密碼");
				}
				/*如果錯誤訊息不是空的就回傳錯誤訊息*/
				if(!errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.forward(req, res);
					return;
				}
				/*判斷資料庫是否有帳號密碼*/
				MemberVO memSelf = new MemberVO();
				MemberService ms = new MemberService();
				memSelf = ms.login(mem_account, mem_password);
				
				if(memSelf == null){
					errorMsgs.add("無效的帳號");
					System.out.println("無效的帳號");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.forward(req, res);
					return;
				}
				
				/*************** 得到會員Session資訊**************/
				HttpSession memSession = req.getSession();
				memSession.setAttribute("memSelf", memSelf);
				
				String index = "/personal_page.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(index);
				rd.forward(req, res);
				return;
			}catch(Exception e){
				errorMsgs.add("無法取得資料");
				System.out.println("無法取得資料");
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.forward(req, res);
				return;
			}
			
		}
		
		
		/*會員註冊*/
		if("getregister_judge".equals(action)){
			
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);
				MemberService ms = new MemberService();
			try{
				String mem_account = req.getParameter("mem_account");
				String mem_accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(mem_account == null || mem_account.trim().length() == 0){
					errorMsgs.put("mem_account", "帳號: 請勿空白");
				}else if(!mem_account.trim().matches(mem_accountReg)){
					errorMsgs.put("mem_account", "帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到15之間");
				}
				if(ms.isMember(mem_account)){
					errorMsgs.put("mem_account", "帳號: 已存在");
				}
				String mem_password = req.getParameter("mem_password");
				String mem_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(mem_password == null || mem_password.trim().length() == 0){
					errorMsgs.put("mem_password", "密碼: 請勿空白");
				}else if(!mem_password.trim().matches(mem_passwordReg)){
					errorMsgs.put("mem_password", "密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到15之間");
				}
				String doublecheck = req.getParameter("doublecheck");
				
				if(!mem_password.equals(doublecheck)){
					errorMsgs.put("doublecheck", "密碼不一致");
				}
				String mem_name = req.getParameter("mem_name");
				if(mem_name == null || mem_name.trim().length() == 0){
					errorMsgs.put("mem_name", "暱稱: 請勿空白");
				}
				
				java.sql.Date mem_birthday = null;
				try{
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				}catch(IllegalArgumentException e){
					errorMsgs.put("mem_birthday", "請輸入日期");
				}
				
				String mem_gender = req.getParameter("mem_gender");
				if(mem_gender == null || mem_gender.trim().length() == 0){
					errorMsgs.put("mem_gender", "請選擇性別");
				}else if(mem_gender.equals("男") || mem_gender.equals("女")){
				}else{
					errorMsgs.put("mem_gender", "請選擇性別123");
				}
				
				
				String mem_bloodtype = req.getParameter("mem_bloodtype");
				if(mem_bloodtype == null || mem_bloodtype.trim().length() == 0){
					errorMsgs.put("mem_bloodtype", "請選您的血型");
				}else if(mem_bloodtype.equals("A") || mem_bloodtype.equals("B") || mem_bloodtype.equals("AB") || mem_bloodtype.equals("O")){
				}else{
					errorMsgs.put("mem_bloodtype", "請選您的血型");
				}
				
				String mem_county = req.getParameter("mem_county");
				if(mem_county == null || mem_county.trim().length() == 0){
					errorMsgs.put("mem_county", "請選擇地區");
				}else if("基隆市".equals(mem_county) || "台北市".equals(mem_county) || "新北市".equals(mem_county) || "桃園市".equals(mem_county) || "新竹市".equals(mem_county) || "新竹縣".equals(mem_county) || "苗栗縣".equals(mem_county) || "台中市".equals(mem_county) || "彰化縣".equals(mem_county) || "南投縣".equals(mem_county) || "雲林縣".equals(mem_county) || "嘉義市".equals(mem_county) || "嘉義縣".equals(mem_county) || "台南市".equals(mem_county) || "高雄市".equals(mem_county) || "屏東縣".equals(mem_county) || "台東縣".equals(mem_county) || "花蓮市".equals(mem_county) || "宜蘭市".equals(mem_county)){
				}else{
					errorMsgs.put("mem_county", "請選擇地區");
				}
				
				Integer mem_height = null;
				try{
					mem_height = Integer.valueOf(req.getParameter("mem_height").trim());
//					if(mem_height < 120 || mem_height > 220)throw new NumberFormatException();
				}catch(NumberFormatException e){
					errorMsgs.put("mem_height", "請選擇身高");
				}
		
				Integer mem_weight = null;
				try{
					mem_weight = Integer.valueOf(req.getParameter("mem_weight").trim());
//					if(mem_weight < 30 || mem_weight > 220)throw new NumberFormatException();
					
				}catch(NumberFormatException e){
					errorMsgs.put("mem_weight", "請選擇體重");
				}


				
				String mem_emotion = req.getParameter("mem_emotion");
				if(mem_emotion == null || mem_emotion.trim().length() == 0 || "請選擇:".equals(mem_emotion)){
					errorMsgs.put("mem_emotion", "請選擇感情狀態");
				}

				
				
				String mem_contact = req.getParameter("mem_contact");
				System.out.println(mem_contact);
				if(mem_contact == null || mem_contact.trim().length() == 0 || "請選擇:".equals(mem_contact)){
					System.out.println("testcontact");
					errorMsgs.put("mem_contact", "請選擇感情狀況");
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
				System.out.println("無法取得資料");
				RequestDispatcher rd = req.getRequestDispatcher("/member_register.jsp");
				rd.forward(req, res);
			}
			
		}

		
		
		
		
	}

}
