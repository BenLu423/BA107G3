package com.admin_auth.model;

import java.util.List;

public interface AdminAuthDAO_interface {
	public void insert(AdminAuthVO asauVO);
	public void delete(String adm_no,String auth_no);
	public AdminAuthVO findByPrimaryKey(String adm_no,String auth_no);
	public List<AdminAuthVO> getAll();
	

}
