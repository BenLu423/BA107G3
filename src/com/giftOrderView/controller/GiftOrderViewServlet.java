package com.giftOrderView.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.giftOrder.model.GiftOrderVO;
import com.giftOrderDetail.model.GiftOrderDetailVO;
import com.giftOrderView.model.GiftOrderViewService;
import com.giftOrderView.model.GiftOrderViewVO;
import com.giftReceive.model.GiftReceiveVO;
import com.member.model.MemberVO;

public class GiftOrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println("Order action: " + action);	
		
		if("searchView".equals(action)){
			HttpSession session = req.getSession();
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			//取得當前會員編號
			MemberVO member = (MemberVO)session.getAttribute("memSelf");
			String mem_no_self = member.getMem_no();
			
			try {
				/***********************1.接收請求參數 , 輸入格式的錯誤處理*************************/
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				if(start == null || start.trim().length() == 0){
					errorMsgs.put("start", "請輸入起始日期");
				}
				if(end == null || end.trim().length() == 0){
					errorMsgs.put("end", "請輸入終止日期");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}			
				
				/***************************2.開始查詢歷史訂單資料***************************************/
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateS = df.parse(start);
				java.sql.Date sqlStart = new java.sql.Date(dateS.getTime());
				java.util.Date dateE = df.parse(end);
				java.sql.Date sqlEnd = new java.sql.Date(dateE.getTime() + 60*60*24*1000);
				GiftOrderViewService giftOrderViewSvc = new GiftOrderViewService();
				List<GiftOrderViewVO> orderViewList = giftOrderViewSvc.getAll(mem_no_self, sqlStart, sqlEnd);	
				
				session.setAttribute("orderViewList", orderViewList);
				RequestDispatcher success = req.getRequestDispatcher(requestURL);
				success.forward(req, res);
			} catch (ParseException e) {
				e.printStackTrace(System.err);
			} catch(Exception e){
System.out.println("安安又爆炸惹");
				errorMsgs.put("Exception","查詢View失敗: "+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
	}

}
