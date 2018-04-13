package com.qa.model;

import java.util.*;


public interface QaDAO_interface {
        public void insert(QaVO QaVO);
        public void update(QaVO QaVO);
        public void delete(String Qa_no);
        public QaVO findByPrimaryKey(String Qa_no);
        public List<QaVO> getAll();
//        萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 
	}


