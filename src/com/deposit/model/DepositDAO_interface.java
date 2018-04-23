package com.deposit.model;

import java.util.List;

public interface DepositDAO_interface {
	public void insert(DepositVO depositVO);
	public void update(DepositVO depositVO);
	public DepositVO getByPrimaryKey(String depo_no);
	public List<DepositVO> getAll();

}
