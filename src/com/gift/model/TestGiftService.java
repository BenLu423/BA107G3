package com.gift.model;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.giftLabelDetail.model.GiftLabelDetailService;
import com.giftLabelDetail.model.GiftLabelDetailVO;

@WebServlet("/TestService")
public class TestGiftService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftService giftSvc = new GiftService();
		
//		byte[] picTest = new byte[1024];
////測試增加一筆禮物 與 數筆禮物標籤
//		out.println("=============測試增加一筆禮物 與 數筆禮物標籤===================");
//		GiftVO giftNew = new GiftVO();
//		giftNew.setGift_name("測試名稱");
//		giftNew.setGift_cnt("測試內容");
//		giftNew.setGift_price(888);
//		giftNew.setGift_pic(picTest);
//		giftNew.setGift_is_on("從未");
//		
//		List<GiftLabelDetailVO> giftLabelDetailList = new ArrayList<>();
//		GiftLabelDetailVO gldVO1 = new GiftLabelDetailVO();
//		gldVO1.setGiftl_no("GL001");
//		giftLabelDetailList.add(gldVO1);
//		GiftLabelDetailVO gldVO2 = new GiftLabelDetailVO();
//		gldVO2.setGiftl_no("GL002");
//		giftLabelDetailList.add(gldVO2);
//
//		//測試 →禮物沒標籤明細		
//		//List<GiftLabelDetailVO> giftLabelDetailList = null;
//		giftSvc.addGift(giftNew, giftLabelDetailList);
//		out.println("增加一筆禮物 與 數筆禮物標籤 ");
//		out.println("===================================================================");
		
		
//測試更新一筆禮物 與 數筆禮物標籤
//		out.println("=============測試更新一筆禮物 與 數筆禮物標籤=================================");
//		GiftVO giftVOUp = null;
//		
//		//測試→原資料庫[有]標籤明細，更新成[沒有]禮物標籤明細
//		giftVOUp = giftSvc.getOneGift("G005");
//		List<GiftLabelDetailVO> gldUpList = new ArrayList<>();
//		
//		//測試→原資料庫[有]標籤明細，更新成[有不同]的禮物標籤明細
//		List<GiftLabelDetailVO> gldUpList = new ArrayList<>();
//		giftVOUp = giftSvc.getOneGift("G013");
//		GiftLabelDetailVO[] gldArray = new GiftLabelDetailVO[2];
//		gldArray[0] = new GiftLabelDetailVO();
//		gldArray[0].setGift_no(giftVOUp.getGift_no());
//		gldArray[0].setGiftl_no("GL003");
//		gldUpList.add(gldArray[0]);
//		gldArray[1] = new GiftLabelDetailVO();
//		gldArray[1].setGift_no(giftVOUp.getGift_no());
//		gldArray[1].setGiftl_no("GL006");
//		gldUpList.add(gldArray[1]);
//
//
//		//測試→原資料庫[沒有]標籤明細，更新成[沒有]的禮物標籤明細
//		giftVOUp = giftSvc.getOneGift("G004");
//		List<GiftLabelDetailVO> gldUpList = new ArrayList<>();
//		
//		//測試→原資料庫[沒有]標籤明細，更新成[有]的禮物標籤明細
//		List<GiftLabelDetailVO> gldUpList = new ArrayList<>();
//		giftVOUp = giftSvc.getOneGift("G001");
//		GiftLabelDetailVO[] gldArray = new GiftLabelDetailVO[2];
//		gldArray[0] = new GiftLabelDetailVO();
//		gldArray[0].setGift_no(giftVOUp.getGift_no());
//		gldArray[0].setGiftl_no("GL005");
//		gldUpList.add(gldArray[0]);
//		gldArray[1] = new GiftLabelDetailVO();
//		gldArray[1].setGift_no(giftVOUp.getGift_no());
//		gldArray[1].setGiftl_no("GL006");
//		gldUpList.add(gldArray[1]);
//		
//		giftSvc.updateGift(giftVOUp, gldUpList);
//		out.println(giftVOUp.getGift_no() + " :更新測試成功");
//		out.println("===================================================================");
//
//
//測試刪除一筆禮物 與 數筆禮物標籤
//		out.println("=============測試刪除一筆禮物 與 數筆禮物標籤=================================");
//		//測試→刪除[沒有]標籤明細的禮物　且 此禮物必須為[從未]上架過  與   [還未建立]成為限時禮物
//		String gift_no = "G014";
//		giftSvc.deleteGift(gift_no);
//		
//		//測試→刪除[有]標籤明細的禮物　且 此禮物必須為[從未]上架過  與   [還未建立]成為限時禮物
//		String gift_no = "G011";
//		giftSvc.deleteGift(gift_no);
		
//		out.println(gift_no + " 刪除成功");
//		out.println("===================================================================");
		
		
////測試使用gift_no取得單一的giftVO
//		out.println("=============測試使用gift_no取得單一的giftVO===================");
//		GiftVO giftOne = null;
//		giftOne = giftSvc.getOneGift("G004");
//		out.println(giftOne.getGift_no());
//		out.println(giftOne.getGift_name());
//		out.println(giftOne.getGift_cnt());
//		out.println(giftOne.getGift_price());
//		out.println(giftOne.getGift_pic());
//		out.println(giftOne.getGift_is_on());
//		out.println("===================================================================");
//		giftOne = null;
//		
//		
////測試取得所有giftVO
//		out.println("=============測試取得所有giftVO===================");
//		List<GiftVO> giftAll = null;
//		giftAll = giftSvc.getAll();
//		for(GiftVO giftVO: giftAll){
//			out.println(giftVO.getGift_no());
//			out.println(giftVO.getGift_name());
//			out.println(giftVO.getGift_cnt());
//			out.println(giftVO.getGift_price());
//			out.println(giftVO.getGift_pic());
//			out.println(giftVO.getGift_is_on());		
//			out.println();
//		}
//		out.println("===================================================================");
//		giftAll = null;
		
	}

}
