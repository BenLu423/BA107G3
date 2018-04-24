package com.account_report.model;

import java.util.ArrayList;
import java.util.List;

public class AccountReportService {
	private AccountReportDAO_interface dao;
	public AccountReportService(){
		dao = new AccountReportDAO();
	}
	public void AddProhibit(String self, String other, String reason, String cnt){
		AccountReportVO arvo = new AccountReportVO();
		arvo.setMem_no_self(self);
		arvo.setMem_no_other(other);
		arvo.setAccrep_reason(reason);
		arvo.setAccrep_cnt(cnt);
		dao.accountReportAdd(arvo);
	}
	public Boolean isExistProhibit(String self,String other){
		Boolean isExist = false;
		isExist =dao.getOne(self, other);
		return isExist;
	}
	public List<AccountReportVO> getAll(){
		return dao.getAll();
	}
	public List<AccountReportVO> findallStatement(String statement){
		return dao.findStatement(statement);
	}
	public AccountReportVO getOneMem(String self, String other){
		return dao.getOneMem(self, other);
	}
	public void accountReportUpdateStatement(String permit, String self, String other){
		AccountReportVO arvo = new AccountReportVO();
		List<AccountReportVO> acclist = new ArrayList<AccountReportVO>();
		acclist = dao.getAll(other, permit);

		dao.accountReportUpdate(acclist);
	}
	public void prohibitDelete(String self, String other){
		AccountReportVO arvo = new AccountReportVO();
		arvo.setMem_no_self(self);
		arvo.setMem_no_other(other);
		dao.accountReportDelete(arvo);
	}
}
