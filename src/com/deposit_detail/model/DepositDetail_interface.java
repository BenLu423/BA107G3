package com.deposit_detail.model;

import java.util.List;

public interface DepositDetail_interface {
	public void insert(DepositDetailVO depositDetailVO);
	public DepositDetailVO getByPrimaryKey(String depod_no);
	public List<DepositDetailVO> getAll();
}
