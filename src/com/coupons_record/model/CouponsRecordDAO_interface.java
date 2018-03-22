package com.coupons_record.model;

import java.sql.Connection;
import java.util.List;

public interface CouponsRecordDAO_interface {
	public void insert(Connection con,String nextCoup_NO);
	public void update(CouponsRecordVO couponsRecordVO);
	public List<CouponsRecordVO> findByMem(String mem_no);
	public List<CouponsRecordVO> getAll();

}
