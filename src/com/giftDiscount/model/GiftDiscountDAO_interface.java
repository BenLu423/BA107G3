package com.giftDiscount.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface GiftDiscountDAO_interface {
	public void insert(GiftDiscountVO giftDiscountVO);
	public void update(GiftDiscountVO giftDiscountVO);
	public void updateAmount(String giftd_no, Integer buyAmount, Connection con);
	public void delete(String giftd_no);
	public GiftDiscountVO getCurrentValidGift(String gift_no);
	public GiftDiscountVO getByPrimaryKey(String giftd_no);
	public List<GiftDiscountVO> getTotal();
	public List<GiftDiscountVO> getTotal(Map<String, String[]> map);
	public List<GiftDiscountVO> getAll();
	public List<GiftDiscountVO> getAll(Map<String, String[]> map);
	public List<GiftDiscountVO> getNotStartAll();
	
}
