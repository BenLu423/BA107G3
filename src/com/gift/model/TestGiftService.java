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
////���ռW�[�@��§�� �P �Ƶ�§������
//		out.println("=============���ռW�[�@��§�� �P �Ƶ�§������===================");
//		GiftVO giftNew = new GiftVO();
//		giftNew.setGift_name("���զW��");
//		giftNew.setGift_cnt("���դ��e");
//		giftNew.setGift_price(888);
//		giftNew.setGift_pic(picTest);
//		giftNew.setGift_is_on("�q��");
//		
//		List<GiftLabelDetailVO> giftLabelDetailList = new ArrayList<>();
//		GiftLabelDetailVO gldVO1 = new GiftLabelDetailVO();
//		gldVO1.setGiftl_no("GL001");
//		giftLabelDetailList.add(gldVO1);
//		GiftLabelDetailVO gldVO2 = new GiftLabelDetailVO();
//		gldVO2.setGiftl_no("GL002");
//		giftLabelDetailList.add(gldVO2);
//
//		//���� ��§���S���ҩ���		
//		//List<GiftLabelDetailVO> giftLabelDetailList = null;
//		giftSvc.addGift(giftNew, giftLabelDetailList);
//		out.println("�W�[�@��§�� �P �Ƶ�§������ ");
//		out.println("===================================================================");
		
		
//���է�s�@��§�� �P �Ƶ�§������
//		out.println("=============���է�s�@��§�� �P �Ƶ�§������=================================");
//		GiftVO giftVOUp = null;
//		
//		//���ա����Ʈw[��]���ҩ��ӡA��s��[�S��]§�����ҩ���
//		giftVOUp = giftSvc.getOneGift("G005");
//		List<GiftLabelDetailVO> gldUpList = new ArrayList<>();
//		
//		//���ա����Ʈw[��]���ҩ��ӡA��s��[�����P]��§�����ҩ���
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
//		//���ա����Ʈw[�S��]���ҩ��ӡA��s��[�S��]��§�����ҩ���
//		giftVOUp = giftSvc.getOneGift("G004");
//		List<GiftLabelDetailVO> gldUpList = new ArrayList<>();
//		
//		//���ա����Ʈw[�S��]���ҩ��ӡA��s��[��]��§�����ҩ���
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
//		out.println(giftVOUp.getGift_no() + " :��s���զ��\");
//		out.println("===================================================================");
//
//
//���էR���@��§�� �P �Ƶ�§������
//		out.println("=============���էR���@��§�� �P �Ƶ�§������=================================");
//		//���ա��R��[�S��]���ҩ��Ӫ�§���@�B ��§��������[�q��]�W�[�L  �P   [�٥��إ�]��������§��
//		String gift_no = "G014";
//		giftSvc.deleteGift(gift_no);
//		
//		//���ա��R��[��]���ҩ��Ӫ�§���@�B ��§��������[�q��]�W�[�L  �P   [�٥��إ�]��������§��
//		String gift_no = "G011";
//		giftSvc.deleteGift(gift_no);
		
//		out.println(gift_no + " �R�����\");
//		out.println("===================================================================");
		
		
////���ըϥ�gift_no���o��@��giftVO
//		out.println("=============���ըϥ�gift_no���o��@��giftVO===================");
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
////���ը��o�Ҧ�giftVO
//		out.println("=============���ը��o�Ҧ�giftVO===================");
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
