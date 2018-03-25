package com.giftLabelDetail.model;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestGiftLabelListService")
public class TestGiftLabelDetailService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftLabelDetailService giftLabelListSvc = new GiftLabelDetailService();
		
		out.println("���ըϥ�gift_no(§���s��)���o�������h��giftl_no(���ҽs��)");
		//���ըϥ�gift_no���o�������h��giftl_no
		List<GiftLabelDetailVO> giftLabelListByNo = null;
		giftLabelListByNo = giftLabelListSvc.getByGiftNo("G013");
		for(GiftLabelDetailVO giftLabelListVO: giftLabelListByNo){
			out.println(giftLabelListVO.getGift_no() + " : " + giftLabelListVO.getGiftl_no());
		}
		out.println("===================================================================");
		giftLabelListByNo = null;
		
		out.println("���ըϥ�giftl_no(���ҽs��)���o�������h��gift_no(§���s��)");
		//���ըϥ�giftLabel_no���o�������h��gift_no
		List<GiftLabelDetailVO> giftLabelListByLabel = null;
		giftLabelListByLabel = giftLabelListSvc.getByGiftLabelNo("GL006");
		for(GiftLabelDetailVO giftLabelListVO: giftLabelListByLabel){
			out.println(giftLabelListVO.getGift_no() + " : " + giftLabelListVO.getGiftl_no());
		}
		out.println("===================================================================");
		giftLabelListByNo = null;
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
