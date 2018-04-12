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
import com.admin_auth.model.AdminAuthVO;
import com.admin_auth.model.AuthService;
import com.auth_feature.model.AuthFeatureService;
import com.auth_feature.model.AuthFeatureVO;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");

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
				

				String mail = req.getParameter("mail");
				if(mail==null || mail.trim().length()==0){
					errorMsgs.add("員工信箱：請勿空白");
				}
				
				String []auths = req.getParameterValues("auth");
				if(auths == null){
					errorMsgs.add("設定權限：請至少選擇一項");
				}
				
				AdminVO adminVO = new AdminVO();
				adminVO.setAdm_acct(account);
				adminVO.setAdm_name(adminName2);
				adminVO.setAdm_mail(mail);
				
				//傳回錯誤訊息以及含有鎘是錯誤的adminVO物件
				if(!errorMsgs.isEmpty()){
					
					req.setAttribute("admin", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/insert_admin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//開始新增資料
				/*1.亂數密碼*/
				StringBuilder pwd = new StringBuilder() ;
				int t;
				for(int i = 0; i < 5 ;i++){
					t = (int)(Math.random()*9)+1;
					pwd.append(t);
				}
				String pwds = pwd.toString();
				
				/*2.新增員工*/
				adminSvc.addAdmin(account, pwds, adminName2,mail);
				/*3.加入權限*/
				AdminVO admin = adminSvc.getOneByAcct(account);
				AuthService authSvc = new AuthService();
				authSvc.addAuth(admin.getAdm_no(), auths);
				
				/*4.寄送密碼*/
				SendMail sendMail = new SendMail();
				sendMail.sendMail(mail, pwds, adminName2);
				
				/*5.新增成功，跳轉到員工頁面*/
				res.sendRedirect(req.getContextPath() + "/back_end/admin/all_admin-catchTag.jsp");
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/insert_admin.jsp");
				failureView.forward(req, res);
			}
		}
		/************** 新增後台員工end **************/
		/************** 刪除後台員工start **************/
		if("delete".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try{
				String adm_no = req.getParameter("adm_no");
				AdminService admSvc = new AdminService();
				AuthService authSvc = new AuthService();
				
				//先刪該員工權限
				List<AuthFeatureVO> auths = admSvc.getAdminAuths(adm_no);//取得員工擁有的權限
				for(AuthFeatureVO auth : auths){
					authSvc.deleteAuth(adm_no, auth.getAuth_no());
				}
				//刪除該員工
				admSvc.deleteAdmin(adm_no);
				
				//成功後跳轉回all_admin
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		/************** 刪除後台員工end **************/
		/************** 將單一員工傳送至修改頁面start **************/
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			String requestURL = req.getParameter("requestURL");
			try{
				String adm_no = req.getParameter("adm_no");
				//以編號取員工物件
				AdminService admSvc = new AdminService();
				AdminVO admin = admSvc.getOneAdmin(adm_no);
				req.setAttribute("admin", admin);
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/admin/update_admin.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		/************** 將單一員工傳送至修改頁面end **************/
		/******************* 修改員工start *********************/
		if("update".equals(action)){

			AdminService adminSvc = new AdminService();
			String requestURL = req.getParameter("requestURL");
			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				
				String adm_no = req.getParameter("adm_no");
				
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
					errorMsgs.add("員工密碼：只能是英文字母和數字，且長度於2到10之間");
				}
				
				String mail = req.getParameter("mail");
				if(mail==null || mail.trim().length()==0){
					errorMsgs.add("員工信箱：請勿空白");
				}
				
				String []auths = req.getParameterValues("auth");
				if(auths == null){
					errorMsgs.add("設定權限：請至少選擇一項");
				}
				
				AdminVO admin = adminSvc.getOneAdmin(adm_no);
				admin.setAdm_name(adminName2);
				admin.setAdm_acct(account);
				admin.setAdm_mail(mail);
				
				//傳回錯誤訊息以及含有鎘是錯誤的adminVO物件
				if(!errorMsgs.isEmpty()){
					
					req.setAttribute("admin", admin);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/update_admin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//開始修改資料
				
				
				adminSvc.updateAdmin(adm_no, account, mail, adminName2);
				
				AuthService authSvc = new AuthService();
				
				//先將全部權限刪除
				List<AuthFeatureVO> adminAuths = adminSvc.getAdminAuths(adm_no);
				for(AuthFeatureVO auth : adminAuths){
					authSvc.deleteAuth(adm_no, auth.getAuth_no());
				}
				
				//再將新的權限送出新增
				authSvc.addAuth(adm_no, auths);
				
				/*3.新增成功，跳轉到員工頁面*/
				req.setAttribute("admin", admin);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		/******************* 修改員工end *********************/
		/******************* 查單一員工start *********************/
		if("search_admin".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String url = req.getParameter("requestURL");
			
			try{
				String search_no = req.getParameter("search_no");
				//檢查是否有輸入編號
				if(search_no==null||(search_no.trim()).length()==0){
					errorMsgs.add("請輸入員工編號");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//檢查有無此帳戶
				AdminService admSvc = new AdminService();
				AdminVO admin = null;
				try{
					admin = admSvc.getOneAdmin(search_no);
				}catch(Exception e){
					errorMsgs.add("查無此員工");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//送出正確員工
				req.setAttribute("admin", admin);
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/admin/one_admin.jsp");
				successView.forward(req, res);
				
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		}
		
		
		/******************* 查單一員工end *********************/
		

	}
}
