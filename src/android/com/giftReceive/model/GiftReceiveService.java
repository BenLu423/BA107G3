package android.com.giftReceive.model;

import java.sql.Connection;

import android.com.giftReceive.model.GiftReceiveDAO;
import android.com.giftReceive.model.GiftReceiveDAO_interface;
import android.com.giftReceive.model.GiftReceiveVOA;

public class GiftReceiveService {
	private GiftReceiveDAO_interface dao;

	public GiftReceiveService() {
		dao = new GiftReceiveDAO();
	}

	public void insert(GiftReceiveVOA giftReceiveVO, Connection con){
		dao.insert(giftReceiveVO, con);
	}
}
	
