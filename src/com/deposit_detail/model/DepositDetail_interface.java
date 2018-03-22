package com.deposit_detail.model;

import java.util.List;

public interface DepositDetail_interface {
	public void insert(DepositDetailVO depositDetailVO);
	public List<DepositDetailVO> findByPrimaryKey(String mem_no);
	public List<DepositDetailVO> getAll();
}
