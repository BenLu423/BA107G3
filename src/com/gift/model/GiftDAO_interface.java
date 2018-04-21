package com.gift.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import com.giftLabelDetail.model.GiftLabelDetailVO;

public interface GiftDAO_interface {
	public void insert(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList);
	public void update(GiftVO giftVO, List<GiftLabelDetailVO> giftLabelDetailList);
	public void updateStatus(String gift_no, String gift_is_on);
	public void updateTrackQty(String gift_no, Integer gift_track_qty, Connection con);
	public void updateBuyQty(GiftVO giftVO, Integer gift_buy_qty, Connection con);
	public void delete(String gift_no);
	public GiftVO getByPrimaryKey(String gift_no);
	public List<GiftVO> getSome(List<String> giftNoList);
	public List<GiftVO> getAll();
	public List<GiftVO> getAll(Map<String, String[]> map);
	public List<GiftVO> getAll(String keyword);
	public List<GiftVO> getCanBuy();
	public byte[] getPic(String gift_no);
}
