package com.gift.model;

import java.util.List;

import com.giftLabelDetail.model.GiftLabelDetailVO;

public interface GiftDAO_interface {
	public void insert(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList);
	public void update(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList);
	public void delete(String gift_no);
	public GiftVO getByPrimaryKey(String gift_no);
	public List<GiftVO> getAll();

	
//	//�d�߬Y���Ҫ�§��(�@��h)(�^�� Set)
//	public Set<GiftVO> getGiftsByLabel(String giftl_name);

//	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//	public List<GiftVO> getAll(Map<String, String[]> map); 

}
