package com.coupons.model;

import java.sql.Date;
import java.util.List;

public interface CouponsDAO_interface {
	public void insert(CouponsVO couponsvo);
	public void update(CouponsVO couponsvo);
	public List<CouponsVO> findByDate(Date date);
	public List<CouponsVO> getAll();

}
