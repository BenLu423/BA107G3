package com.giftOrderView.model;

import java.sql.Date;
import java.util.*;

public class GiftOrderViewService {
	private GiftOrderViewDAO_interface dao;

	public GiftOrderViewService() {
		dao = new GiftOrderViewDAO();
	}

	public GiftOrderViewVO getOne(String giftr_no) {
		return dao.getByPrimaryKey(giftr_no);
	}

	public List<GiftOrderViewVO> getAll() {
		return dao.getAll();
	}
	
	public List<GiftOrderViewVO> getAll(String mem_no_self, Date start, Date end) {
		return dao.getAll(mem_no_self, start, end);
		
	}
	public List<GiftOrderViewVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public List<GiftOrderViewVO> getAll(String mem_no_self) {
		return dao.getAll(mem_no_self);
		
	}
	
	public byte[] getPic(String giftr_no, String columnName) {
		return dao.getPic(giftr_no, columnName);
	}
}
