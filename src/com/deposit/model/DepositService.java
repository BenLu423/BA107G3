package com.deposit.model;

import java.util.List;

public class DepositService {
	private DepositDAO_interface dao;
	public DepositService(){
		dao = new DepositDAO();
	}
	
	public void insert(DepositVO depositVO) {
		dao.insert(depositVO);
	}
	
	public void update(DepositVO depositVO) {
		dao.update(depositVO);
	}
	
	public List<DepositVO> getAll() {
		return dao.getAll();
	}
	
	public DepositVO getByPrimaryKey(String depo_no) {
		return dao.getByPrimaryKey(depo_no);
	}
	
}
