package com.qa.model;

import java.util.*;


public interface QaDAO_interface {
        public void insert(QaVO QaVO);
        public void update(QaVO QaVO);
        public void delete(String Qa_no);
        public QaVO findByPrimaryKey(String Qa_no);
        public List<QaVO> getAll();
//        �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 
	}


