package com.giftOrderView.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface GiftOrderViewDAO_interface {

	public GiftOrderViewVO getByPrimaryKey(String giftr_no); // ³æµ§¦¬ÃØÂ§

	public List<GiftOrderViewVO> getAll();

	public List<GiftOrderViewVO> getAll(String mem_no_self, Date start, Date end);
	
	public List<GiftOrderViewVO> getAll(Map<String, String[]> map);

	public byte[] getPic(String giftr_no, String columnName);

}
