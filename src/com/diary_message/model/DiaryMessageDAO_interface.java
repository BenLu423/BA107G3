package com.diary_message.model;

import java.util.List;

public interface DiaryMessageDAO_interface {
	void DiaryMessageAdd(DiaryMessageVO diam);
	void accountReportUpdate(DiaryMessageVO diam);
	List<DiaryMessageVO> findStatement(String diam);
	List<DiaryMessageVO> getAll();
	
	
}
