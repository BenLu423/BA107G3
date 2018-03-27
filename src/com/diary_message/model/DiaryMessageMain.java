package com.diary_message.model;

import java.util.ArrayList;
import java.util.List;

public class DiaryMessageMain {

	public static void main(String[] args) {
		DiaryMessageDAO dmdao = new DiaryMessageDAO();
		DiaryMessageVO dmvo = new DiaryMessageVO();
		int like = 1;
//		dmvo.setMem_no("M003");
//		dmvo.setDia_no("D001");
//		dmvo.setDiam_cnt("testtesttesttesttesttesttesttesttesttesttesttesttesttest");
//		dmvo.setDiam_time(java.sql.Date.valueOf("2018-03-20"));
//		dmvo.setDiam_like(++like);
//		dmvo.setDiam_rescnt("8787878787");
//		dmvo.setDiam_reslike(++like);
//		dmdao.DiaryMessageAdd(dmvo);
		
		DiaryMessageVO dmvo1 = new DiaryMessageVO();
		dmvo1.setDiam_cnt("sasfasf");
		dmvo1.setDiam_no("DM002");
		dmdao.accountReportUpdate(dmvo1);
		
		List<DiaryMessageVO> list = new ArrayList<>();
//		list = dmdao.findStatement("DM001");
//		String sp = "\t";
//		for(Object find : list){
//			DiaryMessageVO find1 = (DiaryMessageVO)find;
//			System.out.print(find1.getDiam_no()+sp);
//			System.out.print(find1.getMem_no()+sp);
//			System.out.println(find1.getDiam_cnt());
//		}
		
		List<DiaryMessageVO> list1 = new ArrayList<>();
		list1 = dmdao.getAll();
//		String sp1 = " ";
//		for(DiaryMessageVO find : list1){
//			System.out.print(find.getDiam_no()+sp1);
//			System.out.print(find.getMem_no()+sp1);
//			System.out.print(find.getDia_no()+sp1);
//			System.out.print(find.getDiam_time()+sp1);
//			System.out.print(find.getDiam_cnt()+sp1);
//			System.out.print(find.getDiam_like()+sp1);
//			System.out.print(find.getDiam_restime()+sp1);
//			System.out.print(find.getDiam_rescnt()+sp1);
//			System.out.println(find.getDiam_reslike());
//		}
	}
}
