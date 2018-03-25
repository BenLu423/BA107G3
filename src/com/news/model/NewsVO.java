package com.news.model;

import java.sql.Date;

public class NewsVO implements java.io.Serializable{
	private String news_no;
	private Date news_date;
	private String news_title;
	private String news_cnt;
	
	public NewsVO(){
		
	}
	
	public String getNews_no() {
		return news_no;
	}
	public void setNews_no(String news_no) {
		this.news_no = news_no;
	}
	public Date getNews_date() {
		return news_date;
	}
	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_cnt() {
		return news_cnt;
	}
	public void setNews_cnt(String news_cnt) {
		this.news_cnt = news_cnt;
	}

}
