package com.giftLabel.model;

import java.util.ArrayList;
import java.util.List;

import com.giftLabelDetail.model.GiftLabelDetailDAO;
import com.giftLabelDetail.model.GiftLabelDetailDAO_interface;
import com.giftLabelDetail.model.GiftLabelDetailVO;

public class GiftLabelService {
	private GiftLabelDAO_interface labelDao;
	private GiftLabelDetailDAO_interface detailDao;
	
	public GiftLabelService(){
		labelDao = new GiftLabelDAO();
		detailDao = new GiftLabelDetailDAO();
	}
	
	public void addGiftLabel(GiftLabelVO giftLabelVO){
		labelDao.insert(giftLabelVO);
	}
	
	public void updateGiftLabel(GiftLabelVO giftLabelVO){
		labelDao.update(giftLabelVO);
	}
	
	public void deleteGiftLabel(String giftl_no){
		labelDao.delete(giftl_no);
	}
	
	public GiftLabelVO getOneGiftLabelByNo(String giftl_no){
		return labelDao.getByPrimaryKey(giftl_no);
	}
	
	public GiftLabelVO getOneGiftLabelByName(String giftl_name){
		return labelDao.getByLabelName(giftl_name);
	}	
	
	public List<GiftLabelVO> getListGiftLabel(String gift_no){
		List<GiftLabelVO> list = new ArrayList<GiftLabelVO>();
		GiftLabelVO giftLabelVO = null;
		List<GiftLabelDetailVO> details = detailDao.getByGiftNo(gift_no);
		for(GiftLabelDetailVO vo: details){
			giftLabelVO = labelDao.getByPrimaryKey(vo.getGiftl_no());
			list.add(giftLabelVO);
		}
		return list;
	}
	
	public List<GiftLabelVO> getAll(){
		return labelDao.getAll();
	}
	
}
