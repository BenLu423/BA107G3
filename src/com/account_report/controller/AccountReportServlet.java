package com.account_report.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.JSONObject;

import com.account_report.model.AccountReportService;
import com.account_report.model.AccountReportVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberService;
import com.member.model.MemberVO;



public class AccountReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		JSONObject json = null;
		String action = req.getParameter("action");
		/***********個人頁面檢舉***********/
		System.out.println(action);
		if("prohibit".equals(action)){
			try{
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				Boolean isExist = false;
				String self = req.getParameter("self");
				String other = req.getParameter("other");
				String accrep_reason = req.getParameter("accrep_reason");
				String accrep_cnt = req.getParameter("accrep_cnt");
				System.out.println(self);
				System.out.println(other);
				System.out.println(accrep_reason);
				System.out.println(accrep_cnt);
				AccountReportService ars = new AccountReportService();
				if(self.equals(other)){
					errorMsgs.put("error", "錯誤");
				}
				if(self == null || self.trim().length() == 0){
					errorMsgs.put("self", "編號不存在");
				}
				if(other == null || other.trim().length() == 0){
					errorMsgs.put("other", "編號不存在");
				}
					isExist = ars.isExistProhibit(self, other);
				if(isExist){
					System.out.println("testtest");
					errorMsgs.put("isExist", "以檢舉此人");	
				}
				if(accrep_reason == null || accrep_reason.trim().length() == 0){
					errorMsgs.put("accrep_reason", "請選擇檢舉原因");
				}
				
				if(!(errorMsgs.isEmpty())){
	//				RequestDispatcher rd = req.getRequestDispatcher(req.getContextPath()+"/front_end/member/personal_page.jsp");
	//				rd.forward(req, res);
					json = new JSONObject(errorMsgs);
					out.print(json);
					return;
				}
				ars.AddProhibit(self, other, accrep_reason, accrep_cnt);
				return;	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		res.setCharacterEncoding("Big5");
		/**********查詢檢舉狀態**********/
		if("get_prohibit".equals(action)){
			try{
				String statement = req.getParameter("statement");
				String statement_mod = new String(statement.getBytes("ISO-8859-1"),"Big5");
				System.out.println(statement_mod);
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				if("待審核".equals(statement_mod) || "已封鎖".equals(statement_mod) || ("全部".equals(statement_mod))){
				}else{
					System.out.println("test4");
					errorMsgs.put("errorMsgs", "請選擇格式");
				}
				if(!(errorMsgs.isEmpty())){
					System.out.println("test3");
					req.setAttribute("errorMsgs", errorMsgs);
					res.sendRedirect(req.getContextPath()+"/back_end/forbid/mem_prohibit.jsp");
					return;
				}
				System.out.println("test2");
				AccountReportService ars = new AccountReportService();
				List<AccountReportVO> accreplist = new ArrayList<AccountReportVO>();
				HttpSession accrep = req.getSession();
				if("全部".equals(statement_mod)){
					if(accrep != null){
						accrep.removeAttribute("accreplist");
					}
					accreplist = ars.getAll();
					accrep.setAttribute("accreplist", accreplist);
				}
				else{
					if(accrep != null){
						accrep.removeAttribute("accreplist");
					}
					accreplist = ars.findallStatement(statement_mod);
					accrep.setAttribute("accreplist", accreplist);
				}
				res.sendRedirect(req.getContextPath()+"/back_end/forbid/mem_prohibit.jsp");
				return;
			}catch(Exception e){
				e.printStackTrace();
				res.sendRedirect(req.getContextPath()+"/back_end/forbid/mem_prohibit.jsp");
				return;
			}
		}
//		res.setCharacterEncoding("Big5");
		
		System.out.println(action);
		/*************修改檢舉**************/
		if("update".equals(action)){
			try{
				String self = req.getParameter("self");
				String other = req.getParameter("other");
				String permit = req.getParameter("permit");
				String permit1 = new String(permit.getBytes("ISO-8859-1"),"Big5");
				System.out.println(permit1);
				if(permit1 == null || permit1.trim().length() == 0){
					res.sendRedirect(req.getContextPath()+"/back_end/forbid/getone_mem_prohibit.jsp");
					return;
				}
				AccountReportService ars = new AccountReportService();
				ars.accountReportUpdateStatement(permit1, self, other);
				MemberService ms = new MemberService();
				if("已封鎖".equals(permit1)){
					ms.MemBanBoshan(other, "是");
				}
				if("待審核".equals(permit1)){
					ms.MemBanBoshan(other, "否");
				}
				HttpSession accrep = req.getSession();
				if(accrep != null){
					accrep.removeAttribute("accreplist");
				}
				res.sendRedirect(req.getContextPath()+"/back_end/forbid/mem_prohibit.jsp");
				return;
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			
		}
		/***********刪除檢舉**********/
		if("delete".equals(action)){
			try{
				String self = req.getParameter("self");
				String other = req.getParameter("other");
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				if(self == null || self.trim().length() == 0){
					errorMsgs.put("self", "錯誤");
				}
				if(other == null || other.trim().length() == 0){
					errorMsgs.put("other", "錯誤");
				}
				if(!(errorMsgs.isEmpty())){
					req.setAttribute("errorMsgs", errorMsgs);
					res.sendRedirect(req.getContextPath()+"/back_end/forbid/mem_prohibit.jsp");
					return;
				}
				AccountReportService ars = new AccountReportService();
				HttpSession accrep = req.getSession();
				if(accrep != null){
					accrep.removeAttribute("accreplist");
				}
				ars.prohibitDelete(self, other);
				res.sendRedirect(req.getContextPath()+"/back_end/forbid/mem_prohibit.jsp");
				return;
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
		}
		
		if("get_prohibit".equals(action)){
			try{
			String statement = req.getParameter("statement");
			System.out.println(statement);
			Map<String, String> errorMsgs = new LinkedHashMap<>();
				if(statement == null || statement.trim().length() == 0){
					errorMsgs.put("errorMsgs", "請勿空白");
				}
				if(!(statement.equals("待審核")) || !(statement.equals("已封鎖"))){
					errorMsgs.put("errorMsgs", "請輸入正確值");
				}
				if(!(errorMsgs.isEmpty())){
					RequestDispatcher rd = req.getRequestDispatcher("/back_end/forbid/getone_mem_prohibit.jsp");
					rd.forward(req, res);
					return;
				}
				AccountReportService ars = new AccountReportService();
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			
		}
	}
}
