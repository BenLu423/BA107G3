package com.giftOrderView.model;

import java.util.*;

public interface GiftOrderViewDAO_interface {

	public GiftOrderViewVO getByPrimaryKey(String giftr_no); // �浧����§

	public List<GiftOrderViewVO> getAll();

	public List<GiftOrderViewVO> getAll(Map<String, String[]> map);

	public byte[] getPic(String giftr_no, String columnName);

}
