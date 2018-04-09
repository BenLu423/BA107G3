package com.auth_feature.model;

import java.util.List;
import java.util.Set;

import com.admin.model.AdminVO;

public interface AuthFeatureDAO_interface {
	public void insert(AuthFeatureVO authVO);
	public void update(AuthFeatureVO authVO);
	public void delete(String auth_no);
	public AuthFeatureVO findByPrimaryKey(String auth_no);
	public List<AuthFeatureVO> getAll();
	//¨ÌÅv­­·j´M­û¤u
	public Set<AdminVO> getAdminsByAuthno(String auth_no);
	

}
