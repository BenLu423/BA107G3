package com.gift.model;

import java.util.*;

import com.giftLabelDetail.model.GiftLabelDetailVO;

public class GiftService {
	private GiftDAO_interface dao;
	
	public GiftService(){
		dao = new GiftDAO();
	}
	
	public void addGift(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList){
		dao.insert(giftVO, giftLabelDetailList);
	}
	
	public void updateGift(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList){
		dao.update(giftVO, giftLabelDetailList);
	}
	
	public void deleteGift(String gift_no){
		dao.delete(gift_no);
	}
	
	public GiftVO getOneGift(String gift_no){
		return dao.getByPrimaryKey(gift_no);
	}
	
	public List<GiftVO> getAll(){
		return dao.getAll();
	}
}
