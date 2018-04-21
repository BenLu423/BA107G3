package com.gift.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.gift.model.GiftService;
import com.gift.model.GiftVO;
import com.giftDiscount.model.GiftDiscountService;
import com.giftDiscount.model.GiftDiscountVO;
import com.giftDiscount.thread.GiftDiscountThread;
import com.google.gson.Gson;

public class GiftServletUTF8 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("GiftUF8 action: " + action);	

		//�Ӧ�gift_disCount.jsp�ШD�d�ߥ��N§��
		if("searchAllGift".equals(action)){
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			String keyword = req.getParameter("keyword");
			GiftService giftSvc = new GiftService();
			List<GiftVO> list = giftSvc.getAll(keyword);
			out.print(gson.toJson(list));
		}
		
		//�Ӧ�gift_disCount.jsp�ШD�s�W�h�������u�f§��
		if("addGD".equals(action)){
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			Map<String,String> successMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				java.sql.Timestamp giftd_start = null;
				String strStart = req.getParameter("start_dateTime");
				try{
					java.util.Date date = df.parse(strStart);
					giftd_start = new java.sql.Timestamp(date.getTime());
				} catch(ParseException e){
					errorMsgs.put("giftd_start","�}�l�����J���~");
				}
				
				java.sql.Timestamp giftd_end = null;
				String strEnd = req.getParameter("end_dateTime");
				try{
					java.util.Date date = df.parse(strEnd);
					giftd_end = new java.sql.Timestamp(date.getTime());
				} catch(ParseException e){
					errorMsgs.put("giftd_end","���������J���~");
				}
				Double giftd_percent = null;
				try {
					giftd_percent = new Double(req.getParameter("giftd_percent").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("giftd_percent","�ƶq�ж�0.00-1.00����");
				}
				
				Integer giftd_amount = null;
				try {
					giftd_amount = new Integer(req.getParameter("giftd_amount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("giftd_amount","�ƶq�ж���");
				}
				
				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("status", "failure");
					out.print(gson.toJson(errorMsgs));
					return;//�{�����_
				}
				
				GiftDiscountThread thread = new GiftDiscountThread();
				GiftDiscountService giftDiscountSvc = new GiftDiscountService();
				GiftDiscountVO giftDiscountVO = null;
				String[] gift_no_array = req.getParameterValues("gift_no");
				for(String gift_no: gift_no_array){
					giftDiscountVO = new GiftDiscountVO();
					giftDiscountVO.setGift_no(gift_no);
					giftDiscountVO.setGiftd_start(giftd_start);
					giftDiscountVO.setGiftd_end(giftd_end);
					giftDiscountVO.setGiftd_percent(giftd_percent);
					giftDiscountVO.setGiftd_amount(giftd_amount);
					giftDiscountSvc.add(giftDiscountVO);
					
					//�ҰʱƵ{���A������§���۰ʤW�[
					thread.autoPushGD(gift_no, new java.util.Date(giftd_start.getTime()));
				}
				successMsgs.put("status", "success");
				out.print(gson.toJson(successMsgs));
			}catch(Exception e){
				errorMsgs.put("Exception", e.getMessage());
				out.print(gson.toJson(errorMsgs));
			}
			
			
			
			
			
//			GiftService giftSvc = new GiftService();
//			List<GiftVO> list = giftSvc.getAll(keyword);
//			out.print(gson.toJson(list));
		}
		

	}

}
