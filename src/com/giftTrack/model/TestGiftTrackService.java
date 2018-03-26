package com.giftTrack.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestGiftTrackService")
public class TestGiftTrackService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftTrackService gTSvc = new GiftTrackService();

		
////測試插入一個giftTrackVO物件
//			out.println("===========測試插入一個giftTrackVO物件==========================");
//			GiftTrackVO giftTrackInsert = new GiftTrackVO();
//			giftTrackInsert.setMem_no("M005");
//			giftTrackInsert.setGift_no("G013");
//			gTSvc.addGiftTrack(giftTrackInsert);
//			out.println("插入成功");
//			out.println("===========================================================");
			

		
////測試刪除一個giftTrackVO物件
//			out.println("===========測試刪除一個giftTrackVO物件==========================");
//			String mem_no = "M002";
//			String gift_no = "G022";
//			gTSvc.deleteGiftTrack(mem_no, gift_no);
//			out.println(mem_no + ", " + gift_no +  " :刪除成功");
//			out.println("===========================================================");		
		
		
////測試使用mem_no和gift_no取得單一的giftTrackVO
//			out.println("=============測試使用mem_no和gift_no取得單一的giftTrackVO================");
//			String mem_no = "M001";
//			String gift_no = "G003";
//			GiftTrackVO giftTrackOne = null;
//			giftTrackOne = gTSvc.getOneGiftTrack(mem_no, gift_no);
//			out.println("mem_no     : " + giftTrackOne.getMem_no());
//			out.println("gift_no    : " + giftTrackOne.getGift_no());
//			out.println("giftt_time : " + giftTrackOne.getGiftt_time());
//			out.println("===================================================================");
//			giftTrackOne = null;
			
			
////測試取得所有giftTrackVO
//			out.println("=============測試取得所有giftTrackVO===================");
//			List<GiftTrackVO> giftTrackAll = null;
//			giftTrackAll = gTSvc.getAll();
//			for(GiftTrackVO giftTrackVO: giftTrackAll){
//				out.println("mem_no     : " + giftTrackVO.getMem_no());
//				out.println("gift_no    : " + giftTrackVO.getGift_no());
//				out.println("giftt_time : " + giftTrackVO.getGiftt_time());	
//				out.println();
//			}
//			out.println("===================================================================");
//			giftTrackAll = null;

			
////測試取得所有MEM_NO的giftTrackVO
//		out.println("=============測試取得所有MEM_NO的giftTrackVO==========================");
//		String mem_no = "M001";
//		List<GiftTrackVO> giftTrackByMemNo = null;
//		giftTrackByMemNo = gTSvc.getListByMemNo(mem_no);
//		for(GiftTrackVO giftTrackVO: giftTrackByMemNo){
//			out.println("mem_no     : " + giftTrackVO.getMem_no());
//			out.println("gift_no    : " + giftTrackVO.getGift_no());
//			out.println("giftt_time : " + giftTrackVO.getGiftt_time());	
//			out.println();
//		}
//		out.println("===================================================================");
//		giftTrackByMemNo = null;

		
////測試取得所有GIFT_NO的giftTrackVO
//			out.println("=============測試取得所有GIFT_NO的giftTrackVO==========================");
//			String gift_no = "G013";
//			List<GiftTrackVO> giftTrackByGiftNo = null;
//			giftTrackByGiftNo = gTSvc.getListByGiftNo(gift_no);
//			for(GiftTrackVO giftTrackVO: giftTrackByGiftNo){
//				out.println("mem_no     : " + giftTrackVO.getMem_no());
//				out.println("gift_no    : " + giftTrackVO.getGift_no());
//				out.println("giftt_time : " + giftTrackVO.getGiftt_time());	
//				out.println();
//			}
//			out.println("===================================================================");
//			giftTrackByGiftNo = null;		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
