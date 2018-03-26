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

		
////���մ��J�@��giftTrackVO����
//			out.println("===========���մ��J�@��giftTrackVO����==========================");
//			GiftTrackVO giftTrackInsert = new GiftTrackVO();
//			giftTrackInsert.setMem_no("M005");
//			giftTrackInsert.setGift_no("G013");
//			gTSvc.addGiftTrack(giftTrackInsert);
//			out.println("���J���\");
//			out.println("===========================================================");
			

		
////���էR���@��giftTrackVO����
//			out.println("===========���էR���@��giftTrackVO����==========================");
//			String mem_no = "M002";
//			String gift_no = "G022";
//			gTSvc.deleteGiftTrack(mem_no, gift_no);
//			out.println(mem_no + ", " + gift_no +  " :�R�����\");
//			out.println("===========================================================");		
		
		
////���ըϥ�mem_no�Mgift_no���o��@��giftTrackVO
//			out.println("=============���ըϥ�mem_no�Mgift_no���o��@��giftTrackVO================");
//			String mem_no = "M001";
//			String gift_no = "G003";
//			GiftTrackVO giftTrackOne = null;
//			giftTrackOne = gTSvc.getOneGiftTrack(mem_no, gift_no);
//			out.println("mem_no     : " + giftTrackOne.getMem_no());
//			out.println("gift_no    : " + giftTrackOne.getGift_no());
//			out.println("giftt_time : " + giftTrackOne.getGiftt_time());
//			out.println("===================================================================");
//			giftTrackOne = null;
			
			
////���ը��o�Ҧ�giftTrackVO
//			out.println("=============���ը��o�Ҧ�giftTrackVO===================");
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

			
////���ը��o�Ҧ�MEM_NO��giftTrackVO
//		out.println("=============���ը��o�Ҧ�MEM_NO��giftTrackVO==========================");
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

		
////���ը��o�Ҧ�GIFT_NO��giftTrackVO
//			out.println("=============���ը��o�Ҧ�GIFT_NO��giftTrackVO==========================");
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
