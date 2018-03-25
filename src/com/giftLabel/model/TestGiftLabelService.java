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
		
////���մ��J�@��giftLabelVO����
//		out.println("===========���մ��J�@��giftLabelVO����==========================");
//		GiftLabelVO giftLabelInsert = new GiftLabelVO();
//		giftLabelInsert.setGiftl_name("�o�O���ռ���");
//		giftLabelSvc.addGiftLabel(giftLabelInsert);
//		out.println("���J���\");
//		out.println("===========================================================");
//		
//
////���խק�@��giftLabelVO����
//		out.println("===========���խק�@��giftLabelVO����==========================");
//		GiftLabelVO giftLabelUpdate= giftLabelSvc.getOneGiftLabelByNo("GL001");
//		giftLabelUpdate.setGiftl_name("�w�ָt�ϸ`");
//		giftLabelSvc.updateGiftLabel(giftLabelUpdate);
//		out.println("�ק令�\");
//		out.println("===========================================================");
//
//
		
		
//���էR���@��giftLabelVO����
//		//�ȴ���[���Q����§���ϥ�]�����Ҷi��R��
//		out.println("===========���էR���@��giftLabelVO����==========================");
//		String giftl_no = "GL005";
//		giftLabelSvc.deleteGiftLabel(giftl_no);
//		out.println(giftl_no + " :�R�����\");
//		out.println("===========================================================");
		
//		
////���ըϥ�giftl_no���o��@��giftLabelVO
//		out.println("===========���ըϥ�giftl_no���o��@��giftLabelVO=================");
//		GiftLabelVO giftLabelOneByNo = null;
//		giftLabelOneByNo = giftLabelSvc.getOneGiftLabelByNo("GL002");
//		out.println(giftLabelOneByNo.getGiftl_no());
//		out.println(giftLabelOneByNo.getGiftl_name());
//		out.println("===========================================================");
//		giftLabelOneByNo = null;
//		
//		
////���ըϥ�giftl_name���o��@��giftLabelVO
//		out.println("===========���ըϥ�giftl_name���o��@��giftLabelVO===============");
//		GiftLabelVO giftLabelOneByName = null;
//		giftLabelOneByName = giftLabelSvc.getOneGiftLabelByName("�J�|");
//		out.println(giftLabelOneByName.getGiftl_no());
//		out.println(giftLabelOneByName.getGiftl_name());
//		out.println("===========================================================");
//		giftLabelOneByName = null;
//		
//		
////���ը��o�Ҧ�giftLabelVO
//		out.println("===========���ը��o�Ҧ�giftLabelVO=============================");
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
