package android.com.gift.model;

import java.sql.Connection;
import java.util.*;

import android.com.gift.model.GiftVO;

import android.com.giftLabel.model.GiftLabelDAO;
import android.com.giftLabel.model.GiftLabelDAO_interface;
import android.com.giftLabel.model.GiftLabelVO;
import android.com.giftLabelDetail.model.GiftLabelDetailDAO;
import android.com.giftLabelDetail.model.GiftLabelDetailDAO_interface;
import android.com.giftLabelDetail.model.GiftLabelDetailVO;
import android.com.main.ImageUtil;

public class GiftService {
	private GiftDAO_interface dao;
	private GiftLabelDAO_interface labelDao;
	private GiftLabelDetailDAO_interface detailDao;

	public GiftService() {
		dao = new GiftDAO();
		labelDao = new GiftLabelDAO();
		detailDao = new GiftLabelDetailDAO();
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
	
	public void updateStatus(String gift_no, String gift_is_on) {
		dao.updateStatus(gift_no, gift_is_on);
	}

	public void deleteGift(String gift_no) {
		dao.delete(gift_no);
	}

	public GiftVO getOneGift(String gift_no) {
		return dao.getByPrimaryKey(gift_no);
	}

	public List<GiftVO> getAll() {
		List<GiftVO> list = dao.getAll();
		for (GiftVO giftVO : list) {
			giftVO.setGift_pic(ImageUtil.shrink(giftVO.getGift_pic(), 180));
		}
		return list;
	}


	public Set<String> getByKeyWord(String keyword) {
		return dao.getByKeyWord(keyword);
	}

	public Map<GiftVO, List<GiftLabelVO>> getGiftAll() {
		Map<GiftVO, List<GiftLabelVO>> map = new TreeMap<>(new Comparator<GiftVO>() {
			@Override
			public int compare(GiftVO o1, GiftVO o2) {
				String str1 = o1.getGift_no();
				String str2 = o2.getGift_no();
				return str1.compareTo(str2);
			}
		});
		List<GiftLabelVO> labelList = null;
		GiftLabelVO giftLabelVO = null;
		List<GiftLabelDetailVO> detailList = null;
		List<GiftVO> list = dao.getAll();
		for (GiftVO giftVO : list) {
			giftVO.setGift_pic(ImageUtil.shrink(giftVO.getGift_pic(), 150));
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

	public byte[] getPic(String gift_no) {
		return dao.getPic(gift_no);
	}
	
	public Map<GiftVO, List<GiftLabelVO>> getOne(String gift_no) {
		GiftVO giftVO = dao.getByPrimaryKey(gift_no);
		List<GiftVO> list = new ArrayList<GiftVO>();
		list.add(giftVO);
		return getLabelList(list);
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
			 * * * * * * * * * * * * * * * * * * * * * * * * 透過禮物編號(gift_no) *
			 * 來取得該擁有的標籤明細物件們(List<GiftLabelDetailVO>) *
			 * 並逐一找出對應的標籤物件(giftLabelVO)放入集合中 * 再加入Map中回傳給[gift_index.jsp]使用 * *
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
}
