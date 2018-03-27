package com.admin.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.*;
import com.admin_auth.model.AuthService;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		

		/**************** 登入比對start *****************/
		if ("backlogin".equals(action)) {
			// 比對帳密
			try {

				AdminService adminSvc = new AdminService();
				List<AdminVO> admins = adminSvc.getAll();
				AdminVO admin;
				Map<String, String> map = new HashMap<String, String>();// 帳密比對用
				for (AdminVO adminvo : admins) {
					map.put(adminvo.getAdm_acct(), adminvo.getAdm_pwd());
				}
				
				String account = req.getParameter("account");
				String psw = req.getParameter("pwd");

				// 檢查是否有填帳密
				if (account == null || (account.trim().length()) == 0 || psw == null || (psw.trim().length()) == 0) {
					req.setAttribute("errorMsgs", "請輸入帳號密碼");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢查是否有此帳戶
				if (!map.containsKey(account)) {
					req.setAttribute("errorMsgs", "無此員工");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
					failureView.forward(req, res);
					return;
				} else {
					
					// 有此帳戶的話比對密碼是否正確
					if (!psw.equals(map.get(account))) {
						req.setAttribute("errorMsgs", "密碼錯誤");
						req.setAttribute("account", account);
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
						failureView.forward(req, res);
						return;
					} else {
						admin = adminSvc.getOneAdmin(account, psw);
						HttpSession session = req.getSession();
						session.setAttribute("admin", admin);
						res.sendRedirect(req.getContextPath() + "/back_end/index.jsp");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**************** 登入比對end *****************/
		/************** 新增後台員工start **************/
		
		if("insert_admin".equals(action)){
			AdminService adminSvc = new AdminService();
			List<AdminVO> admins = adminSvc.getAll();
			Map<String, String> map = new HashMap<String, String>();// 帳密比對用
			for (AdminVO adminvo : admins) {
				map.put(adminvo.getAdm_acct(), adminvo.getAdm_pwd());
			}
			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//接收請求參數，檢查格式
				req.setCharacterEncoding("Big5");
				String adminName = req.getParameter("adminName");
				String adminName2 = new String(adminName.getBytes("ISO-8859-1"),"Big5");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if(adminName2==null || adminName2.trim().length()==0){
					errorMsgs.add("員工姓名：請勿空白");
				}else if(!adminName2.trim().matches(nameReg)){
					errorMsgs.add("員工姓名：只能是中、英字母和數字，且長度於2到10之間");
				}
				
				String account = req.getParameter("account");
				String reg = "^[a-zA-Z0-9]{2,10}$";
				if(account==null || account.trim().length()==0){
					errorMsgs.add("員工帳號：請勿空白");
				}else if(!account.trim().matches(reg)){
					errorMsgs.add("員工帳號：只能是英文字母和數字，且長度於2到10之間");
				}else if(map.containsKey(account)){
					errorMsgs.add("員工帳號：帳號重複");
				}
				
				String pwd = req.getParameter("pwd");
				if(pwd==null || pwd.trim().length()==0){
					errorMsgs.add("員工密碼：請勿空白");
				}else if(!pwd.trim().matches(reg)){
					errorMsgs.add("員工密碼：只能是英文字母和數字，且長度於2到10之間");
				}
				
				String []auths = req.getParameterValues("auth");
				if(auths == null){
					errorMsgs.add("設定權限：請至少選擇一項");
				}
				
				AdminVO adminVO = new AdminVO();
				adminVO.setAdm_acct(account);
				adminVO.setAdm_name(adminName2);
				
				//傳回錯誤訊息以及含有鎘是錯誤的adminVO物件
				if(!errorMsgs.isEmpty()){
					
					req.setAttribute("admin", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adminInsert.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//開始新增資料
				/*1.新增員工*/
				adminSvc.addAdmin(account, pwd, adminName2);
				/*2.取得新員工資料，加入權限*/
				AdminVO admin = adminSvc.getOneAdmin(account, pwd);
				AuthService authSvc = new AuthService();
				authSvc.addAuths(admin.getAdm_no(), auths);
				/*3.新增成功，跳轉到員工頁面*/
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adminInsert.jsp");
				failureView.forward(req, res);
			}
		}
		/************** 新增後台員工end **************/

	}
}
