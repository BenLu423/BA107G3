package com.gift.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gift.model.GiftService;
import com.gift.model.GiftVO;
import com.giftLabel.model.GiftLabelVO;

public class GiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1287527750058413992L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
		//來自gift_index.jsp的查詢請求
		if("searchGifts".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			HttpSession session = req.getSession();
			Map<String, String[]> giftQuery = (Map<String, String[]>)session.getAttribute("giftQuery");
			if(req.getParameter("whichPage") == null){
				HashMap<String, String[]> map = new HashMap<String, String[]> (req.getParameterMap());
				session.setAttribute("giftQuery", map);
				giftQuery = map;
			}
			
			GiftService giftSvc = new GiftService();
			Map<GiftVO, List<GiftLabelVO>> gifts = giftSvc.getGiftAll(giftQuery);
			req.setAttribute("gifts", gifts);
			
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			successView.forward(req, res);
		}
		
		//來自gift_list.jsp的刪除請求
		if("deleteOneGift".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try{
				String gift_no = req.getParameter("gift_no");
				GiftService giftSvc = new GiftService();
				giftSvc.deleteGift(gift_no);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e){
				System.out.println("error");
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

}
