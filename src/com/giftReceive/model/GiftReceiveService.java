package com.giftReceive.model;

import java.sql.Connection;

public class GiftReceiveService {
	private GiftReceiveDAO_interface dao;

	public GiftReceiveService() {
		dao = new GiftReceiveDAO();
	}

	public void insert(GiftReceiveVO giftReceiveVO, Connection con){
		dao.insert(giftReceiveVO, con);
	}
	
}
