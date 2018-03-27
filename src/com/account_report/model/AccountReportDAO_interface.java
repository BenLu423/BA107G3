package com.account_report.model;

import java.util.List;



public interface AccountReportDAO_interface {
	void accountReportAdd(AccountReportVO memrep);
	void accountReportUpdate(AccountReportVO memrep);
	List<AccountReportVO> findStatement(String memrep);
	List<AccountReportVO> getAll();
}
