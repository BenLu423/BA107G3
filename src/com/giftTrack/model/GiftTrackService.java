package com.giftTrack.model;

import java.util.List;

public class GiftTrackService {
	private GiftTrackDAO_interface dao = null;
	
	public GiftTrackService(){
		dao = new GiftTrackDAO();
	}
	
	public void addGiftTrack(GiftTrackVO giftTrackVO){
		dao.insert(giftTrackVO);
	}
	
	public void deleteGiftTrack(String mem_no, String gift_no){
		dao.delete(mem_no, gift_no);
	}
	
	public GiftTrackVO getOneGiftTrack(String mem_no, String gift_no){
		return dao.getByPrimaryKey(mem_no, gift_no);
	}
	
	public List<GiftTrackVO> getAll(){
		return dao.getAll();
	}
	
	public List<GiftTrackVO> getListByMemNo(String mem_no){
		return dao.getListByMemNo(mem_no);
	}
	
	public List<GiftTrackVO> getListByGiftNo(String gift_no){
		return dao.getListByGiftNo(gift_no);
	}
}
