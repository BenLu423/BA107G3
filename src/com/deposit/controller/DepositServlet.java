package com.deposit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println(action);
		
		//儲值by deposit_index.jsp
		if(("storedValue").equals(action)){
			Gson gson = new Gson();
			Map<String,String> map = new LinkedHashMap<>();			
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			HttpSession session = req.getSession();
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = (MemberVO) session.getAttribute("memSelf");
			int deposit = 0;
			
			String[] money = req.getParameterValues("money");
			for(String str: money){
				Integer oneMoney = new Integer(str);
				deposit += oneMoney;
			}
			memberVO.setMem_deposit(deposit+memberVO.getMem_deposit());
			//因為updateDeposit方法為扣除帳戶金額，故儲值時須*(-1)
			memberSvc.updateDeposit(memberVO.getMem_no(), deposit*(-1));
			req.setAttribute("memSelf", memberVO);
			
			map.put("status", "success");
			out.print(gson.toJson(map));
		}
	}

}
