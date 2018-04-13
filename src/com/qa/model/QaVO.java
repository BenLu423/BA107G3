package com.qa.model;

import java.sql.Date;

public class QaVO implements java.io.Serializable{
			
			private String qa_no;
			private Date qa_date;
			private String qa_title;
			private String qa_cnt;
			
			
			public String getQa_no() {
				return qa_no;
			}
			public void setQa_no(String qa_no) {
				this.qa_no = qa_no;
			}
			public Date getQa_date() {
				return qa_date;
			}
			public void setQa_date(Date qa_date) {
				this.qa_date = qa_date;
			}
			public String getQa_title() {
				return qa_title;
			}
			public void setQa_title(String qa_title) {
				this.qa_title = qa_title;
			}
			public String getQa_cnt() {
				return qa_cnt;
			}
			public void setQa_cnt(String qa_cnt) {
				this.qa_cnt = qa_cnt;
			}
			
			
			
	
			
		

	}


