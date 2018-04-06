package com.giftReceive.model;

import java.sql.Connection;

public interface GiftReceiveDAO_interface {
	public void insert(GiftReceiveVO giftReceiveVO, Connection con);
}
