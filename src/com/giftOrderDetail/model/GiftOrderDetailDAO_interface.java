package com.giftOrderDetail.model;

import java.sql.Connection;
import java.util.*;

import com.gift.model.GiftVO;
import com.giftReceive.model.GiftReceiveVO;

public interface GiftOrderDetailDAO_interface {
	
//	public void insert(GiftOrderDetailVO giftOrderDetailVO, Connection con);
	public void insert(Map.Entry<GiftOrderDetailVO, List<GiftReceiveVO>> giftOrderDetail, Connection con);
}
