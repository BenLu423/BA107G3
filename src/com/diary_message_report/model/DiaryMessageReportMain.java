package com.diary_message_report.model;

import java.util.ArrayList;
import java.util.List;

public class DiaryMessageReportMain {

	public static void main(String[] args) {
		DiaryMessageReportDAO dmrdao = new DiaryMessageReportDAO();
		DiaryMessageReportVO dmrvo = new DiaryMessageReportVO();
//		dmrvo.setMem_no("M003");
//		dmrvo.setDiam_no("DM001");
//		dmrvo.setDiamr_reason("¤£¶®µü»y");
//		dmrvo.setDiamr_cnt("bangbangbang");
//		dmrdao.diaryMessageReportVOAdd(dmrvo);
//		
		
		DiaryMessageReportVO dmrvo1 = new DiaryMessageReportVO();
//		dmrvo1.setDiamr_permit("¤w«ÊÂê");
//		dmrvo.setMem_no("M003");
//		dmrvo.setDiam_no("DM001");
//		dmrdao.diaryMessageReportVOUpdate(dmrvo1);
		
		
		List<DiaryMessageReportVO> list = new ArrayList<>();
//		list = dmrdao.findStatement("¬O");
//		for(DiaryMessageReportVO dmvo : list){
//			System.out.println(dmvo.getMem_no());
//			System.out.println(dmvo.getDiam_no());
//			System.out.println(dmvo.getDiamr_reason());
//			System.out.println(dmvo.getDiamr_cnt());
//			System.out.println(dmvo.getDiamr_time());
//			System.out.println(dmvo.getDiamr_permit());
//		}
//		
		List<DiaryMessageReportVO> list1 = new ArrayList<>();
		list1 = dmrdao.getAll();
		for(DiaryMessageReportVO dmvo : list1){
			System.out.print(dmvo.getMem_no());
			System.out.print(dmvo.getDiam_no());
			System.out.print(dmvo.getDiamr_reason());
			System.out.print(dmvo.getDiamr_cnt());
			System.out.print(dmvo.getDiamr_time());
			System.out.println(dmvo.getDiamr_permit());
		}
		
		
		
	}
}
