package com.giftTrack.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gift.model.GiftVO;
import com.giftTrack.model.GiftTrackService;
import com.giftTrack.model.GiftTrackVO;
import com.google.gson.Gson;
import com.member.model.MemberVO;

public class GfitTrackServletUTF8 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("GiftUF8 action: " + action);	

		//新增追蹤禮物
		if("addToFollow".equals(action)){
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			Map<String,String> successMsgs = new LinkedHashMap<>();
			
			try{
				String mem_no = req.getParameter("mem_no");
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				String gift_no = req.getParameter("gift_no");
				GiftVO giftVO = new GiftVO();
				giftVO.setGift_no(gift_no);
				GiftTrackVO giftTrackVO = new GiftTrackVO();
				giftTrackVO.setMemberVO(memberVO);
				giftTrackVO.setGiftVO(giftVO);
				
				GiftTrackService giftTrackSvc = new GiftTrackService();
				giftTrackSvc.addGiftTrack(giftTrackVO);
				successMsgs.put("status", "success");
				out.print(gson.toJson(successMsgs));
			}catch(Exception e){
				Map<String,String> errorMsgs = new LinkedHashMap<>();
				errorMsgs.put("status", "failure");
				errorMsgs.put("Exception", e.getMessage());
				out.print(gson.toJson(errorMsgs));
			}
		}
		
		
		//刪除追蹤禮物
		if("delToFollow".equals(action)){
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			Map<String,String> successMsgs = new LinkedHashMap<>();
			
			try{
				String mem_no = req.getParameter("mem_no");
				String gift_no = req.getParameter("gift_no");
				
				GiftTrackService giftTrackSvc = new GiftTrackService();
				giftTrackSvc.deleteGiftTrack(mem_no, gift_no);
				successMsgs.put("status", "success");
				out.print(gson.toJson(successMsgs));
			}catch(Exception e){
				Map<String,String> errorMsgs = new LinkedHashMap<>();
				errorMsgs.put("status", "failure");
				errorMsgs.put("Exception", e.getMessage());
				out.print(gson.toJson(errorMsgs));
			}
		}
		
	}

}
