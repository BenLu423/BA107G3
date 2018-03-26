package com.giftDiscount.model;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestGiftDiscountService")
public class TestGiftDiscountService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.setContentType("text/html; charset=BIG5");
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftDiscountService gDSvc = new GiftDiscountService();

//���ռW�[�@�������u�f		
//		out.println("=======================���ռW�[�@�������u�f==============================");
//		GiftDiscountVO insertVO = new GiftDiscountVO();
//		java.sql.Timestamp timestamp_start = new java.sql.Timestamp(System.currentTimeMillis());
//		java.sql.Timestamp timestamp_end = new java.sql.Timestamp(System.currentTimeMillis()+86400000);
//		
//		insertVO.setGift_no("G013");
//		insertVO.setGiftd_start(timestamp_start);
//		insertVO.setGiftd_end(timestamp_end);
//		insertVO.setGiftd_percent(0.15);
//		insertVO.setGiftd_amount(999);
//		gDSvc.addGiftDiscount(insertVO);
//		out.println("���\�s�W�@�������u�f");
//		out.println("===================================================================");
		
//		//���խק�@�������u�f		
//		out.println("=======================���խק�@�������u�f==============================");
//		String giftd_no = "GD002";
//		GiftDiscountVO insertVO = gDSvc.getOneGiftDiscount(giftd_no);
//		java.sql.Timestamp timestamp_start = new java.sql.Timestamp(System.currentTimeMillis());
//		java.sql.Timestamp timestamp_end = new java.sql.Timestamp(System.currentTimeMillis()+86400000);
//		
//		insertVO.setGift_no("G001");
//		insertVO.setGiftd_start(timestamp_start);
//		insertVO.setGiftd_end(timestamp_end);
//		insertVO.setGiftd_percent(0.01);
//		insertVO.setGiftd_amount(9);
//		gDSvc.updateGiftDiscount(insertVO);
//		out.println(giftd_no + " : ���\�ק�ӵ������u�f");
//		out.println("===================================================================");
		

//		//���էR���@�������u�f		
//		out.println("=======================���էR���@�������u�f==============================");
//		String giftd_no = "GD001";
//		gDSvc.deleteGiftDiscount(giftd_no);
//		out.println(giftd_no + " : ���������u�f���\�Q�R��");
//		out.println("===================================================================");		
		
		
////���ըϥ�giftd_no���o��@��GiftDiscountVO
//		String giftd_no = "GD001";
//		GiftDiscountVO oneVO = gDSvc.getOneGiftDiscount(giftd_no);
//		out.println("=============���ըϥ�gift_no���o��@��giftVO===================");
//		out.println("giftd_no     : " + oneVO.getGiftd_no());
//		out.println("gift_no      : " + oneVO.getGift_no());
//		out.println("giftd_start  : " + oneVO.getGiftd_start());
//		out.println("giftd_end    : " + oneVO.getGiftd_end());
//		out.println("giftd_percent: " + oneVO.getGiftd_percent());
//		out.println("giftd_amount : " + oneVO.getGiftd_amount());
//		out.println("===================================================================");
//		oneVO = null;
		
		
////���ը��o�Ҧ�GiftDiscountVO
//			out.println("=============���ը��o�Ҧ�GiftDiscountVO===================");
//			List<GiftDiscountVO> giftDiscountAll = null;
//			giftDiscountAll = gDSvc.getAll();
//			for(GiftDiscountVO giftDiscountVO: giftDiscountAll){
//				out.println("giftd_no     : " + giftDiscountVO.getGiftd_no());
//				out.println("gift_no      : " + giftDiscountVO.getGift_no());
//				out.println("giftd_start  : " + giftDiscountVO.getGiftd_start());
//				out.println("giftd_end    : " + giftDiscountVO.getGiftd_end());
//				out.println("giftd_percent: " + giftDiscountVO.getGiftd_percent());
//				out.println("giftd_amount : " + giftDiscountVO.getGiftd_amount());		
//				out.println();
//			}
//			out.println("===================================================================");
//			giftDiscountAll = null;	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
