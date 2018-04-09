package com.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.auth_feature.model.AuthFeatureVO;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(String adminNo);
	public AdminVO findByPrimaryKey(String admin_no);
	public List<AdminVO> getAll();
	//以員工編號搜尋該員工權限
	public List<AuthFeatureVO> getAdminAuths(String admin_no);
	
	//以帳號搜尋員工
	public AdminVO findByAcct(String admin_acct);
	

}
