package com.giftDiscount.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.gift.model.GiftDAO;
import com.gift.model.GiftDAO_interface;
import com.gift.model.GiftVO;
import com.giftLabel.model.GiftLabelVO;
import com.giftLabelDetail.model.GiftLabelDetailVO;

public class GiftDiscountService {
	private GiftDiscountDAO_interface daoGD;
	private GiftDAO_interface daoG;
	
	private Map<GiftDiscountVO,GiftVO> getLabelList(List<GiftDiscountVO> list) {
		Map<GiftDiscountVO,GiftVO> map = new TreeMap<>(new Comparator<GiftDiscountVO>() {
			@Override
			public int compare(GiftDiscountVO o1, GiftDiscountVO o2) {
				String str1 = o1.getGiftd_no();
				String str2 = o2.getGiftd_no();
				return str2.compareTo(str1);
			}
		});
		
		for (GiftDiscountVO giftDiscountVO : list) {
			String gift_no = giftDiscountVO.getGift_no();
			GiftVO giftVO = daoG.getByPrimaryKey(gift_no);
			map.put(giftDiscountVO, giftVO);
		}
		return map;
	}
	
	public GiftDiscountService(){
		daoGD = new GiftDiscountDAO();
		daoG = new GiftDAO();
	}
	
	public Map<GiftDiscountVO, GiftVO> deductEditGD(Map<GiftDiscountVO, GiftVO> gifts,
			Map<GiftDiscountVO, GiftVO> giftEdits) {
		
		Map<GiftDiscountVO, GiftVO> map = new LinkedHashMap<>();
		Set<GiftDiscountVO> giftsList = gifts.keySet();
		GiftDiscountVO giftDiscountVO = null;
		Iterator<GiftDiscountVO> its = giftsList.iterator();
		int count = 0;
		while(its.hasNext()){
			giftDiscountVO = its.next();
			if(!giftEdits.containsKey(giftDiscountVO)){
				map.put(giftDiscountVO, gifts.get(giftDiscountVO));
				count++;
			}
		}
		return map;
	}	
	
	public void add(GiftDiscountVO giftDiscountVO){
		daoGD.insert(giftDiscountVO);
	}
	
	public void update(GiftDiscountVO giftDiscountVO){
		daoGD.update(giftDiscountVO);
	}
	
	public void delete(String giftd_no){
		daoGD.delete(giftd_no);
	}
	
	public GiftDiscountVO getOneGD(String giftd_no){
		return daoGD.getByPrimaryKey(giftd_no);
	}
	
	public Map<GiftDiscountVO,GiftVO> getOne(String giftd_no){
		Map<GiftDiscountVO,GiftVO> map = new HashMap<GiftDiscountVO,GiftVO>();
		GiftDiscountVO giftDiscountVO = daoGD.getByPrimaryKey(giftd_no);
		String gift_no = giftDiscountVO.getGift_no();
		GiftVO giftVO = daoG.getByPrimaryKey(gift_no);
		map.put(giftDiscountVO, giftVO);
		return map;
	}
	
	public List<GiftDiscountVO> getAll(){
		return daoGD.getAll();
	}
	
	public List<GiftDiscountVO> getTotal(){
		return daoGD.getTotal();
	}
	
	public Map<GiftDiscountVO,GiftVO> getGiftDiscountAll(Map<String, String[]> map){
		List<GiftDiscountVO> list = daoGD.getAll(map);
		return getLabelList(list);
	}
	
	public Map<GiftDiscountVO,GiftVO> getGiftDiscountAll(){
		List<GiftDiscountVO> list = daoGD.getAll();
		return getLabelList(list);
	}

	public Map<GiftDiscountVO,GiftVO> getGiftDiscountTotal(Map<String, String[]> map){
		List<GiftDiscountVO> list = daoGD.getTotal(map);
		return getLabelList(list);
	}
	
	public Map<GiftDiscountVO,GiftVO> getGiftDiscountTotal(){
		List<GiftDiscountVO> list = daoGD.getTotal();
		return getLabelList(list);
	}
}
