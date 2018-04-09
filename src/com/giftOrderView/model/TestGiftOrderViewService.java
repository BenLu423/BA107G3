package com.giftOrderView.model;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestGiftOrderViewService")
public class TestGiftOrderViewService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftOrderViewService giftOrderViewSvc = new GiftOrderViewService();
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		 * 圖片取法，給3個參數[表格名稱,收贈禮編號,欄位名稱]												 * 
		 * ----------------------------------------------------------------------------------* 
		 * DBGifReader4?table=GIFT_ORDER_VIEW&giftr_no=20180402-GR022&columnName=GIFT_PIC	 * 
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */		

////測試使用giftr_no取得單一的GiftOrderViewVO
//			out.println("=============測試使用gift_no取得單一的giftVO===================");
//			GiftOrderViewVO one = null;
//			one = giftOrderViewSvc.getOne("20180402-GR022");
//			out.println(one.getGiftr_no());
//			out.println(one.getMem_no_self());
//			out.println(one.getMem_name_self());
//			out.println(one.getMem_photo_self());
//			out.println(one.getMem_no_other());
//			out.println(one.getMem_name_other());
//			out.println(one.getMem_photo_other());
//			out.println(one.getGiftr_amount());
//			out.println(one.getGiftr_time());
//			out.println(one.getGifto_no());
//			out.println(one.getGifto_time());
//			out.println(one.getGifto_remark());
//			out.println(one.getCoup_no());
//			out.println(one.getCoup_name());
//			out.println(one.getCoup_value());
//			out.println(one.getGiftod_no());
//			out.println(one.getGift_no());
//			out.println(one.getGift_name());
//			out.println(one.getGift_cnt());
//			out.println(one.getGift_price());
//			out.println(one.getGift_pic());
//			out.println(one.getGiftod_amount());
//			out.println(one.getGiftod_money());
//			out.println(one.getGiftod_inventory());
//			out.println("===================================================================");
//			one = null;
//		
//		
////測試取得所有GiftOrderViewVO
//		out.println("=============測試取得所有giftVO===================");
//		List<GiftOrderViewVO> all = null;
//		all = giftOrderViewSvc.getAll();
//		for(GiftOrderViewVO giftOrderViewVO: all){
//			out.println(giftOrderViewVO.getGiftr_no());
//			out.println(giftOrderViewVO.getMem_no_self());
//			out.println(giftOrderViewVO.getMem_name_self());
//			out.println(giftOrderViewVO.getMem_photo_self());
//			out.println(giftOrderViewVO.getMem_no_other());
//			out.println(giftOrderViewVO.getMem_name_other());
//			out.println(giftOrderViewVO.getMem_photo_other());
//			out.println(giftOrderViewVO.getGiftr_amount());
//			out.println(giftOrderViewVO.getGiftr_time());
//			out.println(giftOrderViewVO.getGifto_no());
//			out.println(giftOrderViewVO.getGifto_time());
//			out.println(giftOrderViewVO.getGifto_remark());
//			out.println(giftOrderViewVO.getCoup_no());
//			out.println(giftOrderViewVO.getCoup_name());
//			out.println(giftOrderViewVO.getCoup_value());
//			out.println(giftOrderViewVO.getGiftod_no());
//			out.println(giftOrderViewVO.getGift_no());
//			out.println(giftOrderViewVO.getGift_name());
//			out.println(giftOrderViewVO.getGift_cnt());
//			out.println(giftOrderViewVO.getGift_price());
//			out.println(giftOrderViewVO.getGift_pic());
//			out.println(giftOrderViewVO.getGiftod_amount());
//			out.println(giftOrderViewVO.getGiftod_money());
//			out.println(giftOrderViewVO.getGiftod_inventory());
//			out.println();
//		}
//		out.println("===================================================================");
//		all = null;		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
