package com.account_report.model;

import java.util.List;



public interface AccountReportDAO_interface {
	void accountReportAdd(AccountReportVO memrep);
	void accountReportUpdate(List<AccountReportVO> memrep);
	List<AccountReportVO> findStatement(String memrep);
	List<AccountReportVO> getAll();
	List<AccountReportVO> getAll(String other,String permit);
	AccountReportVO getOneMem(String self,String other);
	boolean getOne(String self, String other);
	void accountReportDelete(AccountReportVO memrep);
}
