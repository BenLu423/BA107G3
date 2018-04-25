package com.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.admin_auth.model.AdminAuthVO;
import com.auth_feature.model.AuthFeatureVO;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(String adminNo);
	public AdminVO findByPrimaryKey(String admin_no);
	public List<AdminVO> getAll();
	//�H���u�s���j�M�ӭ��u�v��
	public List<AdminAuthVO> getAdminAuths(String admin_no);
	
	//�H�b���j�M���u
	public AdminVO findByAcct(String admin_acct);
	

}
