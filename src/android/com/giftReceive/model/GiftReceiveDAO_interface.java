package android.com.giftReceive.model;

import java.sql.Connection;

import android.com.giftReceive.model.GiftReceiveVOA;

public interface GiftReceiveDAO_interface {
	public void insert(GiftReceiveVOA giftReceiveVO, Connection con);
}
