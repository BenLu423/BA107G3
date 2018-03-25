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

public class AdminServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			
		AdminService adminSvc = new AdminService();
		List<AdminVO>admins = adminSvc.getAll();
		Map<String ,String >map = new HashMap<String ,String >();//帳密比對用
		AdminVO admin;
		
		for(AdminVO adminvo : admins){
			map.put(adminvo.getAdm_acct(), adminvo.getAdm_pwd());
		}
		
		//測試
//		Set<String> set = map.keySet();
//		Iterator<String> it = set.iterator();
//		while(it.hasNext()){
//			Object myKey = it.next();
//			System.out.println(myKey+"="+map.get(myKey));
//		}
//		
		//測試
		
		
		//比對帳密
		try{
			String account = req.getParameter("account");
			String psw = req.getParameter("pwd");
			
			//檢查是否有填帳密
			if(account==null || (account.trim().length())==0 || psw==null || (psw.trim().length())==0){
				req.setAttribute("errorMsgs", "請輸入帳號密碼");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
				failureView.forward(req,res);
				return;
			}
			//檢查是否有此帳戶
			if(!map.containsKey(account)){
				req.setAttribute("errorMsgs", "無此員工");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
				failureView.forward(req,res);
				return;
			}else{
				//有此帳戶的話比對密碼是否正確
				
				if(!psw.equals(map.get(account))){
					req.setAttribute("errorMsgs", "密碼錯誤");
					req.setAttribute("account", account);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
					failureView.forward(req,res);
					return;
				}else{
					admin = adminSvc.getOneAdmin(account, psw);
					HttpSession session = req.getSession();
					session.setAttribute("admin", admin);
					res.sendRedirect(req.getContextPath()+"/back_end/index.jsp");
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
}
