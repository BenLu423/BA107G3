package com.giftDiscount.model;

import java.util.List;
import java.util.Map;

public interface GiftDiscountDAO_interface {
	public void insert(GiftDiscountVO giftDiscountVO);
	public void update(GiftDiscountVO giftDiscountVO);
	public void delete(String giftd_no);
	public GiftDiscountVO getCurrentValidGift(String gift_no);
	public GiftDiscountVO getByPrimaryKey(String giftd_no);
	public List<GiftDiscountVO> getAll();
	public List<GiftDiscountVO> getAll(Map<String, String[]> map);
	
}
