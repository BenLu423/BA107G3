package com.account_report.model;

import java.util.ArrayList;
import java.util.List;



public class AccountReportMain {

	public static void main(String[] args) {
		AccountReportDAO memrepdao = new AccountReportDAO();
		AccountReportVO memrepvo = new AccountReportVO();
		/*新增檢舉*/
//		memrepvo.setMem_no_self("M004");
//		memrepvo.setMem_no_other("M003");
//		memrepvo.setAccrep_reason("謾罵");
//		memrepvo.setAccrep_cnt("管理員請檢舉他");
//		memrepdao.accountReportAdd(memrepvo);
		
		/*修改狀態*/
		AccountReportVO memrepvo2 = new AccountReportVO();
//		memrepvo2.setAccrep_permit("以檢舉");
//		memrepvo2.setMem_no_self("M004");
//		memrepvo2.setMem_no_other("M003");
//		memrepdao.accountReportUpdate(memrepvo2);
		
		/*查詢檢舉狀態*/
//		List<AccountReportVO> lists = new ArrayList<>();
//		lists = memrepdao.findStatement("待審核");
//		for(AccountReportVO find : lists){
//			System.out.print(find.getMem_no_self()+" ");
//			System.out.print(find.getMem_no_other()+" ");
//			System.out.print(find.getAccrep_reason()+" ");
//			System.out.print(find.getAccrep_cnt()+" ");
//			System.out.print(find.getAccrep_time()+" ");
//			System.out.println(find.getAccrep_permit()+" ");
//		}
		
		
		/*查詢全部名單*/
		List<AccountReportVO> list = new ArrayList<>();
		list = memrepdao.getAll();
		String strsp = "\t";
		for(AccountReportVO getall : list){
			System.out.print(getall.getMem_no_self());
			System.out.print(getall.getMem_no_other());
			System.out.print(getall.getAccrep_reason());
			System.out.print(getall.getAccrep_cnt());
			System.out.print(getall.getAccrep_time());
			System.out.println(getall.getAccrep_permit());
	
		}

	}

}
