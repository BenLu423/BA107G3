package com.giftOrder.model;

import java.util.List;
import java.util.Map;

import com.giftOrderDetail.model.GiftOrderDetailVO;
import com.giftReceive.model.GiftReceiveVO;

public class GiftOrderService {

	GiftOrderDAO_interface dao;

	public GiftOrderService() {
		dao = new GiftOrderDAO();
	}

	public void insert(GiftOrderVO giftOrderVO, Map<GiftOrderDetailVO, List<GiftReceiveVO>> giftOrderDetailMap) {
		dao.insert(giftOrderVO, giftOrderDetailMap);
	}

}
