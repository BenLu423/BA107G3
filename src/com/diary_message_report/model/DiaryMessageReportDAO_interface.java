package com.diary_message_report.model;

import java.util.List;

import com.account_report.model.AccountReportVO;

public interface DiaryMessageReportDAO_interface {
	void diaryMessageReportVOAdd(DiaryMessageReportVO dmrrep);
	void diaryMessageReportVOUpdate(DiaryMessageReportVO dmrrep);
	List<DiaryMessageReportVO> findStatement(String dmrrep);
	List<DiaryMessageReportVO> getAll();
}
