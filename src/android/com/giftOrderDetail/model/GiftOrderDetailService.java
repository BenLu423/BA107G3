package android.com.giftOrderDetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import android.com.giftReceive.model.GiftReceiveVOA;

public class GiftOrderDetailService {
	private GiftOrderDetailDAO_interface dao;

	public GiftOrderDetailService() {
		dao = new GiftOrderDetailDAO();
	}
	
	public void insert(Map.Entry<GiftOrderDetailVO, List<GiftReceiveVOA>> giftOrderDetail, Connection con){
		dao.insert(giftOrderDetail, con);
	}

}
