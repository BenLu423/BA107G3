package com.giftLabel.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestGiftLabelService")
public class TestGiftLabelService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestGiftLabelService() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftLabelService giftLabelSvc = new GiftLabelService();
		
////測試插入一個giftLabelVO物件
//		out.println("===========測試插入一個giftLabelVO物件==========================");
//		GiftLabelVO giftLabelInsert = new GiftLabelVO();
//		giftLabelInsert.setGiftl_name("這是測試標籤");
//		giftLabelSvc.addGiftLabel(giftLabelInsert);
//		out.println("插入成功");
//		out.println("===========================================================");
//		
//
////測試修改一個giftLabelVO物件
//		out.println("===========測試修改一個giftLabelVO物件==========================");
//		GiftLabelVO giftLabelUpdate= giftLabelSvc.getOneGiftLabelByNo("GL001");
//		giftLabelUpdate.setGiftl_name("歡樂聖誕節");
//		giftLabelSvc.updateGiftLabel(giftLabelUpdate);
//		out.println("修改成功");
//		out.println("===========================================================");
//
//
		
		
//測試刪除一個giftLabelVO物件
//		//僅提供[未被任何禮物使用]的標籤進行刪除
//		out.println("===========測試刪除一個giftLabelVO物件==========================");
//		String giftl_no = "GL005";
//		giftLabelSvc.deleteGiftLabel(giftl_no);
//		out.println(giftl_no + " :刪除成功");
//		out.println("===========================================================");
		
//		
////測試使用giftl_no取得單一的giftLabelVO
//		out.println("===========測試使用giftl_no取得單一的giftLabelVO=================");
//		GiftLabelVO giftLabelOneByNo = null;
//		giftLabelOneByNo = giftLabelSvc.getOneGiftLabelByNo("GL002");
//		out.println(giftLabelOneByNo.getGiftl_no());
//		out.println(giftLabelOneByNo.getGiftl_name());
//		out.println("===========================================================");
//		giftLabelOneByNo = null;
//		
//		
////測試使用giftl_name取得單一的giftLabelVO
//		out.println("===========測試使用giftl_name取得單一的giftLabelVO===============");
//		GiftLabelVO giftLabelOneByName = null;
//		giftLabelOneByName = giftLabelSvc.getOneGiftLabelByName("蛋糕");
//		out.println(giftLabelOneByName.getGiftl_no());
//		out.println(giftLabelOneByName.getGiftl_name());
//		out.println("===========================================================");
//		giftLabelOneByName = null;
//		
//		
////測試取得所有giftLabelVO
//		out.println("===========測試取得所有giftLabelVO=============================");
//		List<GiftLabelVO> giftLabelAll = null;
//		giftLabelAll = giftLabelSvc.getAll();
//		for(GiftLabelVO giftLabelVO: giftLabelAll){
//			out.println(giftLabelVO.getGiftl_no());
//			out.println(giftLabelVO.getGiftl_name());
//			out.println();
//		}
//		out.println("===========================================================");
//		giftLabelAll = null;

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
