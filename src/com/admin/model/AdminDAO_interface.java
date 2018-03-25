package com.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.admin_feature.model.AuthFeatureVO;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(String adminNo);
	public AdminVO findByPrimaryKey(String adminAcct,String adminPwd);
	public List<AdminVO> getAll();
	//以員工編號搜尋該員工權限
	public Set<AuthFeatureVO> getAdminAuths(String admin_no);
	

}
