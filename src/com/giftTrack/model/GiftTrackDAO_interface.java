package com.giftTrack.model;

import java.util.List;

import com.giftLabel.model.GiftLabelVO;

public interface GiftTrackDAO_interface {
	public void insert(GiftTrackVO giftTrackVO);
	public void delete(String mem_no, String gift_no);
	public GiftTrackVO getByPrimaryKey(String mem_no, String gift_no);
	public List<GiftTrackVO> getAll();

	public List<GiftTrackVO> getListByMemNo(String mem_no);
	public List<GiftTrackVO> getListByGiftNo(String gift_no);
	
}
