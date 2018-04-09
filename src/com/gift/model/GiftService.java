package com.gift.model;

import java.sql.Connection;
import java.util.*;

import com.giftDiscount.model.*;
import com.giftLabel.model.*;
import com.giftLabelDetail.model.*;

public class GiftService {
	private GiftDAO_interface dao;
	private GiftLabelDAO_interface labelDao;
	private GiftLabelDetailDAO_interface detailDao;
	private GiftDiscountDAO_interface discountDao;

	public GiftService() {
		dao = new GiftDAO();
		labelDao = new GiftLabelDAO();
		detailDao = new GiftLabelDetailDAO();
		discountDao = new GiftDiscountDAO();
	}

	private Map<GiftVO, List<GiftLabelVO>> getLabelList(List<GiftVO> list) {
		Map<GiftVO, List<GiftLabelVO>> map = new TreeMap<>(new Comparator<GiftVO>() {
			@Override
			public int compare(GiftVO o1, GiftVO o2) {
				String str1 = o1.getGift_no();
				String str2 = o2.getGift_no();
				return str2.compareTo(str1);
			}
		});
		List<GiftLabelVO> labelList = null;
		GiftLabelVO giftLabelVO = null;
		List<GiftLabelDetailVO> detailList = null;
		for (GiftVO giftVO : list) {
			/*
			 * * * * * * * * * * * * * * * * * * * * * * * * �z�L§���s��(gift_no) *
			 * �Ө��o�Ӿ֦������ҩ��Ӫ����(List<GiftLabelDetailVO>) *
			 * �óv�@��X���������Ҫ���(giftLabelVO)��J���X�� * �A�[�JMap���^�ǵ�[gift_index.jsp]�ϥ� * *
			 * * * * * * * * * * * * * * * * * * * * * *
			 */
			labelList = new ArrayList<>();
			detailList = detailDao.getByGiftNo(giftVO.getGift_no());
			for (GiftLabelDetailVO detailVO : detailList) {
				giftLabelVO = labelDao.getByPrimaryKey(detailVO.getGiftl_no());
				labelList.add(giftLabelVO);
			}
			map.put(giftVO, labelList);
		}
		return map;
	}

	public Map<GiftVO, List<GiftLabelVO>> deductEditGift(Map<GiftVO, List<GiftLabelVO>> gifts,
			Map<GiftVO, List<GiftLabelVO>> giftEdits) {
		
		Map<GiftVO, List<GiftLabelVO>> map = new LinkedHashMap<>();
		Set<GiftVO> giftsList = gifts.keySet();
		GiftVO giftVO = null;
		Iterator<GiftVO> its = giftsList.iterator();
		int count = 0;
		while(its.hasNext()){
			giftVO = its.next();
			if(!giftEdits.containsKey(giftVO)){
				map.put(giftVO, gifts.get(giftVO));
				count++;
			}
		}
		return map;
	}

	public void addGift(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList) {
		dao.insert(giftVO, giftLabelDetailList);
	}

	public void updateGift(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList) {
		dao.update(giftVO, giftLabelDetailList);
	}

	public void updateGiftTrack(String gift_no, Integer gift_track_qty, Connection con) {
		dao.updateTrackQty(gift_no, gift_track_qty, con);
	}

	public void updateGiftButQty(GiftVO giftVO, Integer gift_buy_qty, Connection con) {
		dao.updateBuyQty(giftVO, gift_buy_qty, con);
	}

	public void deleteGift(String gift_no) {
		dao.delete(gift_no);
	}

	public Integer getMoney(String gift_no){
		//���o��U[§�����X�����u�f]���̧C����
		
		//��l§������
		GiftVO giftVO = dao.getByPrimaryKey(gift_no);
		Integer oriMoney = giftVO.getGift_price();
		
		//�O�_�������u�f
		GiftDiscountVO giftDiscountVO = discountDao.getCurrentValidGift(gift_no);
		Double offer = 1.0;
		if(giftDiscountVO != null)
			offer = giftDiscountVO.getGiftd_percent();
		
		//�ثe�̧C����
		Double price = oriMoney * offer;
		return price.intValue();
	}
	
	public Integer getAmount(String gift_no){
		//���o�i�ʶR�ƶq�A�d��20�P�����u�f�ƶq��[�̤p��]
		
		//�O�_�������u�f
		GiftDiscountVO giftDiscountVO = discountDao.getCurrentValidGift(gift_no);
		Integer count = 10;
		if(giftDiscountVO != null)
			count = count<giftDiscountVO.getGiftd_amount() ? count : giftDiscountVO.getGiftd_amount();
		
		return count;
	}
	
	public GiftVO getOneGift(String gift_no) {
		return dao.getByPrimaryKey(gift_no);
	}

	public Map<GiftVO, List<GiftLabelVO>> getOne(String gift_no) {
		GiftVO giftVO = dao.getByPrimaryKey(gift_no);
		List<GiftVO> list = new ArrayList<GiftVO>();
		list.add(giftVO);
		return getLabelList(list);
	}
	
	public List<GiftVO> getAll() {
		return dao.getAll();
	}

	public Map<GiftVO, List<GiftLabelVO>> getGiftAll() {
		List<GiftVO> list = dao.getAll();
		return getLabelList(list);
	}

	public Map<GiftVO, List<GiftLabelVO>> getGiftAll(Map<String, String[]> map) {
		List<GiftVO> list = dao.getAll(map);
		return getLabelList(list);
	}
	
	public Map<GiftVO, List<GiftLabelVO>> getCanBuy() {
		List<GiftVO> list = dao.getCanBuy();
		return getLabelList(list);
	}
	
	public byte[] getPic(String gift_no) {
		return dao.getPic(gift_no);
	}

}
